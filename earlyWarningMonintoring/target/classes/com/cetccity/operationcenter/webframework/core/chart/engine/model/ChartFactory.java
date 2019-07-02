package com.cetccity.operationcenter.webframework.core.chart.engine.model;

/**
 * @Package: com.cetccity.operationcenter.webframework.core.chart.engine
 * @Project: Futian-EarlyWarningMonitoring
 * @Creator: huangzezhou
 * @Create_Date: 2018/11/28 12:08
 * @Updater: huangzezhou
 * @Update_Date: 2018/11/28 12:08
 * @Update_Description: huangzezhou 补充
 * @Description: //TODO
 **/

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueTypeModel;
import org.apache.commons.lang3.StringUtils;
import java.util.*;

/**
 * 纵轴目前发现两种类型：
 * 一种是不同类型赌赢不同列，类型在列名上，值为数量，因此在求和的时候用SUM去计算
 * 所有的类型都在一列中，值为类型名，因此在求和的时候用Count或者size即可。
 *
 * 横轴一般也有两种情况：
 * 一种是固定的，与查询的数据无关。例如时间：最近6个月，最近一年等。
 * 一种是跟数据有关的，例如分街道统计，分设备类型统计（类型直接放到X轴上）。
 *
 * 维度：
 * 一维：饼图。统计每个类型的个数
 * 二维：条形图、折线图。X轴为分类标准，Y轴为符合该标准的数量。
 * 二维增强：多条形图、多则线条。X轴为某个确定维度的分类标准，Y轴为每个类型符合该标准的数量。二维增强与二维不同点在于，对应同一个X轴分类，Y轴有多个类型与之对应。
 *
 * 构建步骤如下：
 * 1. 查询数据
 * 2. 构建X轴的有序分类集合List
 * 3. 构建Y轴的类型。形成一个类似table的结构，实际用HashMap<String, List<NameValueTypeModel<Integer>>>
 * 4. 遍历所有的数据，a.判断该数据属于哪一个部分，b.是对其+1还是++num
 * 5. 遍历结构，返回标准结构List<BarOrLineModel>。之前的Hash结构是为了快速找到。
 *
 * 核心在于4，如何判断属于哪一部分。
 * 遍历数据的时候，有列名、值，写判断函数
 */
public abstract class ChartFactory {

    /**
     * 原始数据
     */
    protected List<HashMap> input = null;

    /**
     * 统计的数据
     * data<X,Y>
     * 该HashMap为x轴与y轴的映射关系。当成Table用
     */
    protected LinkedHashMap<String, LinkedHashMap<String, Integer>> dataMap = null;

    /**
     * 数据访问层
     * @return
     */
    public abstract List<HashMap> queryData();

    /**
     * 图表的结构初始化:X轴
     * @return
     */
    public abstract List<String> initX();

    /**
     * 图表的结构初始化:Y轴
     * @return
     */
    public abstract List<String> initY();

    public ChartFactory() {
        //请求数据
        input = queryData();
        //创建x轴和y轴
        List<String> x = initX();
        List<String> y = initY();
        dataMap = new LinkedHashMap<String, LinkedHashMap<String, Integer>>();
        for (int i = 0; i < x.size(); ++i) {
            LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
            for (int j = 0; j < y.size(); ++j) {
                map.put(y.get(j), 0);
            }
            dataMap.put(x.get(i), map);
        }
        //统计
        for (int i = 0; i < input.size(); ++i) {
            try {
                match(input.get(i));
            } catch (Exception e) {
                //不做任何处理，说明该项数据不属于table表格
            }
        }
    }

    //匹配函数，遍历每一行数据时，处理逻辑。
    public abstract void match(HashMap row);

    /**
     * 构建图表
     */
    public List<BarOrLineModel> build(Map<String,String> map_type) {
        /**Map<String,String> map = new HashMap();
             map.put("type","bar");map.put("stack","人口");   //指定图形类型，
         */
        //遍历统计数据，构造条形图/折线图数据结构
        List<BarOrLineModel> result = new ArrayList<BarOrLineModel>();
        int i = 0;
        for (String xKey: dataMap.keySet()) {
            LinkedHashMap<String, Integer> map = dataMap.get(xKey);
            int j = 0;
            for (String yKey: map.keySet()) {
                Integer value = map.get(yKey);
                if (i == 0) {
                    List<NameValueTypeModel<Integer>> list = new ArrayList<NameValueTypeModel<Integer>>();
                    list.add(new NameValueTypeModel<Integer>(xKey, value));
                    if(map_type==null){
                        result.add(new BarOrLineModel(yKey, list));
                    }else{
                        result.add(new BarOrLineModel(yKey, map_type.get("type"),map_type.get("stack"), list));
                    }
                } else {
                    result.get(j).getData().add(new NameValueTypeModel<Integer>(xKey, value));
                }
                j++;
            }
            i++;
        }
        return result;
    }

    /**
     * 判断字符串是否为空， null、""、"null"都算为空
     * @param input 字符串
     * @return
     */
    public static boolean isNull(String input){
        return StringUtils.isEmpty(input) || "null".equalsIgnoreCase(input);
    }

    /**
     * 字符串转int，如果为空则返回0
     * @param input 字符串
     * @return
     */
    public static int str2num(String input) {
        if (isNull(input)) return 0;
        else return Integer.parseInt(input);
    }

    /**
     * 统计图表中每一类型的总数
     * @param chart 图表
     * @return
     */
    public static List<NameValueTypeModel<Integer>> countByType(List<BarOrLineModel> chart) {

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        //初始化类型
        for (NameValueTypeModel<Integer> model: chart.get(0).getData()) {
            map.put(model.getName(), 0);
        }
        for (BarOrLineModel barModel: chart) {
            for (NameValueTypeModel<Integer> model: barModel.getData()) {
                map.put(model.getName(), map.get(model.getName())+ model.getValue());
            }
        }
        return map2ListNameValue(map);
    }

    /**
     * map2ListNameValue
     * @param hashMap HashMap
     * @return
     */
    public static List<NameValueTypeModel<Integer>> map2ListNameValue(HashMap<String, Integer> hashMap) {
        List<NameValueTypeModel<Integer>> result = new ArrayList<NameValueTypeModel<Integer>>();
        for (String key: hashMap.keySet()) {
            result.add(new NameValueTypeModel<Integer>(key, hashMap.get(key)));
        }
        return result;
    }
}
