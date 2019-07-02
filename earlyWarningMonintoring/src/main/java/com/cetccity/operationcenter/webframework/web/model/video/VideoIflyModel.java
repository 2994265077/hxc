package com.cetccity.operationcenter.webframework.web.model.video;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * VIDEO_IFLY
 * @author 
 */
public class VideoIflyModel implements Serializable {
    private BigDecimal id;

    private String ifly1;

    private String ifly2;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getIfly1() {
        return ifly1;
    }

    public void setIfly1(String ifly1) {
        this.ifly1 = ifly1;
    }

    public String getIfly2() {
        return ifly2;
    }

    public void setIfly2(String ifly2) {
        this.ifly2 = ifly2;
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
        VideoIflyModel other = (VideoIflyModel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIfly1() == null ? other.getIfly1() == null : this.getIfly1().equals(other.getIfly1()))
            && (this.getIfly2() == null ? other.getIfly2() == null : this.getIfly2().equals(other.getIfly2()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIfly1() == null) ? 0 : getIfly1().hashCode());
        result = prime * result + ((getIfly2() == null) ? 0 : getIfly2().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ifly1=").append(ifly1);
        sb.append(", ifly2=").append(ifly2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}