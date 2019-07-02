package com.cetc.cloud.datasynch.provider.template.impl;


import com.alibaba.fastjson.JSONObject;
import com.cetc.cloud.datasynch.api.model.traffic.OffenceModel;
import com.cetc.cloud.datasynch.api.model.traffic.ProblemModel;
import com.cetc.cloud.datasynch.api.model.traffic.TrafficLinkModel;
import com.cetc.cloud.datasynch.api.model.traffic.VehiclePermitModel;
import com.cetc.cloud.datasynch.provider.mapper.traffic.ProblemMapper;
import com.cetc.cloud.datasynch.provider.mapper.traffic.TrafficOffenceMapper;
import com.cetc.cloud.datasynch.provider.mapper.traffic.VehiclePermitMapper;
import com.cetc.cloud.datasynch.provider.service.impl.DbOperateService;
import com.cetc.cloud.datasynch.provider.template.OuterJobRunnableTemplate;
import com.cetc.cloud.datasynch.provider.util.HttpClientUtil2;
import com.cetc.cloud.datasynch.provider.config.SpringContextUtil;
import com.cetc.cloud.datasynch.provider.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.exception.NoSuchAxisException;
import org.seimicrawler.xpath.exception.NoSuchFunctionException;
import org.seimicrawler.xpath.exception.XpathSyntaxErrorException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 交通数据爬取入库
 * Created by Administrator on 2019/6/20.
 */
@Slf4j
public class TrafficRunnable implements OuterJobRunnableTemplate {

    private DbOperateService dbOperateService;
    private VehiclePermitMapper vehiclePermitMapper;
    private ProblemMapper problemMapper;
    private TrafficOffenceMapper trafficOffenceMapper;
    private final String summaryUrl = "http://szjj.sz.gov.cn/ZWGK/TJSJ/TJXX/";


    //    #定义机动车临时通行证列表
    private final String[] motor_vehicle_permit_list = new String[]{"会展证", "搅拌车辆临时通行证", "余泥渣土砂石运输车辆临时通行证", "机动车辆临时通行证", "工程抢险准行证",
            "小型货车临时通行证", "大型货车临时通行证", "大型货车临时通行证", "剧毒化学品运输车辆临时通行证"};
    //    #定义道路支队名单
    private final String road_team = new String("福田大队");


    public TrafficRunnable() {
        this.dbOperateService = (DbOperateService) SpringContextUtil.getBean("dbOperateService");
        this.vehiclePermitMapper = (VehiclePermitMapper) SpringContextUtil.getBean("vehiclePermitMapper");
        this.problemMapper = (ProblemMapper) SpringContextUtil.getBean("problemMapper");
        this.trafficOffenceMapper = (TrafficOffenceMapper) SpringContextUtil.getBean("trafficOffenceMapper");
    }

    @Override
    public void run() {
        log.info(">>> TrafficRunnable---start");
        Thread.currentThread().setName("TrafficRunnable");
        String pages[] = new String[]{"index.htm", "index_1.htm", "index_2.htm"};
        for (String page : pages) {
            extractOnePage(page);
        }
        log.info(">>> TrafficRunnable---finished");
    }

