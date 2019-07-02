package com.cetccity.operationcenter.webframework.trigger.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * 卫计_单位信息表
 * </p>
 *
 * @author jobob
 * @since 2019-03-03
 */
@Data
public class YjjcQwjjOrgV implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 床位数
     */

    private String BED;

    /**
     * 科室-编码
     */

    private String DEPT_CODE;

    /**
     * 科室-名称
     */

    private String DEPT_NAME;

    /**
     * 空闲床位数
     */

    private String FREE_BED;

    /**
     * 举办主体
     */

    private String HOST_NAME;

    /**
     * 唯一标识  
     */

    private String ID;

    /**
     * 编制床位数
     */

    private String ORG_BED_NUM;

    /**
     * 组织机构-编码
     */

    private String ORG_CODE;

    /**
     * 医院等级(3=三级;30=三级特等;31=三级甲等;32=三级乙等;33=三级丙等;2=二级;21=二级甲等;22=二级乙等;23=二级丙等;1=一级;11=一级甲等，12=一级乙等，13=一级丙等;99=其他)  
     */

    private String ORG_LEVEL;

    /**
     * 组织机构-名称
     */

    private String ORG_NAME;

    /**
     * 机构分类
     */

    private String ORG_TYPE;

    /**
     * 机构类型
     */

    private String ORG_TYPE_N;

    /**
     * 记录创建时间
     */

    private String REC_CREATE_TIME;

    /**
     * 记录最后修改时间
     */

    private String REC_MODIFY_TIME;

    /**
     * 更新时间-YXY
     */

    private String YXY_UPDATEDTIME;

    /**
     * 更新时间-ADQ
     */

    private String ADQ_UPDATE_TIME;

    /**
     * 84经度
     */

    private String JD84;

    /**
     * 84纬度
     */

    private String WD84;

    /**
     * 街道
     */

    private String STREET;

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


}
