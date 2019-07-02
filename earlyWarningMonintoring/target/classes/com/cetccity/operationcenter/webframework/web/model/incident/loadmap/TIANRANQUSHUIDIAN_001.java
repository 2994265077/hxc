package com.cetccity.operationcenter.webframework.web.model.incident.loadmap;

import java.io.Serializable;
import java.util.Date;

public class TIANRANQUSHUIDIAN_001 implements Serializable {
    private Integer id;

    private String uuid;

    private String qsdmc;

    private String qsdbh;

    private String qstcws;

    private String qsdjlsmgd;

    private String szdz;

    private String jtms;

    private String jd;

    private String wd;

    private String bz;

    private String jd84;

    private String wd84;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;

    private String reserved5;

    private String updateStatus;

    private Date updateTime;

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

    public String getQsdmc() {
        return qsdmc;
    }

    public void setQsdmc(String qsdmc) {
        this.qsdmc = qsdmc == null ? null : qsdmc.trim();
    }

    public String getQsdbh() {
        return qsdbh;
    }

    public void setQsdbh(String qsdbh) {
        this.qsdbh = qsdbh == null ? null : qsdbh.trim();
    }

    public String getQstcws() {
        return qstcws;
    }

    public void setQstcws(String qstcws) {
        this.qstcws = qstcws == null ? null : qstcws.trim();
    }

    public String getQsdjlsmgd() {
        return qsdjlsmgd;
    }

    public void setQsdjlsmgd(String qsdjlsmgd) {
        this.qsdjlsmgd = qsdjlsmgd == null ? null : qsdjlsmgd.trim();
    }

    public String getSzdz() {
        return szdz;
    }

    public void setSzdz(String szdz) {
        this.szdz = szdz == null ? null : szdz.trim();
    }

    public String getJtms() {
        return jtms;
    }

    public void setJtms(String jtms) {
        this.jtms = jtms == null ? null : jtms.trim();
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