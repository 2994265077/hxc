package com.cetccity.operationcenter.webframework.hiddendanger.dao.entity;

import java.io.Serializable;

/**
 * IOT_EVENT
 * @author
 */
public class IotEventModelWithBLOBs extends IotEventModel implements Serializable {
    private String eventDesc;

    private String duration;

    private static final long serialVersionUID = 1L;

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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
        IotEventModelWithBLOBs other = (IotEventModelWithBLOBs) that;
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
                && (this.getReserved5() == null ? other.getReserved5() == null : this.getReserved5().equals(other.getReserved5()))
                && (this.getEventDesc() == null ? other.getEventDesc() == null : this.getEventDesc().equals(other.getEventDesc()))
                && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()));
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
        result = prime * result + ((getEventDesc() == null) ? 0 : getEventDesc().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", eventDesc=").append(eventDesc);
        sb.append(", duration=").append(duration);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}