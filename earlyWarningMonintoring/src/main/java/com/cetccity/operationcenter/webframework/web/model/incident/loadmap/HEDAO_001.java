package com.cetccity.operationcenter.webframework.web.model.incident.loadmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public class HEDAO_001 implements Serializable {
    private Integer id;

    private String uuid;
    @JsonIgnore
    private String hdbh;

    private String hdmc;
    @JsonIgnore
    private String ssqy;

    private String ssly;
    @JsonIgnore
    private String lymj;
    @JsonIgnore
    private String hdfhbz;
    @JsonIgnore
    private String hdcd;
    @JsonIgnore
    private String zdlykd;
    @JsonIgnore
    private String symj;
    @JsonIgnore
    private String ldmj;
    @JsonIgnore
    private String djsl;
    @JsonIgnore
    private String lxmj;
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

    public String getSsly() {
        return ssly;
    }

    public void setSsly(String ssly) {
        this.ssly = ssly == null ? null : ssly.trim();
    }

    public String getLymj() {
        return lymj;
    }

    public void setLymj(String lymj) {
        this.lymj = lymj == null ? null : lymj.trim();
    }

    public String getHdfhbz() {
        return hdfhbz;
    }

    public void setHdfhbz(String hdfhbz) {
        this.hdfhbz = hdfhbz == null ? null : hdfhbz.trim();
    }

    public String getHdcd() {
        return hdcd;
    }

    public void setHdcd(String hdcd) {
        this.hdcd = hdcd == null ? null : hdcd.trim();
    }

    public String getZdlykd() {
        return zdlykd;
    }

    public void setZdlykd(String zdlykd) {
        this.zdlykd = zdlykd == null ? null : zdlykd.trim();
    }

    public String getSymj() {
        return symj;
    }

    public void setSymj(String symj) {
        this.symj = symj == null ? null : symj.trim();
    }

    public String getLdmj() {
        return ldmj;
    }

    public void setLdmj(String ldmj) {
        this.ldmj = ldmj == null ? null : ldmj.trim();
    }

    public String getDjsl() {
        return djsl;
    }

    public void setDjsl(String djsl) {
        this.djsl = djsl == null ? null : djsl.trim();
    }

    public String getLxmj() {
        return lxmj;
    }

    public void setLxmj(String lxmj) {
        this.lxmj = lxmj == null ? null : lxmj.trim();
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