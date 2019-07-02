package com.cetccity.operationcenter.webframework.web.model.incident;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public class FireEvent implements Serializable {
    private Integer id;

    private String uuid;

    private String emergencyName;

    private String emergencyCategory;

    private String emergencyTime;

    private String emergencyAddress;

    private String jd;

    private String wd;

    private String jd84;

    private String wd84;

    private String disposalStatus;

    private String reportingUnit;

    private String emergencyGrade;

    private String emergencyDesc;

    private String onSiteLiaison;
    @JsonIgnore
    private String reserved1;
    @JsonIgnore
    private String reserved2;
    @JsonIgnore
    private String reserved3;
    @JsonIgnore
    private String reserved4;
    @JsonIgnore
    private String reserved5;
    @JsonIgnore
    private String updateStatus;
    @JsonIgnore
    private Date updateTime;
    @JsonIgnore
    private Date createTime;

    private String startRow;

    private String pageSize;

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

    public String getEmergencyName() {
        return emergencyName;
    }

    public void setEmergencyName(String emergencyName) {
        this.emergencyName = emergencyName == null ? null : emergencyName.trim();
    }

    public String getEmergencyCategory() {
        return emergencyCategory;
    }

    public void setEmergencyCategory(String emergencyCategory) {
        this.emergencyCategory = emergencyCategory == null ? null : emergencyCategory.trim();
    }

    public String getEmergencyTime() {
        return emergencyTime;
    }

    public void setEmergencyTime(String emergencyTime) {
        this.emergencyTime = emergencyTime == null ? null : emergencyTime.trim();
    }

    public String getEmergencyAddress() {
        return emergencyAddress;
    }

    public void setEmergencyAddress(String emergencyAddress) {
        this.emergencyAddress = emergencyAddress == null ? null : emergencyAddress.trim();
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd == null ? null : jd.trim();
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd == null ? null : wd.trim();
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

    public String getDisposalStatus() {
        return disposalStatus;
    }

    public void setDisposalStatus(String disposalStatus) {
        this.disposalStatus = disposalStatus;
    }

    public String getReportingUnit() {
        return reportingUnit;
    }

    public void setReportingUnit(String reportingUnit) {
        this.reportingUnit = reportingUnit == null ? null : reportingUnit.trim();
    }

    public String getEmergencyGrade() {
        return emergencyGrade;
    }

    public void setEmergencyGrade(String emergencyGrade) {
        this.emergencyGrade = emergencyGrade == null ? null : emergencyGrade.trim();
    }

    public String getEmergencyDesc() {
        return emergencyDesc;
    }

    public void setEmergencyDesc(String emergencyDesc) {
        this.emergencyDesc = emergencyDesc == null ? null : emergencyDesc.trim();
    }

    public String getOnSiteLiaison() {
        return onSiteLiaison;
    }

    public void setOnSiteLiaison(String onSiteLiaison) {
        this.onSiteLiaison = onSiteLiaison == null ? null : onSiteLiaison.trim();
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

    public String getStartRow() {
        return startRow;
    }

    public void setStartRow(String startRow) {
        this.startRow = startRow;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }
}