package com.cetccity.operationcenter.webframework.web.dao.hidden_danger;

import com.cetccity.operationcenter.webframework.web.model.hidden_danger.QXFJ_BUILD_INFO_V;
import com.cetccity.operationcenter.webframework.web.model.hidden_danger.QXFJ_BUILD_INFO_VExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QXFJ_BUILD_INFO_VMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZHFTYJJCPT.qxfj_build_info_v
     *
     * @mbg.generated Fri Sep 07 14:42:21 CST 2018
     */
    long countByExample(QXFJ_BUILD_INFO_VExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZHFTYJJCPT.qxfj_build_info_v
     *
     * @mbg.generated Fri Sep 07 14:42:21 CST 2018
     */
    int deleteByExample(QXFJ_BUILD_INFO_VExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZHFTYJJCPT.qxfj_build_info_v
     *
     * @mbg.generated Fri Sep 07 14:42:21 CST 2018
     */
    int insert(QXFJ_BUILD_INFO_V record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZHFTYJJCPT.qxfj_build_info_v
     *
     * @mbg.generated Fri Sep 07 14:42:21 CST 2018
     */
    int insertSelective(QXFJ_BUILD_INFO_V record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZHFTYJJCPT.qxfj_build_info_v
     *
     * @mbg.generated Fri Sep 07 14:42:21 CST 2018
     */
    List<QXFJ_BUILD_INFO_V> selectByExample(QXFJ_BUILD_INFO_VExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZHFTYJJCPT.qxfj_build_info_v
     *
     * @mbg.generated Fri Sep 07 14:42:21 CST 2018
     */
    int updateByExampleSelective(@Param("record") QXFJ_BUILD_INFO_V record, @Param("example") QXFJ_BUILD_INFO_VExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ZHFTYJJCPT.qxfj_build_info_v
     *
     * @mbg.generated Fri Sep 07 14:42:21 CST 2018
     */
    int updateByExample(@Param("record") QXFJ_BUILD_INFO_V record, @Param("example") QXFJ_BUILD_INFO_VExample example);
}