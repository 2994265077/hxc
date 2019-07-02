package com.cetccity.operationcenter.webframework.urbansign.service.impl;

import com.cetccity.operationcenter.webframework.urbansign.api.model.*;
import com.cetccity.operationcenter.webframework.urbansign.dao.HISTORY_FIREMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.QAJJ_REPACCIDENT_V_SignMapper;
import com.cetccity.operationcenter.webframework.urbansign.dao.entity.QAJJ_REPACCIDENT_V;
import com.cetccity.operationcenter.webframework.urbansign.service.PublicSecurityService;
import com.cetccity.operationcenter.webframework.publichealth.dao.YJJC_QWJJ_SDM_INFO_VMapper;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameDataModel;
import com.cetccity.operationcenter.webframework.core.frame.basicmodel.NameValueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("PublicSecurityService")
public class PublicSecurityServiceImpl implements PublicSecurityService {

    @Autowired
    QAJJ_REPACCIDENT_V_SignMapper qAJJ_REPACCIDENT_V_SignMapper;

    @Autowired
    YJJC_QWJJ_SDM_INFO_VMapper yJJC_QWJJ_SDM_INFO_VMapper;

    @Autowired
    HISTORY_FIREMapper hISTORY_FIREMapper;

    /*城市体征--公共安全*/
    public NameDataModel publicSecurity() {
        NameDataModel nameDataModel = new NameDataModel();
        PublicSecurity publicSecurity = new PublicSecurity();
        Calendar cal = Calendar.getInstance();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String day = date.split("-")[2];
        String time2 = ""+year+month+day+"%";
        if("0".equals(month.substring(0,1))){
            month =  month.split("0")[1];
        }
        String time1 = ""+year+"/"+month+"/"+day+"%";
        //昨天
        Calendar cal_y = Calendar.getInstance();
        cal_y.add(Calendar.DATE, -1);
        String date_y = new SimpleDateFormat("yyyy-MM-dd").format(cal_y.getTime());
        String year_y = date_y.split("-")[0];
        String month_y = date_y.split("-")[1];
        String day_y = date_y.split("-")[2];
        String time_y = ""+year_y+month_y+day_y+"%";
        if("0".equals(month_y.substring(0,1))){
            month_y =  month_y.split("0")[1];
        }

        String time_yesterday = ""+year_y+"/"+month_y+"/"+day_y+"%";
        List<QAJJ_REPACCIDENT_V> safetyAccident_list = qAJJ_REPACCIDENT_V_SignMapper.safetyAccidentCount(time1);
        List<QAJJ_REPACCIDENT_V> safetyAccident_list_yesterday =  qAJJ_REPACCIDENT_V_SignMapper.safetyAccidentCount(time_yesterday);
        List<HISTORY_FIRE_NUM> casualties_list = hISTORY_FIREMapper.casualtiesCount(time2);
        //当天重大安全事故
        String safetyAccidenttrend = "";
        Integer safetyAccident_num = safetyAccident_list.size();
        Integer safetyAccident_num_yesterday = safetyAccident_list_yesterday.size();
        if(safetyAccident_num-safetyAccident_num_yesterday>0){
            safetyAccidenttrend = "1";
        }else if(safetyAccident_num-safetyAccident_num_yesterday<0){
            safetyAccidenttrend = "-1";
        }else{
            safetyAccidenttrend = "0";
        }
        //今日伤亡人数
        Integer Injured_num = 0;
        Integer dead_num = 0;

        for (QAJJ_REPACCIDENT_V safetyAccident:safetyAccident_list) {
            Injured_num += Integer.parseInt(safetyAccident.getTotalboo())+Integer.parseInt(safetyAccident.getTotalgbh());
            dead_num += Integer.parseInt(safetyAccident.getTotaldead());
        }
        for (HISTORY_FIRE_NUM casualties:casualties_list) {
            Injured_num += Integer.valueOf(casualties.getHurtnum());
            dead_num += Integer.valueOf(casualties.getDeadnum());
        }
        List<NameValueModel> nameValueModel_Injured_list = new ArrayList<NameValueModel>();
        List<NameValueModel> nameValueModel_Dead_list = new ArrayList<NameValueModel>();

        for (int i = 0; i < 7; i++) {
            Integer Injured_num_month = 0;
            Integer dead_num_month = 0;
            String month2_r ="";
            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.MONTH, -i);
            String date2 = new SimpleDateFormat("yyyy-MM").format(cal2.getTime());
            String year2 = date2.split("-")[0];
            String month2 = date2.split("-")[1];
            String time2_month = ""+year+month2+"%";
            if("0".equals(month2.substring(0,1))){
                 month2_r =  month2.split("0")[1];
            }
            String time1_month = ""+year+"/"+month2_r+"%";
            List<QAJJ_REPACCIDENT_V> safetyAccident_list_month = qAJJ_REPACCIDENT_V_SignMapper.safetyAccidentCount(time1_month);
            List<HISTORY_FIRE_NUM> casualties_list_month = hISTORY_FIREMapper.casualtiesCount(time2_month);
            for (QAJJ_REPACCIDENT_V safetyAccident:safetyAccident_list_month) {
                Injured_num_month += Integer.valueOf(safetyAccident.getTotalboo())+Integer.valueOf(safetyAccident.getTotalgbh());
                dead_num_month += Integer.valueOf(safetyAccident.getTotaldead());
            }
            for (HISTORY_FIRE_NUM casualties:casualties_list_month) {
                if("".equals(casualties.getHurtnum())||casualties.getHurtnum()==null){
                    casualties.setHurtnum("0");
                }
                if("".equals(casualties.getDeadnum())||casualties.getDeadnum()==null){
                    casualties.setDeadnum("0");
                }
                Injured_num_month += Integer.valueOf(casualties.getHurtnum());
                dead_num_month += Integer.valueOf(casualties.getDeadnum());
            }
            nameValueModel_Injured_list.add(NameValueModel.builder().name(year2+month2).value(String.valueOf(Injured_num_month)).build());
            nameValueModel_Dead_list.add(NameValueModel.builder().name(year2+month2).value(String.valueOf(dead_num_month)).build());
        }

