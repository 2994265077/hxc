package com.cetccity.operationcenter.webframework.publichealth.dao;

import com.cetccity.operationcenter.webframework.publichealth.api.model.EMPLOYEE_NUM;
import com.cetccity.operationcenter.webframework.publichealth.dao.entity.YJJC_QWJJ_SDM_INFO_V;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface YJJC_QWJJ_SDM_INFO_VMapper {

    Integer selectCountOfToday(String time);

    List<YJJC_QWJJ_SDM_INFO_V> getYJJC_QWJJ_SDM_INFO_V(YJJC_QWJJ_SDM_INFO_V yJJC_QWJJ_SDM_INFO_V);

    List<EMPLOYEE_NUM> specialDiseasesTrend(Map trend);

    List<EMPLOYEE_NUM> specialDiseasesHospital(@Param("ORG_CODE") String ORG_CODE);

    List<EMPLOYEE_NUM> getDIAG_TYPE_NUM();

    List<EMPLOYEE_NUM> getDIAG_TYPE_CODE_NUM();
}
