package com.cetccity.operationcenter.webframework.hiddendanger.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * IOT_DEVICE
 * @author 
 */
public class IotDeviceModel implements Serializable {
    private BigDecimal id;

    private Object name;

    private Object deviceCode;

    private Object jd84;

    private Object wd84;

    private Object address;

    private Date createTime;

    private Date updateTime;

    private Object updateStatus;

    private Object uuid;

    private Object region;

    private Object reserved3;

    private Object reserved4;

    private Object reserved5;

    private Object street;

    private String deviceType;

    private Object jdsz;

    private Object wdsz;

    private String regionCode;

    private String streetCode;

    private String communityCode;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(Object deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Object getJd84() {
        return jd84;
    }

    public void setJd84(Object jd84) {
        this.jd84 = jd84;
    }

    public Object getWd84() {
        return wd84;
    }

    public void setWd84(Object wd84) {
        this.wd84 = wd84;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Object getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Object updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Object getUuid() {
        return uuid;
    }

    public void setUuid(Object uuid) {
        this.uuid = uuid;
    }

    public Object getRegion() {
        return region;
    }

    public void setRegion(Object region) {
        this.region = region;
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

    public Object getStreet() {
        return street;
    }

    public void setStreet(Object street) {
        this.street = street;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Object getJdsz() {
        return jdsz;
    }

    public void setJdsz(Object jdsz) {
        this.jdsz = jdsz;
    }

    public Object getWdsz() {
        return wdsz;
    }

    public void setWdsz(Object wdsz) {
        this.wdsz = wdsz;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
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
        IotDeviceModel other = (IotDeviceModel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDeviceCode() == null ? other.getDeviceCode() == null : this.getDeviceCode().equals(other.getDeviceCode()))
            && (this.getJd84() == null ? other.getJd84() == null : this.getJd84().equals(other.getJd84()))
            && (this.getWd84() == null ? other.getWd84() == null : this.getWd84().equals(other.getWd84()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateStatus() == null ? other.getUpdateStatus() == null : this.getUpdateStatus().equals(other.getUpdateStatus()))
            && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getReserved3() == null ? other.getReserved3() == null : this.getReserved3().equals(other.getReserved3()))
            && (this.getReserved4() == null ? other.getReserved4() == null : this.getReserved4().equals(other.getReserved4()))
            && (this.getReserved5() == null ? other.getReserved5() == null : this.getReserved5().equals(other.getReserved5()))
            && (this.getStreet() == null ? other.getStreet() == null : this.getStreet().equals(other.getStreet()))
            && (this.getDeviceType() == null ? other.getDeviceType() == null : this.getDeviceType().equals(other.getDeviceType()))
            && (this.getJdsz() == null ? other.getJdsz() == null : this.getJdsz().equals(other.getJdsz()))
            && (this.getWdsz() == null ? other.getWdsz() == null : this.getWdsz().equals(other.getWdsz()))
            && (this.getRegionCode() == null ? other.getRegionCode() == null : this.getRegionCode().equals(other.getRegionCode()))
            && (this.getStreetCode() == null ? other.getStreetCode() == null : this.getStreetCode().equals(other.getStreetCode()))
            && (this.getCommunityCode() == null ? other.getCommunityCode() == null : this.getCommunityCode().equals(other.getCommunityCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDeviceCode() == null) ? 0 : getDeviceCode().hashCode());
        result = prime * result + ((getJd84() == null) ? 0 : getJd84().hashCode());
        result = prime * result + ((getWd84() == null) ? 0 : getWd84().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateStatus() == null) ? 0 : getUpdateStatus().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getReserved3() == null) ? 0 : getReserved3().hashCode());
        result = prime * result + ((getReserved4() == null) ? 0 : getReserved4().hashCode());
        result = prime * result + ((getReserved5() == null) ? 0 : getReserved5().hashCode());
        result = prime * result + ((getStreet() == null) ? 0 : getStreet().hashCode());
        result = prime * result + ((getDeviceType() == null) ? 0 : getDeviceType().hashCode());
        result = prime * result + ((getJdsz() == null) ? 0 : getJdsz().hashCode());
        result = prime * result + ((getWdsz() == null) ? 0 : getWdsz().hashCode());
        result = prime * result + ((getRegionCode() == null) ? 0 : getRegionCode().hashCode());
        result = prime * result + ((getStreetCode() == null) ? 0 : getStreetCode().hashCode());
        result = prime * result + ((getCommunityCode() == null) ? 0 : getCommunityCode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", deviceCode=").append(deviceCode);
        sb.append(", jd84=").append(jd84);
        sb.append(", wd84=").append(wd84);
        sb.append(", address=").append(address);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStatus=").append(updateStatus);
        sb.append(", uuid=").append(uuid);
        sb.append(", region=").append(region);
        sb.append(", reserved3=").append(reserved3);
        sb.append(", reserved4=").append(reserved4);
        sb.append(", reserved5=").append(reserved5);
        sb.append(", street=").append(street);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", jdsz=").append(jdsz);
        sb.append(", wdsz=").append(wdsz);
        sb.append(", regionCode=").append(regionCode);
        sb.append(", streetCode=").append(streetCode);
        sb.append(", communityCode=").append(communityCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}