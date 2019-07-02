package com.cetccity.operationcenter.webframework.web.model.video;

import java.io.Serializable;
import java.util.Date;

/**
 * VIDEO_POLICE
 * @author 
 */
public class VideoPoliceModel implements Serializable {
    private String id;

    private String cameraid;

    private String district;

    private String gbCode;

    private String address;

    private String name;

    private String jd84;

    private String wd84;

    private String ip;

    private String startDate;

    private String state;

    private String record;

    private String category;

    private String appearance;

    private String resolution;

    private Date createTime;

    private Date updateTime;

    private String updateStatus;

    private String uuid;

    private String regionCode;

    private String streetCode;

    private String communityCode;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCameraid() {
        return cameraid;
    }

    public void setCameraid(String cameraid) {
        this.cameraid = cameraid;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getGbCode() {
        return gbCode;
    }

    public void setGbCode(String gbCode) {
        this.gbCode = gbCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJd84() {
        return jd84;
    }

    public void setJd84(String jd84) {
        this.jd84 = jd84;
    }

    public String getWd84() {
        return wd84;
    }

    public void setWd84(String wd84) {
        this.wd84 = wd84;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
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

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
        VideoPoliceModel other = (VideoPoliceModel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCameraid() == null ? other.getCameraid() == null : this.getCameraid().equals(other.getCameraid()))
            && (this.getDistrict() == null ? other.getDistrict() == null : this.getDistrict().equals(other.getDistrict()))
            && (this.getGbCode() == null ? other.getGbCode() == null : this.getGbCode().equals(other.getGbCode()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getJd84() == null ? other.getJd84() == null : this.getJd84().equals(other.getJd84()))
            && (this.getWd84() == null ? other.getWd84() == null : this.getWd84().equals(other.getWd84()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getRecord() == null ? other.getRecord() == null : this.getRecord().equals(other.getRecord()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()))
            && (this.getAppearance() == null ? other.getAppearance() == null : this.getAppearance().equals(other.getAppearance()))
            && (this.getResolution() == null ? other.getResolution() == null : this.getResolution().equals(other.getResolution()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateStatus() == null ? other.getUpdateStatus() == null : this.getUpdateStatus().equals(other.getUpdateStatus()))
            && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getRegionCode() == null ? other.getRegionCode() == null : this.getRegionCode().equals(other.getRegionCode()))
            && (this.getStreetCode() == null ? other.getStreetCode() == null : this.getStreetCode().equals(other.getStreetCode()))
            && (this.getCommunityCode() == null ? other.getCommunityCode() == null : this.getCommunityCode().equals(other.getCommunityCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCameraid() == null) ? 0 : getCameraid().hashCode());
        result = prime * result + ((getDistrict() == null) ? 0 : getDistrict().hashCode());
        result = prime * result + ((getGbCode() == null) ? 0 : getGbCode().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getJd84() == null) ? 0 : getJd84().hashCode());
        result = prime * result + ((getWd84() == null) ? 0 : getWd84().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getRecord() == null) ? 0 : getRecord().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        result = prime * result + ((getAppearance() == null) ? 0 : getAppearance().hashCode());
        result = prime * result + ((getResolution() == null) ? 0 : getResolution().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateStatus() == null) ? 0 : getUpdateStatus().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
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
        sb.append(", cameraid=").append(cameraid);
        sb.append(", district=").append(district);
        sb.append(", gbCode=").append(gbCode);
        sb.append(", address=").append(address);
        sb.append(", name=").append(name);
        sb.append(", jd84=").append(jd84);
        sb.append(", wd84=").append(wd84);
        sb.append(", ip=").append(ip);
        sb.append(", startDate=").append(startDate);
        sb.append(", state=").append(state);
        sb.append(", record=").append(record);
        sb.append(", category=").append(category);
        sb.append(", appearance=").append(appearance);
        sb.append(", resolution=").append(resolution);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateStatus=").append(updateStatus);
        sb.append(", uuid=").append(uuid);
        sb.append(", regionCode=").append(regionCode);
        sb.append(", streetCode=").append(streetCode);
        sb.append(", communityCode=").append(communityCode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}