package com.cetccity.operationcenter.webframework.web.model.incident;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public class DisposalProcess implements Serializable {

    private Integer id;
    @JsonIgnore
    private String emergencyId;
    @JsonIgnore
    private String uuid;

    private String disposalRecord;

    private String disposalTime;

    private String disposalStatus;
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

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmergencyId() {
        return emergencyId;
    }

    public void setEmergencyId(String emergencyId) {
        this.emergencyId = emergencyId == null ? null : emergencyId.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getDisposalRecord() {
        return disposalRecord;
    }

    public void setDisposalRecord(String disposalRecord) {
        this.disposalRecord = disposalRecord == null ? null : disposalRecord.trim();
    }

    public String getDisposalTime() {
        return disposalTime;
    }

    public void setDisposalTime(String disposalTime) {
        this.disposalTime = disposalTime == null ? null : disposalTime.trim();
    }

    public String getDisposalStatus() {
        return disposalStatus;
    }

    public void setDisposalStatus(String disposalStatus) {
        this.disposalStatus = disposalStatus == null ? null : disposalStatus.trim();
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