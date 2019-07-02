package com.cetccity.operationcenter.webframework.web.model.incident.loadmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public class SHUIZHA_001 implements Serializable {
    private Integer id;

    private String uuid;
    @JsonIgnore
    private String szbh;

    private String szmc;

    private String ssqy;
    @JsonIgnore
    private String gcdj;
    @JsonIgnore
    private String szlx;
    @JsonIgnore
    private String zksl;
    @JsonIgnore
    private String zkzjk;
    @JsonIgnore
    private String gldwmc;
    @JsonIgnore
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

    public String getSzbh() {
        return szbh;
    }

    public void setSzbh(String szbh) {
        this.szbh = szbh == null ? null : szbh.trim();
    }

    public String getSzmc() {
        return szmc;
    }

    public void setSzmc(String szmc) {
        this.szmc = szmc == null ? null : szmc.trim();
    }

    public String getSsqy() {
        return ssqy;
    }

    public void setSsqy(String ssqy) {
        this.ssqy = ssqy == null ? null : ssqy.trim();
    }

    public String getGcdj() {
        return gcdj;
    }

    public void setGcdj(String gcdj) {
        this.gcdj = gcdj == null ? null : gcdj.trim();
    }

    public String getSzlx() {
        return szlx;
    }

    public void setSzlx(String szlx) {
        this.szlx = szlx == null ? null : szlx.trim();
    }

    public String getZksl() {
        return zksl;
    }

    public void setZksl(String zksl) {
        this.zksl = zksl == null ? null : zksl.trim();
    }

    public String getZkzjk() {
        return zkzjk;
    }

    public void setZkzjk(String zkzjk) {
        this.zkzjk = zkzjk == null ? null : zkzjk.trim();
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