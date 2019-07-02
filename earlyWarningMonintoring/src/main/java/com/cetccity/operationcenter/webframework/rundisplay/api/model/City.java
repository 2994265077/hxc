package com.cetccity.operationcenter.webframework.rundisplay.api.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("d")
public class City {
    @XStreamAsAttribute
    @XStreamAlias("d1")
    private String cityId;

    @XStreamAsAttribute
    @XStreamAlias("d2")
    private String cityName;

    @XStreamAlias("d3")
    @XStreamAsAttribute
    private String cityCode;

    @XStreamAsAttribute
    @XStreamAlias("d4")
    private String province;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}



