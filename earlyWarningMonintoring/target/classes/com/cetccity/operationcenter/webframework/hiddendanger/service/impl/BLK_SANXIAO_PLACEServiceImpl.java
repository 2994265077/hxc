package com.cetccity.operationcenter.webframework.hiddendanger.service.impl;

import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.TheadTbodyModel;
import com.cetccity.operationcenter.webframework.core.tools.ImageFileTool;
import com.cetccity.operationcenter.webframework.core.tools.LoadMyUtil;
import com.cetccity.operationcenter.webframework.hiddendanger.api.model.SanXiaoTip;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.BlkCgEvtAttsMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BLK_CG_EVT_ATTS;
import com.cetccity.operationcenter.webframework.hiddendanger.service.BLK_SANXIAO_PLACEService;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.BlkSanxiaoPlaceMapper;
import com.cetccity.operationcenter.webframework.hiddendanger.dao.entity.BLK_SANXIAO_PLACE;
import com.cetccity.operationcenter.webframework.hiddendanger.tools.sanxiao.CalculationSanXiaoPlaceScore;
import com.cetccity.operationcenter.webframework.core.frame.model.HttpResponseModel;
import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class BLK_SANXIAO_PLACEServiceImpl implements BLK_SANXIAO_PLACEService {

    @Autowired
    BlkSanxiaoPlaceMapper bLK_SANXIAO_PLACEMapper;

    @Autowired
    OracleOperateService oracleOperateService;

    @Autowired
    BlkCgEvtAttsMapper bLK_CG_EVT_ATTSMapper;

    @Autowired
    CalculationSanXiaoPlaceScore calculationSanXiaoPlaceScore;

    public long getBLK_SANXIAO_PLACECount(BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE){
        long count = bLK_SANXIAO_PLACEMapper.getBLK_SANXIAO_PLACECount(bLK_SANXIAO_PLACE);
        return count;
    }

    public List<BLK_SANXIAO_PLACE> getBLK_SANXIAO_PLACE(BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE){
        List<BLK_SANXIAO_PLACE> bLK_SANXIAO_PLACE_list = bLK_SANXIAO_PLACEMapper.getBLK_SANXIAO_PLACE(bLK_SANXIAO_PLACE);
        return bLK_SANXIAO_PLACE_list;
    }

    public SanXiaoTip findBLK_SANXIAO_PLACETip(String sanxiao_id){
        String buildid;
        SanXiaoTip sanXiaoTip;
        BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE = new BLK_SANXIAO_PLACE();
        bLK_SANXIAO_PLACE.setID(sanxiao_id);
        List<BLK_SANXIAO_PLACE> bLK_SANXIAO_PLACE_list = bLK_SANXIAO_PLACEMapper.getBLK_SANXIAO_PLACE(bLK_SANXIAO_PLACE);
        if(bLK_SANXIAO_PLACE_list.isEmpty()){
            sanXiaoTip = null;
        }else {
            buildid = bLK_SANXIAO_PLACE_list.get(0).getLDDM();
            sanXiaoTip = calculationSanXiaoPlaceScore.calculationAttribute_OneScore(sanxiao_id,buildid);
        }
        return sanXiaoTip;
    }

    public NameDataModel findBLK_SANXIAO_PLACEDetails(String id){
        NameDataModel nameDataModel = new NameDataModel();
        BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE = new BLK_SANXIAO_PLACE();
        bLK_SANXIAO_PLACE.setID(id);
        List<BLK_SANXIAO_PLACE> bLK_SANXIAO_PLACE_list = bLK_SANXIAO_PLACEMapper.getBLK_SANXIAO_PLACE(bLK_SANXIAO_PLACE);
        List<NameDataModel> nameDataModel_list = new ArrayList<NameDataModel>();
        //1、场所详情
        NameDataModel nameDataModel_detail = new NameDataModel();
        LinkedHashMap map_detail = new LinkedHashMap();
        String top_details[] = {"名称","场所性质","经营面积","地址","街道","社区","楼栋","是否有营业执照","检查状态","是否需复查"};

        String place_type = bLK_SANXIAO_PLACE_list.get(0).getPLACE_TYPE();    //场所性质
        String place_state = bLK_SANXIAO_PLACE_list.get(0).getCHECKSTATE();    //检查状态
        String place_review = bLK_SANXIAO_PLACE_list.get(0).getISDOUBLECHECK();    //是否需复查状态
        /**字段BUSINUSS_AREA--经营面积:1:100㎡以下  2:100-200㎡   3:200㎡以上  */
        String businessArea = bLK_SANXIAO_PLACE_list.get(0).getBUSINESS_AREA();     //经营面积
        if ("1".equals(businessArea)){
            businessArea = "100㎡以下";
        }else if ("2".equals(businessArea)){
            businessArea = "100-200㎡";
        }else if ("3".equals(businessArea)){
            businessArea = "200㎡以上";
        }
        if("0".equals(place_state)){ place_state = "未检查"; }else{ place_state = "已检查"; }
        if("1".equals(place_review)){ place_review = "否"; }else if("2".equals(place_review)){ place_review = "是"; }else if("3".equals(place_review)){ place_review = "已现场整改"; }
        if("60021".equals(place_type)){ place_type = "小档口"; }else if("60022".equals(place_type)){ place_type = "小作坊"; }else if("60023".equals(place_type)){ place_type = "小娱乐场所"; }
        String vaule_details[] = {bLK_SANXIAO_PLACE_list.get(0).getNAME(),//名称
                place_type,               //场所性质
                businessArea,                               //经营面积
                bLK_SANXIAO_PLACE_list.get(0).getADDRDESC(),//地址
                bLK_SANXIAO_PLACE_list.get(0).getSTREET(),// "街道"
                bLK_SANXIAO_PLACE_list.get(0).getSQ(),// "社区"
                bLK_SANXIAO_PLACE_list.get(0).getDL(),// "楼栋"
                bLK_SANXIAO_PLACE_list.get(0).getIS_BUSINESS_CARD(),// "是否有营业执照"
                place_state,// "检查状态"
                place_review// "是否需复查"
                };
        for (int i=0;i<top_details.length;i++) {
            if("".equals(vaule_details[i])||vaule_details[i]==null) vaule_details[i] = "无";
            map_detail.put(top_details[i], vaule_details[i]);
        }
        nameDataModel_detail.setName("场所详情");
        nameDataModel_detail.setData(map_detail);
        //2、责任人
        NameDataModel nameDataModel_zrr = new NameDataModel();
        LinkedHashMap map_zrr = new LinkedHashMap();
        String top_zrr[] = {"经营者","业主姓名","经营者电话","业主电话","经营者证件号码","业主证件号码"};
        String vaule_zrr[] = {
                bLK_SANXIAO_PLACE_list.get(0).getOPERATOR_NAME(),
                bLK_SANXIAO_PLACE_list.get(0).getOWNER_NAME(),
                bLK_SANXIAO_PLACE_list.get(0).getOPERATOR_TEL(),
                bLK_SANXIAO_PLACE_list.get(0).getOWNER_TEL(),
                bLK_SANXIAO_PLACE_list.get(0).getOPERATOR_CARD_ID(),
                bLK_SANXIAO_PLACE_list.get(0).getOWNER_CARD_ID()};
        for (int i=0;i<top_zrr.length;i++) {
            if("".equals(vaule_zrr[i])||vaule_zrr[i]==null) vaule_zrr[i] = "无";
            map_zrr.put(top_zrr[i],vaule_zrr[i]);
        }
        nameDataModel_zrr.setName("责任人");
        nameDataModel_zrr.setData(map_zrr);

        //3、巡查隐患
        NameDataModel nameDataModel_hidden = new NameDataModel();
        String sql = "SELECT DISTINCT a.id,a.OPERATOR_NAME,d.CREATER_NAME,a.OPERATOR_TEL,d.STATE,d.EVENT_NAME,d.EVENT_SOURCE,d.EVENT_CONTENT,d.EVENT_TIME,d.REPORT_NAME,d.REPORT_ADDRESS,d.SYSTEMID,d.REPORT_PHONE\n" +
                "from BLK_SANXIAO_PLACE a,BLK_CHENGGUAN_EVENT d\n" +
                "WHERE a.name=d.EVENT_NAME\n" +
                "and a.ADDRESS=d.ADDRESS\n" +
                "and d.EVENT_SOURCE='6'\n" +
                "and d.state='1'\n" +
//                "and d.EVENT_TIME > add_months(SYSDATE, -2)\n"+
                "and a.id = '"+id+"'\n" +
                "ORDER BY d.EVENT_TIME DESC";
        List<LinkedHashMap> hidden_danger_list = oracleOperateService.querySql(sql);
        String top_hidden[] = {"场所名称","事件来源","事件描述","发生时间","安全员","事件状态","巡查图片", "处置"};
        List<String[]> hidden_list = new ArrayList();
        Map<String,String> map_event_status = new HashMap();
        map_event_status.put("0","受理");map_event_status.put("1","分拨");
        map_event_status.put("2","办结");map_event_status.put("3","已评价");
        map_event_status.put("9","归档(字典项)");map_event_status.put("50","挂起");
        map_event_status.put("80","作废");

        for (LinkedHashMap<String,String> map_danger:hidden_danger_list) {
            List<String> list_img = new ArrayList<>();
            List<BLK_CG_EVT_ATTS> bLK_CG_EVT_ATTS_list = bLK_CG_EVT_ATTSMapper.getImgUrlBLK_CG_EVT_ATTS(map_danger.get("SYSTEMID"));
            for (BLK_CG_EVT_ATTS bLK_CG_EVT_ATTS:bLK_CG_EVT_ATTS_list) {
                HttpResponseModel<String> imgUrl = ImageFileTool.getSanxiaoImageFileUrl(bLK_CG_EVT_ATTS.getFILEPATH());
                list_img.add(imgUrl.getData());
            }
            String event_status = map_event_status.get(map_danger.get("STATE"));//事件状态
            String hidden_tbody[] = {
                    LoadMyUtil.checkMyVariable(map_danger.get("EVENT_NAME")),
                    "巡查上报",
                    LoadMyUtil.checkMyVariable(map_danger.get("EVENT_CONTENT")),
                    LoadMyUtil.checkMyVariable(map_danger.get("EVENT_TIME")),
                    LoadMyUtil.checkMyVariable(map_danger.get("CREATER_NAME")),
                    event_status, String.join("、", list_img), map_danger.get("SYSTEMID")};
            hidden_list.add(hidden_tbody);
        }
        TheadTbodyModel theadTbodyModel = new TheadTbodyModel();
        theadTbodyModel.setThead(top_hidden);
        theadTbodyModel.setTbody(hidden_list);
        nameDataModel_hidden.setName("巡查隐患");
        nameDataModel_hidden.setData(theadTbodyModel);

        nameDataModel_list.add(nameDataModel_detail);
        nameDataModel_list.add(nameDataModel_zrr);
        if(!hidden_list.isEmpty()) {
            nameDataModel_list.add(nameDataModel_hidden);
        }
        nameDataModel.setName(bLK_SANXIAO_PLACE_list.get(0).getADDRESS());
        nameDataModel.setData(nameDataModel_list);
        return nameDataModel;
    }

    public NameDataModel findBLK_SANXIAO_PLACEScore(String sanxiao_id){
        String buildid;
        NameDataModel nameDataModel;
        BLK_SANXIAO_PLACE bLK_SANXIAO_PLACE = new BLK_SANXIAO_PLACE();
        bLK_SANXIAO_PLACE.setID(sanxiao_id);
        List<BLK_SANXIAO_PLACE> bLK_SANXIAO_PLACE_list = bLK_SANXIAO_PLACEMapper.getBLK_SANXIAO_PLACE(bLK_SANXIAO_PLACE);
        if(bLK_SANXIAO_PLACE_list.isEmpty()){
            nameDataModel = null;
        }else {
            buildid = bLK_SANXIAO_PLACE_list.get(0).getLDDM();
            nameDataModel = calculationSanXiaoPlaceScore.calculationScore(sanxiao_id,buildid);
        }
        return nameDataModel;
    }
}
