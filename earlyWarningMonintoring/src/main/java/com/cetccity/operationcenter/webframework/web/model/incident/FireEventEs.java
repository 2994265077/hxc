package com.cetccity.operationcenter.webframework.web.model.incident;

import java.io.Serializable;
import java.util.Date;

public class FireEventEs implements Serializable {

    private String id;

    private String uuid;

    private String emergency_name;

    private String emergency_category;

    private String emergency_time;

    private String emergency_address;

    private String jd;

    private String wd;

    private String jd84;

    private String wd84;

    private String disposal_status;

    private String reporting_unit;

    private String emergency_grade;

    private String emergency_desc;

    private String on_site_liaison;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;

    private String reserved5;

    private String update_status;

    private String update_time;

    private String create_time;

    private String startRow;

    private String pageSize;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmergency_name() {
        return emergency_name;
    }

    public void setEmergency_name(String emergency_name) {
        this.emergency_name = emergency_name;
    }

    public String getEmergency_category() {
        return emergency_category;
    }

    public void setEmergency_category(String emergency_category) {
        this.emergency_category = emergency_category;
    }

    public String getEmergency_time() {
        return emergency_time;
    }

    public void setEmergency_time(String emergency_time) {
        this.emergency_time = emergency_time;
    }

    public String getEmergency_address() {
        return emergency_address;
    }

    public void setEmergency_address(String emergency_address) {
        this.emergency_address = emergency_address;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
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

    public String getReporting_unit() {
        return reporting_unit;
    }

    public void setReporting_unit(String reporting_unit) {
        this.reporting_unit = reporting_unit;
    }

    public String getEmergency_grade() {
        return emergency_grade;
    }

    public void setEmergency_grade(String emergency_grade) {
        this.emergency_grade = emergency_grade;
    }

    public String getEmergency_desc() {
        return emergency_desc;
    }

    public void setEmergency_desc(String emergency_desc) {
        this.emergency_desc = emergency_desc;
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

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStartRow() {
        return startRow;
    }

    public void setStartRow(String startRow) {
        this.startRow = startRow;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getDisposal_status() {
        return disposal_status;
    }

    public void setDisposal_status(String disposal_status) {
        this.disposal_status = disposal_status;
    }

    public String getOn_site_liaison() {
        return on_site_liaison;
    }

    public void setOn_site_liaison(String on_site_liaison) {
        this.on_site_liaison = on_site_liaison;
    }

    public String getUpdate_status() {
        return update_status;
    }

    public void setUpdate_status(String update_status) {
        this.update_status = update_status;
    }
}