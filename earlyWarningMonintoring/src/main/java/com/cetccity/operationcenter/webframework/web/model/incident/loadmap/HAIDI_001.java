package com.cetccity.operationcenter.webframework.web.model.incident.loadmap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;

public class HAIDI_001 implements Serializable {
    private Integer id;

    private String uuid;
    @JsonIgnore
    private String hdbh;

    private String hdmc;

    @JsonIgnore
    private String ssqy;

    @JsonIgnore
    private String dfcd;

    @JsonIgnore
    private String ghfhbzcd;

    @JsonIgnore
    private String szsl;

    @JsonIgnore
    private String ghsl;

    @JsonIgnore
    private String bzsl;

    @JsonIgnore
    private String dhxsl;

    @JsonIgnore
    private String gldwmc;

    private String dz;

    @JsonIgnore
    private String jd;

    @JsonIgnore
    private String wd;

    @JsonIgnore
    private String bz;

    @JsonIgnore
    private String jd84;

    @JsonIgnore
    private String wd84;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getHdbh() {
        return hdbh;
    }

    public void setHdbh(String hdbh) {
        this.hdbh = hdbh == null ? null : hdbh.trim();
    }

    public String getHdmc() {
        return hdmc;
    }

    public void setHdmc(String hdmc) {
        this.hdmc = hdmc == null ? null : hdmc.trim();
    }

    public String getSsqy() {
        return ssqy;
    }

    public void setSsqy(String ssqy) {
        this.ssqy = ssqy == null ? null : ssqy.trim();
    }

    public String getDfcd() {
        return dfcd;
    }

    public void setDfcd(String dfcd) {
        this.dfcd = dfcd == null ? null : dfcd.trim();
    }

    public String getGhfhbzcd() {
        return ghfhbzcd;
    }

    public void setGhfhbzcd(String ghfhbzcd) {
        this.ghfhbzcd = ghfhbzcd == null ? null : ghfhbzcd.trim();
    }

    public String getSzsl() {
        return szsl;
    }

    public void setSzsl(String szsl) {
        this.szsl = szsl == null ? null : szsl.trim();
    }

    public String getGhsl() {
        return ghsl;
    }

    public void setGhsl(String ghsl) {
        this.ghsl = ghsl == null ? null : ghsl.trim();
    }

    public String getBzsl() {
        return bzsl;
    }

    public void setBzsl(String bzsl) {
        this.bzsl = bzsl == null ? null : bzsl.trim();
    }

    public String getDhxsl() {
        return dhxsl;
    }

    public void setDhxsl(String dhxsl) {
        this.dhxsl = dhxsl == null ? null : dhxsl.trim();
    }

    public String getGldwmc() {
        return gldwmc;
    }

    public void setGldwmc(String gldwmc) {
        this.gldwmc = gldwmc == null ? null : gldwmc.trim();
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz == null ? null : dz.trim();
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

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
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