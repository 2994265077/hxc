package com.cetccity.operationcenter.webframework.web.model.incident.loadmap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;

public class HUAPODIAN_001 implements Serializable {

    private Integer id;

    private String uuid;

    private String hpdmc;
    @JsonIgnore
    private String hpdbh;
    @JsonIgnore
    private String szjd;
    @JsonIgnore
    private String pc;
    @JsonIgnore
    private String pg;
    @JsonIgnore
    private String pd;
    @JsonIgnore
    private String wxdx;
    @JsonIgnore
    private String wxrs;
    @JsonIgnore
    private String qzjjss;
    @JsonIgnore
    private String yhdj;
    @JsonIgnore
    private String jcyfzrdw;
    @JsonIgnore
    private String yflxr;
    @JsonIgnore
    private String lxdh;
    @JsonIgnore
    private String zlzrdw;
    @JsonIgnore
    private String hyjgbm;
    @JsonIgnore
    private String yfyqjfzdc;
    @JsonIgnore
    private String bplb;

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

    public String getHpdmc() {
        return hpdmc;
    }

    public void setHpdmc(String hpdmc) {
        this.hpdmc = hpdmc == null ? null : hpdmc.trim();
    }

    public String getHpdbh() {
        return hpdbh;
    }

    public void setHpdbh(String hpdbh) {
        this.hpdbh = hpdbh == null ? null : hpdbh.trim();
    }

    public String getSzjd() {
        return szjd;
    }

    public void setSzjd(String szjd) {
        this.szjd = szjd == null ? null : szjd.trim();
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc == null ? null : pc.trim();
    }

    public String getPg() {
        return pg;
    }

    public void setPg(String pg) {
        this.pg = pg == null ? null : pg.trim();
    }

    public String getPd() {
        return pd;
    }

    public void setPd(String pd) {
        this.pd = pd == null ? null : pd.trim();
    }

    public String getWxdx() {
        return wxdx;
    }

    public void setWxdx(String wxdx) {
        this.wxdx = wxdx == null ? null : wxdx.trim();
    }

    public String getWxrs() {
        return wxrs;
    }

    public void setWxrs(String wxrs) {
        this.wxrs = wxrs == null ? null : wxrs.trim();
    }

    public String getQzjjss() {
        return qzjjss;
    }

    public void setQzjjss(String qzjjss) {
        this.qzjjss = qzjjss == null ? null : qzjjss.trim();
    }

    public String getYhdj() {
        return yhdj;
    }

    public void setYhdj(String yhdj) {
        this.yhdj = yhdj == null ? null : yhdj.trim();
    }

    public String getJcyfzrdw() {
        return jcyfzrdw;
    }

    public void setJcyfzrdw(String jcyfzrdw) {
        this.jcyfzrdw = jcyfzrdw == null ? null : jcyfzrdw.trim();
    }

    public String getYflxr() {
        return yflxr;
    }

    public void setYflxr(String yflxr) {
        this.yflxr = yflxr == null ? null : yflxr.trim();
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh == null ? null : lxdh.trim();
    }

    public String getZlzrdw() {
        return zlzrdw;
    }

    public void setZlzrdw(String zlzrdw) {
        this.zlzrdw = zlzrdw == null ? null : zlzrdw.trim();
    }

    public String getHyjgbm() {
        return hyjgbm;
    }

    public void setHyjgbm(String hyjgbm) {
        this.hyjgbm = hyjgbm == null ? null : hyjgbm.trim();
    }

    public String getYfyqjfzdc() {
        return yfyqjfzdc;
    }

    public void setYfyqjfzdc(String yfyqjfzdc) {
        this.yfyqjfzdc = yfyqjfzdc == null ? null : yfyqjfzdc.trim();
    }

    public String getBplb() {
        return bplb;
    }

    public void setBplb(String bplb) {
        this.bplb = bplb == null ? null : bplb.trim();
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