        NameTodayDataModel nameTodayDataModel_Safety = new NameTodayDataModel();
        NameTodayDataModel nameTodayDataModel_Injured = new NameTodayDataModel();
        NameTodayDataModel nameTodayDataModel_Death = new NameTodayDataModel();
        nameTodayDataModel_Safety.setName("今日重大安全事故");
        nameTodayDataModel_Safety.setToday(String.valueOf(safetyAccident_num));
        nameTodayDataModel_Safety.setData(safetyAccidenttrend);

        //list--nameValueModel_Injured_list 反向
        List<NameValueModel> nameValueModel_Injured_list_reverse = new ArrayList<NameValueModel>();
        ListIterator<NameValueModel> li = nameValueModel_Injured_list.listIterator();
        for (li = nameValueModel_Injured_list.listIterator(); li.hasNext();) {
            li.next();
        }
        for (int i = nameValueModel_Injured_list.size() - 1; i >= 0; i--) {
            nameValueModel_Injured_list_reverse.add(nameValueModel_Injured_list.get(i));
        }

        //list--nameValueModel_Dead_list 反向
        List<NameValueModel> nameValueModel_Dead_list_reverse = new ArrayList<NameValueModel>();
        ListIterator<NameValueModel> li_2 = nameValueModel_Dead_list.listIterator();
        for (li_2 = nameValueModel_Dead_list.listIterator(); li_2.hasNext();) {
            li_2.next();
        }
        for (int i = nameValueModel_Dead_list.size() - 1; i >= 0; i--) {
            nameValueModel_Dead_list_reverse.add(nameValueModel_Dead_list.get(i));
        }

        nameTodayDataModel_Injured.setName("安全事故受伤人数");
        nameTodayDataModel_Injured.setToday(String.valueOf(Injured_num));
        nameTodayDataModel_Injured.setData(nameValueModel_Injured_list_reverse);

        nameTodayDataModel_Death.setName("安全事故死亡人数");
        nameTodayDataModel_Death.setToday(String.valueOf(dead_num));
        nameTodayDataModel_Death.setData(nameValueModel_Dead_list_reverse);

