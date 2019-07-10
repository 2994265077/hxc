package com.cetccity.operationcenter.webframework.urbansign.tools;

import com.cetccity.operationcenter.webframework.web.service.db.OracleOperateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * 工程包名:   com.cetccity.operationcenter.webframework.urbansign.tools
 * 项目名称:   futian
 * 创建描述:   heliangming 补充
 * Creator:     heliangming
 * Create_Date: 16:47 2019-07-08
 * Updater:     heliangming
 * Update_Date：16:47 2019-07-08
 * 更新描述:    heliangming 补充
 **/
@Component
public class RightThirteenDrillDownUtil {

    @Autowired
    OracleOperateService oracleOperateService;

    public Map getMap(){
        Map map = new HashMap(100);
        map.put("困难群众人员","BLK_DIFFICULT_PO");
        map.put("残疾人员","BLK_DISABLED");
        map.put("社会团体","BLK_SOCIAL_GROUPS");
        map.put("重点上访人员","BLK_KEY_APPEALS");
        map.put("现役军人","BLK_MILITARY");
        map.put("残疾军人","BLK_DISABLED_MILITARY");
        map.put("学校数据","BLK_SCHOOL");
        map.put("困难家庭","BLK_DIFFICULT_FA");
        map.put("高龄老人70至80岁","BLK_OLD_ALLOWANCE_70");
        map.put("失独家庭","BLK_LOST_FAMILY");
        map.put("复员军人","BLK_DEMOBILIZED_SOLDIERS");
        map.put("严重精神障碍患者","BLK_BRAIN_DISABLED");
        map.put("分娩记录_新生儿情况","BLK_BABY");
        map.put("业主信息","BLK_OWNER");
        map.put("高龄老人100岁以上","BLK_OLD_ALLOWANCE_100");
        map.put("无军籍职工","BLK_MILITARY_FREE_WORKERS");
        map.put("失业人员","BLK_UNEMPLOYED_PERSONS");
        map.put("敬老优待证黄蓝证","BLK_ELDER_CRETIFICATE");
        map.put("房屋信息","BLK_HOUSE");
        map.put("精神病人","BLK_MENTAL_PATIENT");
        map.put("楼栋信息","BLK_BUIDING");
        map.put("关注人员","BLK_CONCERN_PERSON");
        map.put("因公牺牲军人遗属","BLK_SOILDER_SURVIVORS");
        map.put("死亡人口数据","BLK_DEATH");
        map.put("伤残国家机关工作人员","BLK_STATE_ORGANS_DISABLED");
        map.put("困难党员","BLK_DIFFICULT_PARTY_MEM");
        map.put("社区矫正","BLK_CMNTY_CORRECTION");
        map.put("基建工程兵","BLK_JIJIAN_CORPS");
        map.put("困难职工","BLK_DIFFICULT_STAFF");
        map.put("高龄老人90至100岁","BLK_OLD_ALLOWANCE_90");
        map.put("五老人员","BLK_5_OLD");
        map.put("关爱女孩","BLK_GIRL_CARE");
        map.put("灵活就业人员","BLK_FLEXIBLE_EMPLOYEE");
        map.put("三属人员","BLK_3_SURVIVORS");
        map.put("流动人口","BLK_FLOW_PERSONNEL");
        map.put("企业个体户法人","BLK_INDIVIDUAL_LEGAL_PERSON");
        map.put("伤残家庭","BLK_DISABLED_FAMILY");
        map.put("企业单位法人","BLK_ENTERPRISE_LEGAL_PERSON");
        map.put("境外人口","BLK_OVERSEAS_PEOPLE");
        map.put("稳控对象人员","BLK_STABLE_CONTROL_PERSON");
        map.put("低保边缘家庭","BLK_MINI_ASSURANCE");
        map.put("60岁以上农村籍退役士兵","BLK_RETIRED_SOLDIER_NC_OVER60");
        map.put("伤残人民警察","BLK_DISABLED_POLICE");
        map.put("户籍人口","BLK_PEOPLE");
        map.put("党员数据","BLK_PARTY_MEMBER");
        map.put("法人基础","BLK_LEGAL_PERSON");
        map.put("病故军人遗属","BLK_SICK_SOLDIER");
        map.put("高龄老人80至90岁","BLK_OLD_ALLOWANCE_80");
        map.put("事件基础","BLK_EVENT");
        map.put("带病回乡人员","BLK_ILL_RETURN_PERSON");
        map.put("刑满释放人员","BLK_RELEASED_PERSON");
        map.put("低保人员","BLK_MINI_ASSURANCE_PEOPLE");
        map.put("分娩记录表","BLK_BIRTH_RECORD");
        map.put("安置帮教人员","BLK_HELP_EDUCATE_PEOPLE");
        map.put("单亲家庭","BLK_SINGLE_PARENT");
        map.put("吸毒人员","BLK_DRUG_ADDICTS");
        map.put("参战涉核人员","BLK_NUCLEAR_WAR_PERSONNEL");
        map.put("维稳事件","BLK_WEIWEN");
        map.put("烈士遗属","BLK_MARTYR_DEPENDANTS");
        map.put("楼栋长信息","BLK_BUILDING_PERSIDENT");
        return map;
    }

    public List<HashMap> getBarNoStreet(String name){
        List<HashMap> data = new ArrayList<>();
        String sql = "SELECT STREET_NAME NAME_X,count(*) "+name+" from "+getMap().get(name)+" where REGION_CODE = '440304' and STREET_NAME is not null GROUP BY STREET_NAME";
        List<LinkedHashMap> list = oracleOperateService.querySql(sql);
        list.stream().forEach(u-> data.add(u));
        return data;
    }

    public List<HashMap> getBarHasStreet(String streetCode, String name){
        List<HashMap> data = new ArrayList<>();
        String sql = "SELECT COMMUNITY_NAME NAME_X,count(*) "+name+" from "+getMap().get(name)+" where STREET_CODE = '"+streetCode+"' and COMMUNITY_NAME is not null GROUP BY COMMUNITY_NAME";
        List<LinkedHashMap> list = oracleOperateService.querySql(sql);
        list.stream().forEach(u->data.add(u));
        return data;
    }

}
