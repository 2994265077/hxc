package com.cetccity.operationcenter.webframework.trigger.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 卫计_特殊病种监控表
 * </p>
 *
 * @author jobob
 * @since 2019-03-03
 */
@Data
public class YjjcQwjjSdmInfoV implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 年龄
     */

    private String AGE;

    /**
     * 出生日期时间
     */

    private String DATE_OF_BIRTH;

    /**
     * 诊断科室编码(填机构内部编码，需要设置维护字典数据)
     */

    private String DEPT_CODE;

    /**
     * 诊断科室名称
     */

    private String DEPT_NAME;

    /**
     * 机构内部诊断编码(填机构内部编码，需要设置维护字典对照表，无值时，填“/”)
     */

    private String DIAG_CODE_INHOS;

    /**
     * 机构内部诊断名称(填机构内部名称，需要设置维护字典对照表，无值时，填“/”)
     */

    private String DIAG_NAME_INHOS;

    /**
     * 诊断流水号(标识一条诊断记录)
     */

    private String DIAG_SNO;

    /**
     * 诊断时间
     */

    private Date DIAG_TIME;

    /**
     * 特殊病种类别
     */

    private String DIAG_TYPE;

    /**
     * 诊断类型-编码
     */

    private String DIAG_TYPE_CODE;

    /**
     * 医生-编码(填机构内部编码，需要设置维护字典数据)
     */

    private String DOCTOR_CODE;

    /**
     * 医生-姓名
     */

    private String DOCTOR_NAME;

    /**
     * 标识
     */

    private String ID;

    /**
     * POS最后编辑时间?
     */

    private String LAST_EDITED_TIME;

    /**
     * 组织机构-编码
     */

    private String ORG_CODE;

    /**
     * 组织机构-名称
     */

    private String ORG_NAME;

    /**
     * 门诊流水号(用于门诊接诊表关联的外键)
     */

    private String OUT_SNO;

    /**
     * 性别-编码
     */

    private String SEX_CODE;

    /**
     * 采集时间
     */
    private String UPLOAD_TIME;
    /**
     * 更新时间
     */
    private String YXY_UPDATEDTIME;
    /**
     * 更新时间
     */
    private String ADQ_UPDATE_TIME;
    /**
     * 区代码
     */
    private String REGION_CODE;
    /**
     * 街道代码
     */
    private String STREET_CODE;
    /**
     * 社区代码
     */
    private String COMMUNITY_CODE;
    /**
     * WGS84经度
     */
    private String JD84;
    /**
     * WGS84纬度
     */
    private String WD84;
    /**
     * 地址
     */
    private String ADDRESS;
    /**
     * 序列自增id
     */
    private Double OBJECT_ID;
    /**
     * 街道名称
     */
    private String STREET_NAME;
    /**
     * 社区名称
     */
    private String COMMUNITY_NAME;

    private Date YJJC_CREATE_TIME;

    private Date YJJC_UPDATE_TIME;


}