    private void extractOnePage(String page) {

        //1.从页面抓取列表，获取带有“交通管理相关数据”的标签链接
        List<TrafficLinkModel> nodesList = new ArrayList<>();
        try {
            log.info("HttpQuery for URL:"+summaryUrl+page);
            String html = HttpClientUtil2.get(summaryUrl + page, new JSONObject());
            log.info("Received HTML,length:"+html.length());
            //获取节点
            String xpath_node = "//tbody/tr/td[2]/a";
            JXDocument jxDocument_summary = JXDocument.create(html);

            List<Object> list = jxDocument_summary.sel(xpath_node);
            for (Object o : list) {
                if (o instanceof Element) {
                    String text = ((Element) o).text();
                    if (text.contains("交通")&&text.contains("月")&&(!text.contains("2017"))) {
                        Elements hrefNodes = ((Element) o).getElementsByAttribute("href");
                        TrafficLinkModel trafficLinkModel = new TrafficLinkModel(text, hrefNodes.get(0).attr("href"));
                        nodesList.add(trafficLinkModel);
                    }
                }
            }

            for (TrafficLinkModel trafficLinkModel : nodesList) {
                String text = trafficLinkModel.getText();
                String url = trafficLinkModel.getHref();

                Date maxHtmlDate = getMaxDateInHtml(text);
                Set<Long> tbDatesSet = getDateSetInTb();
//                Date maxTbDate = new Date(1990-1900, 1, 1);//测试临时加的，后续需要替换
                if (null == tbDatesSet || !tbDatesSet.contains(maxHtmlDate.getTime())) {
                    //2.筛选出未入库的数据链接，并发起Http请求
                    String htmlDetail = null;
                    log.info("determined data in Date:"+ DateFormatUtils.format( maxHtmlDate,"yyyy-MM"));
                    htmlDetail = HttpClientUtil2.get(summaryUrl + url.substring(1), new JSONObject());
                    // 3.解析页面中的文本，通过正则表达式抓取列表，并写入数据库
                    //(2)机动车临时通行许可事项办理情况
                    String xpath3 = "//*/p[4]";

                    JXDocument jxDocument = JXDocument.create(htmlDetail);

                    List<Object> permmitText = jxDocument.sel(xpath3);
                    if (permmitText.size() == 0) {
                        log.error("cannot match text:" + htmlDetail + " with xpath:" + xpath3);
                        continue;
                    }
                    String subPermmitText = getTextFromSel(permmitText);

                    HashMap<String, String> vehiclePermitMap = new HashMap<>();
                    //循环遍历匹配规则数组，抽取匹配规则抽取到的结果
                    for (String regex : motor_vehicle_permit_list) {
                        String extractRes = RegexUtil.regexExtract(regex + "[\\s\\S]*?\\d+张", subPermmitText);
                        if (null != extractRes) {
                            vehiclePermitMap.put(regex, RegexUtil.regexExtract("\\d+", extractRes));
                        } else {
                            vehiclePermitMap.put(regex, "0");
                        }
                    }

                    //（3）路政违法行为查处情况及典型问题
                    String xpath4 = "//*/p[6]";
                    List<Object> offenceAndProblem = jxDocument.sel(xpath4);
                    if (offenceAndProblem.size() == 0) {
                        log.error("cannot match text:" + htmlDetail + " with xpath:" + xpath4);
                        continue;
                    }
                    String subOffenceAndProblem = getTextFromSel(offenceAndProblem);
                    //3.1 获取路政违法数据列表
                    String extractRes = RegexUtil.regexExtract(road_team + "[\\s\\S]*?\\d+宗", subOffenceAndProblem);
                    String offenceNum_futian = RegexUtil.regexExtract("\\d+", extractRes);

                    //3.2 获取涉及主要问题文本
                    String mainProblem = RegexUtil.regexExtract("涉及主要问题[\\s\\S]*?。", subOffenceAndProblem);

                    //入库（当前月）
                    //1 通行证入库
                    Set<String> keySet = vehiclePermitMap.keySet();
                    for (String key : keySet) {
                        VehiclePermitModel vehiclePermitModel = new VehiclePermitModel();
                        vehiclePermitModel.setPerimitName(key);
                        vehiclePermitModel.setPermitNum(Integer.valueOf(vehiclePermitMap.get(key)));
                        vehiclePermitModel.setPublishTime(maxHtmlDate);
                        vehiclePermitMapper.insert(vehiclePermitModel);
                    }
                    //2 交通违法案件入库
                    OffenceModel offenceModel = new OffenceModel();
                    offenceModel.setPublishTime(maxHtmlDate);
                    offenceModel.setNum(Integer.valueOf(offenceNum_futian));
                    trafficOffenceMapper.insert(offenceModel);

                    //3 涉及主要问题入库
                    ProblemModel problemModel = new ProblemModel();
                    problemModel.setPublishTime(maxHtmlDate);
                    problemModel.setText(mainProblem);
                    problemMapper.insert(problemModel);

                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (XpathSyntaxErrorException e) {
            e.printStackTrace();
        } catch (NoSuchAxisException e) {
            e.printStackTrace();
        } catch (NoSuchFunctionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTextFromSel(List<Object> inputTextList) {
        for (Object o : inputTextList) {
            if (o instanceof Element) {
                return ((Element) o).text();
            }
        }
        return null;
    }

    private Set<Long> getDateSetInTb() {
        //获取当前数据库中最新数据入库时间
        String sql = "select PUBLISH_TIME from ROAD_ADMIN_PROBLEM";
        List<HashMap> list = null;
        Set<Long> dates = new HashSet<>();
        try {
            list = dbOperateService.oracleQuerySql(sql);

            for (HashMap map : list) {
                if (null != list && list.size() != 0) {
                    Date publish_time = (Date) map.get("PUBLISH_TIME");
                    dates.add(publish_time.getTime());
                }
            }
            return dates;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Date getMaxDateInHtml(String text) {
        Calendar instance = Calendar.getInstance();
        instance.clear();
        //提取数字  年份，月份
        String month = "";
        String year = "";
        //定义匹配规则:由3个字符组成的单词
        String regex = "\\d{4}年\\d{1,2}月";//单个'\'需要加斜杠转义后才能表示为‘\’

        //把规则编译成模式对象
        Pattern p = Pattern.compile(regex);

        //通过模式对象得到匹配器对象
        Matcher m = p.matcher(text);
        String extractedDateText = null;
        //查找下一个匹配的字符串，进入循环则表示查找到
        while (m.find()) {
            extractedDateText = m.group();//获取并打印该匹配的字符串
        }
        if (null != extractedDateText) {
            year = extractedDateText.substring(0, 4);
            month = extractedDateText.split("年")[1].split("月")[0];

            instance.set(Calendar.YEAR, Integer.valueOf(year));
            instance.set(Calendar.MONTH, Integer.valueOf(month) - 1);

            return instance.getTime();
        } else {
            log.error("TrafficRunnable：未获取到匹配的时间内容");
            return null;
        }
    }



}
