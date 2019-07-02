package com.cetccity.operationcenter.webframework.hiddendanger.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * IOT_SENSORDATA
 * @author 
 */
public class IotSensordataModel implements Serializable {
    private BigDecimal id;

    private Object deviceCode;

    private Object dataCode;

    private Object dataValue;

    private Object createTime;

    private Object updateTime;

    private Object updateStatus;

    private Object reserved2;

    private Object reserved3;

    private Object reserved4;

    private Object reserved5;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Object getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(Object deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Object getDataCode() {
        return dataCode;
    }

    public void setDataCode(Object dataCode) {
        this.dataCode = dataCode;
    }

    public Object getDataValue() {
        return dataValue;
    }

    public void setDataValue(Object dataValue) {
        this.dataValue = dataValue;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Object updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Object getReserved2() {
        return reserved2;
    }

    public void setReserved2(Object reserved2) {
        this.reserved2 = reserved2;
    }

    public Object getReserved3() {
        return reserved3;
    }

    public void setReserved3(Object reserved3) {
        this.reserved3 = reserved3;
    }

    public Object getReserved4() {
        return reserved4;
    }

    public void setReserved4(Object reserved4) {
        this.reserved4 = reserved4;
    }

    public Object getReserved5() {
        return reserved5;
    }

    public void setReserved5(Object reserved5) {
        this.reserved5 = reserved5;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        IotSensordataModel other = (IotSensordataModel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getDeviceCode() == null ? other.getDeviceCode() == null : this.getDeviceCode().equals(other.getDeviceCode()))
            && (this.getDataCode() == null ? other.getDataCode() == null : this.getDataCode().equals(other.getDataCode()))
            && (this.getDataValue() == null ? other.getDataValue() == null : this.getDataValue().equals(other.getDataValue()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateStatus() == null ? other.getUpdateStatus() == null : this.getUpdateStatus().equals(other.getUpdateStatus()))
            && (this.getReserved2() == null ? other.getReserved2() == null : this.getReserved2().equals(other.getReserved2()))
            && (this.getReserved3() == null ? other.getReserved3() == null : this.getReserved3().equals(other.getReserved3()))
            && (this.getReserved4() == null ? other.getReserved4() == null : this.getReserved4().equals(other.getReserved4()))
            && (this.getReserved5() == null ? other.getReserved5() == null : this.getReserved5().equals(other.getReserved5()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDeviceCode() == null) ? 0 : getDeviceCode().hashCode());
        result = prime * result + ((getDataCode() == null) ? 0 : getDataCode().hashCode());
        result = prime * result + ((getDataValue() == null) ? 0 : getDataValue().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateStatus() == null) ? 0 : getUpdateStatus().hashCode());
        result = prime * result + ((getReserved2() == null) ? 0 : getReserved2().hashCode());
        result = prime * result + ((getReserved3() == null) ? 0 : getReserved3().hashCode());
        result = prime * result + ((getReserved4() == null) ? 0 : getReserved4().hashCode());
        result = prime * result + ((getReserved5() == null) ? 0 : getReserved5().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deviceCode=").append(deviceCode);
        sb.append(", dataCode=").append(dataCode);
        sb.append(", dataValue=").append(dataValue);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStatus=").append(updateStatus);
        sb.append(", reserved2=").append(reserved2);
        sb.append(", reserved3=").append(reserved3);
        sb.append(", reserved4=").append(reserved4);
        sb.append(", reserved5=").append(reserved5);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}