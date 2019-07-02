package com.cetccity.operationcenter.webframework.web.model.iot;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * IOT_EVENT
 * @author
 */
public class IotEventModel implements Serializable {
    private BigDecimal id;

    private Object eventId;

    private Object eventCode;

    private Object eventName;

    private Object eventLevel;

    private Object eventState;

    private Date produceTime;

    private Date endTime;

    private Date updateTime;

    private Object updateState;

    private Object uuid;

    private Object deviceCode;

    private Object dataCode;

    private Object dataValue;

    private Object paraCode;

    private Object paraValue;

    private String isProcessed;

    private String processor;

    private String processUnit;

    private String processSystem;

    private Date processTime;

    private String processWay;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;

    private String reserved5;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Object getEventId() {
        return eventId;
    }

    public void setEventId(Object eventId) {
        this.eventId = eventId;
    }

    public Object getEventCode() {
        return eventCode;
    }

    public void setEventCode(Object eventCode) {
        this.eventCode = eventCode;
    }

    public Object getEventName() {
        return eventName;
    }

    public void setEventName(Object eventName) {
        this.eventName = eventName;
    }

    public Object getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(Object eventLevel) {
        this.eventLevel = eventLevel;
    }

    public Object getEventState() {
        return eventState;
    }

    public void setEventState(Object eventState) {
        this.eventState = eventState;
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Object getUpdateState() {
        return updateState;
    }

    public void setUpdateState(Object updateState) {
        this.updateState = updateState;
    }

    public Object getUuid() {
        return uuid;
    }

    public void setUuid(Object uuid) {
        this.uuid = uuid;
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

    public Object getParaCode() {
        return paraCode;
    }

    public void setParaCode(Object paraCode) {
        this.paraCode = paraCode;
    }

    public Object getParaValue() {
        return paraValue;
    }

    public void setParaValue(Object paraValue) {
        this.paraValue = paraValue;
    }

    public String getIsProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(String isProcessed) {
        this.isProcessed = isProcessed;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getProcessUnit() {
        return processUnit;
    }

    public void setProcessUnit(String processUnit) {
        this.processUnit = processUnit;
    }

    public String getProcessSystem() {
        return processSystem;
    }

    public void setProcessSystem(String processSystem) {
        this.processSystem = processSystem;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public String getProcessWay() {
        return processWay;
    }

    public void setProcessWay(String processWay) {
        this.processWay = processWay;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public String getReserved4() {
        return reserved4;
    }

    public void setReserved4(String reserved4) {
        this.reserved4 = reserved4;
    }

    public String getReserved5() {
        return reserved5;
    }

    public void setReserved5(String reserved5) {
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
        IotEventModel other = (IotEventModel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getEventId() == null ? other.getEventId() == null : this.getEventId().equals(other.getEventId()))
                && (this.getEventCode() == null ? other.getEventCode() == null : this.getEventCode().equals(other.getEventCode()))
                && (this.getEventName() == null ? other.getEventName() == null : this.getEventName().equals(other.getEventName()))
                && (this.getEventLevel() == null ? other.getEventLevel() == null : this.getEventLevel().equals(other.getEventLevel()))
                && (this.getEventState() == null ? other.getEventState() == null : this.getEventState().equals(other.getEventState()))
                && (this.getProduceTime() == null ? other.getProduceTime() == null : this.getProduceTime().equals(other.getProduceTime()))
                && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getUpdateState() == null ? other.getUpdateState() == null : this.getUpdateState().equals(other.getUpdateState()))
                && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
                && (this.getDeviceCode() == null ? other.getDeviceCode() == null : this.getDeviceCode().equals(other.getDeviceCode()))
                && (this.getDataCode() == null ? other.getDataCode() == null : this.getDataCode().equals(other.getDataCode()))
                && (this.getDataValue() == null ? other.getDataValue() == null : this.getDataValue().equals(other.getDataValue()))
                && (this.getParaCode() == null ? other.getParaCode() == null : this.getParaCode().equals(other.getParaCode()))
                && (this.getParaValue() == null ? other.getParaValue() == null : this.getParaValue().equals(other.getParaValue()))
                && (this.getIsProcessed() == null ? other.getIsProcessed() == null : this.getIsProcessed().equals(other.getIsProcessed()))
                && (this.getProcessor() == null ? other.getProcessor() == null : this.getProcessor().equals(other.getProcessor()))
                && (this.getProcessUnit() == null ? other.getProcessUnit() == null : this.getProcessUnit().equals(other.getProcessUnit()))
                && (this.getProcessSystem() == null ? other.getProcessSystem() == null : this.getProcessSystem().equals(other.getProcessSystem()))
                && (this.getProcessTime() == null ? other.getProcessTime() == null : this.getProcessTime().equals(other.getProcessTime()))
                && (this.getProcessWay() == null ? other.getProcessWay() == null : this.getProcessWay().equals(other.getProcessWay()))
                && (this.getReserved1() == null ? other.getReserved1() == null : this.getReserved1().equals(other.getReserved1()))
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
        result = prime * result + ((getEventId() == null) ? 0 : getEventId().hashCode());
        result = prime * result + ((getEventCode() == null) ? 0 : getEventCode().hashCode());
        result = prime * result + ((getEventName() == null) ? 0 : getEventName().hashCode());
        result = prime * result + ((getEventLevel() == null) ? 0 : getEventLevel().hashCode());
        result = prime * result + ((getEventState() == null) ? 0 : getEventState().hashCode());
        result = prime * result + ((getProduceTime() == null) ? 0 : getProduceTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateState() == null) ? 0 : getUpdateState().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getDeviceCode() == null) ? 0 : getDeviceCode().hashCode());
        result = prime * result + ((getDataCode() == null) ? 0 : getDataCode().hashCode());
        result = prime * result + ((getDataValue() == null) ? 0 : getDataValue().hashCode());
        result = prime * result + ((getParaCode() == null) ? 0 : getParaCode().hashCode());
        result = prime * result + ((getParaValue() == null) ? 0 : getParaValue().hashCode());
        result = prime * result + ((getIsProcessed() == null) ? 0 : getIsProcessed().hashCode());
        result = prime * result + ((getProcessor() == null) ? 0 : getProcessor().hashCode());
        result = prime * result + ((getProcessUnit() == null) ? 0 : getProcessUnit().hashCode());
        result = prime * result + ((getProcessSystem() == null) ? 0 : getProcessSystem().hashCode());
        result = prime * result + ((getProcessTime() == null) ? 0 : getProcessTime().hashCode());
        result = prime * result + ((getProcessWay() == null) ? 0 : getProcessWay().hashCode());
        result = prime * result + ((getReserved1() == null) ? 0 : getReserved1().hashCode());
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
        sb.append(", eventId=").append(eventId);
        sb.append(", eventCode=").append(eventCode);
        sb.append(", eventName=").append(eventName);
        sb.append(", eventLevel=").append(eventLevel);
        sb.append(", eventState=").append(eventState);
        sb.append(", produceTime=").append(produceTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateState=").append(updateState);
        sb.append(", uuid=").append(uuid);
        sb.append(", deviceCode=").append(deviceCode);
        sb.append(", dataCode=").append(dataCode);
        sb.append(", dataValue=").append(dataValue);
        sb.append(", paraCode=").append(paraCode);
        sb.append(", paraValue=").append(paraValue);
        sb.append(", isProcessed=").append(isProcessed);
        sb.append(", processor=").append(processor);
        sb.append(", processUnit=").append(processUnit);
        sb.append(", processSystem=").append(processSystem);
        sb.append(", processTime=").append(processTime);
        sb.append(", processWay=").append(processWay);
        sb.append(", reserved1=").append(reserved1);
        sb.append(", reserved2=").append(reserved2);
        sb.append(", reserved3=").append(reserved3);
        sb.append(", reserved4=").append(reserved4);
        sb.append(", reserved5=").append(reserved5);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}