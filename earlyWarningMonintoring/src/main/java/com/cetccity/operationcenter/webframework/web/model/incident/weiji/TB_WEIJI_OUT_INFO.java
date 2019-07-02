package com.cetccity.operationcenter.webframework.web.model.incident.weiji;

import java.io.Serializable;
import java.util.Date;

public class TB_WEIJI_OUT_INFO implements Serializable {
    private Integer id;

    private String uuid;

    private String orgCode;

    private String orgName;

    private String deptCode;

    private String deptName;

    private Integer registerNo;

    private Integer visitNo;

    private Integer waitingNo;

    private String recCreateTime;

    private String recModifyTime;

    private String city;

    private String region;

    private String street;

    private String community;

    private String grid;

    private String address;

    private String jd84;

    private String wd84;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;

    private String reserved5;

    private String updateStatus;

    private Date updateTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Integer getRegisterNo() {
        return registerNo;
    }

    public void setRegisterNo(Integer registerNo) {
        this.registerNo = registerNo;
    }

    public Integer getVisitNo() {
        return visitNo;
    }

    public void setVisitNo(Integer visitNo) {
        this.visitNo = visitNo;
    }

    public Integer getWaitingNo() {
        return waitingNo;
    }

    public void setWaitingNo(Integer waitingNo) {
        this.waitingNo = waitingNo;
    }

    public String getRecCreateTime() {
        return recCreateTime;
    }

    public void setRecCreateTime(String recCreateTime) {
        this.recCreateTime = recCreateTime == null ? null : recCreateTime.trim();
    }

    public String getRecModifyTime() {
        return recModifyTime;
    }

    public void setRecModifyTime(String recModifyTime) {
        this.recModifyTime = recModifyTime == null ? null : recModifyTime.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community == null ? null : community.trim();
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid == null ? null : grid.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getJd84() {
        return jd84;
    }

    public void setJd84(String jd84) {
        this.jd84 = jd84 == null ? null : jd84.trim();
    }

    public String getWd84() {
        return wd84;
    }

    public void setWd84(String wd84) {
        this.wd84 = wd84 == null ? null : wd84.trim();
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3 == null ? null : reserved3.trim();
    }

    public String getReserved4() {
        return reserved4;
    }

    public void setReserved4(String reserved4) {
        this.reserved4 = reserved4 == null ? null : reserved4.trim();
    }

    public String getReserved5() {
        return reserved5;
    }

    public void setReserved5(String reserved5) {
        this.reserved5 = reserved5 == null ? null : reserved5.trim();
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus == null ? null : updateStatus.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}