        publicSecurity.setMajorSafetyAccident(nameTodayDataModel_Safety);
        publicSecurity.setInjury(nameTodayDataModel_Injured);
        publicSecurity.setDeath(nameTodayDataModel_Death);
        nameDataModel.setName("公共安全");
        nameDataModel.setData(publicSecurity);
        return nameDataModel;
    }

    /*城市体征--公共安全--详情*/
    public PublicSecurityDetail publicSecurityDetail(){
        PublicSecurityDetail publicSecurityDetail = new PublicSecurityDetail();
        //1、安全生产
        NameDataModel nameDataModel_safetyProduction = new NameDataModel();
        List<NameValueModel> safetyProduction_list = new ArrayList<NameValueModel>();
        //城区连续无安全生产事件天数
        Integer daysWithoutIncidents = 0;
        //1、公共卫生
        NameDataModel nameDataModel_publicHealth = new NameDataModel();
        List<NameValueModel> publicHealth_list = new ArrayList<NameValueModel>();
        Integer daysWithoutpublicHealth = 0;
        //3、消防安全
        NameDataModel nameDataModel_fireSafety = new NameDataModel();
        List<NameValueModel> fireSafety_list = new ArrayList<NameValueModel>();
        Integer daysWithoutfireSafety = 0;
        for (int i=0;i<30;i++){

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -i);
            String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
            String year = date.split("-")[0];
            String month = date.split("-")[1];
            String day = date.split("-")[2];
            date=year+month+day;
            if("0".equals(month.substring(0,1))){
                month =  month.split("0")[1];
            }
            if("0".equals(day.substring(0,1))){
                day =  day.substring(1,2);
            }
            String time = ""+year+"/"+month+"/"+day+"%";
            Integer safetyProduction_day_total = qAJJ_REPACCIDENT_V_SignMapper.selectCountOfToday(time);
            Integer publicHealth_day_total = yJJC_QWJJ_SDM_INFO_VMapper.selectCountOfToday(time);
            Integer fireSafety_day_total = hISTORY_FIREMapper.selectCountOfToday(date);

            if(safetyProduction_day_total==0){
                daysWithoutIncidents++;
            }
            if(publicHealth_day_total==0) {
                daysWithoutpublicHealth++;
            }
            if(fireSafety_day_total==0) {
                daysWithoutfireSafety++;
            }
            safetyProduction_list.add(NameValueModel.builder().name(date).value(String.valueOf(safetyProduction_day_total)).build());
            publicHealth_list.add(NameValueModel.builder().name(date).value(String.valueOf(publicHealth_day_total)).build());
            fireSafety_list.add(NameValueModel.builder().name(date).value(String.valueOf(fireSafety_day_total)).build());
        }

         //安全生产
        nameDataModel_safetyProduction.setName("安全生产");
        NameValueDataModel nameValueDataModel_safetyProduction = new NameValueDataModel();
        nameValueDataModel_safetyProduction.setName("城区连续无安全生产事件天数");
        nameValueDataModel_safetyProduction.setValue(String.valueOf(daysWithoutIncidents));
        nameValueDataModel_safetyProduction.setData(safetyProduction_list);
        nameDataModel_safetyProduction.setData(nameValueDataModel_safetyProduction);
        //公共卫生
        nameDataModel_publicHealth.setName("公共卫生");
        NameValueDataModel nameValueDataModel_publicHealth = new NameValueDataModel();
        nameValueDataModel_publicHealth.setName("城区连续无公共卫生事件天数");
        nameValueDataModel_publicHealth.setValue(String.valueOf(daysWithoutpublicHealth));
        nameValueDataModel_publicHealth.setData(publicHealth_list);
        nameDataModel_publicHealth.setData(nameValueDataModel_publicHealth);
        //消防安全
        nameDataModel_fireSafety.setName("消防安全");
        NameValueDataModel nameValueDataModel_fireSafety = new NameValueDataModel();
        nameValueDataModel_fireSafety.setName("城区连续无消防事件天数");
        nameValueDataModel_fireSafety.setValue(String.valueOf(daysWithoutfireSafety));
        nameValueDataModel_fireSafety.setData(fireSafety_list);
        nameDataModel_fireSafety.setData(nameValueDataModel_fireSafety);

        publicSecurityDetail.setSafetyProduction(nameDataModel_safetyProduction);
        publicSecurityDetail.setPublicHealth(nameDataModel_publicHealth);
        publicSecurityDetail.setFireSafety(nameDataModel_fireSafety);
        return publicSecurityDetail;
    }

}
