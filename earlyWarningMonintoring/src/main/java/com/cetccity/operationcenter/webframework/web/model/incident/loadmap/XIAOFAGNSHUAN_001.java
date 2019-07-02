package com.cetccity.operationcenter.webframework.web.model.incident.loadmap;

import java.io.Serializable;
import java.util.Date;

public class XIAOFAGNSHUAN_001 implements Serializable {
    private Integer id;

    private String uuid;

    private String xfsmc;

    private String xfsbh;

    private String gsyl;

    private String gdzj;

    private String szdz;

    private String jtms;

    private String jd;

    private String wd;

    private String bz;

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

    public String getXfsmc() {
        return xfsmc;
    }

    public void setXfsmc(String xfsmc) {
        this.xfsmc = xfsmc == null ? null : xfsmc.trim();
    }

    public String getXfsbh() {
        return xfsbh;
    }

    public void setXfsbh(String xfsbh) {
        this.xfsbh = xfsbh == null ? null : xfsbh.trim();
    }

    public String getGsyl() {
        return gsyl;
    }

    public void setGsyl(String gsyl) {
        this.gsyl = gsyl == null ? null : gsyl.trim();
    }

    public String getGdzj() {
        return gdzj;
    }

    public void setGdzj(String gdzj) {
        this.gdzj = gdzj == null ? null : gdzj.trim();
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