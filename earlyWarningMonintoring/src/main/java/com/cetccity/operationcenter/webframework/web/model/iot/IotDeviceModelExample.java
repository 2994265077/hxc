package com.cetccity.operationcenter.webframework.web.model.iot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class IotDeviceModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IotDeviceModelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(BigDecimal value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigDecimal value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigDecimal value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigDecimal value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigDecimal> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigDecimal> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`NAME` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`NAME` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(Object value) {
            addCriterion("`NAME` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(Object value) {
            addCriterion("`NAME` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(Object value) {
            addCriterion("`NAME` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(Object value) {
            addCriterion("`NAME` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(Object value) {
            addCriterion("`NAME` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(Object value) {
            addCriterion("`NAME` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<Object> values) {
            addCriterion("`NAME` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<Object> values) {
            addCriterion("`NAME` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(Object value1, Object value2) {
            addCriterion("`NAME` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(Object value1, Object value2) {
            addCriterion("`NAME` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIsNull() {
            addCriterion("DEVICE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIsNotNull() {
            addCriterion("DEVICE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeEqualTo(Object value) {
            addCriterion("DEVICE_CODE =", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotEqualTo(Object value) {
            addCriterion("DEVICE_CODE <>", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeGreaterThan(Object value) {
            addCriterion("DEVICE_CODE >", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeGreaterThanOrEqualTo(Object value) {
            addCriterion("DEVICE_CODE >=", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLessThan(Object value) {
            addCriterion("DEVICE_CODE <", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLessThanOrEqualTo(Object value) {
            addCriterion("DEVICE_CODE <=", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIn(List<Object> values) {
            addCriterion("DEVICE_CODE in", values, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotIn(List<Object> values) {
            addCriterion("DEVICE_CODE not in", values, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeBetween(Object value1, Object value2) {
            addCriterion("DEVICE_CODE between", value1, value2, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotBetween(Object value1, Object value2) {
            addCriterion("DEVICE_CODE not between", value1, value2, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andJd84IsNull() {
            addCriterion("JD84 is null");
            return (Criteria) this;
        }

        public Criteria andJd84IsNotNull() {
            addCriterion("JD84 is not null");
            return (Criteria) this;
        }

        public Criteria andJd84EqualTo(Object value) {
            addCriterion("JD84 =", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotEqualTo(Object value) {
            addCriterion("JD84 <>", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84GreaterThan(Object value) {
            addCriterion("JD84 >", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84GreaterThanOrEqualTo(Object value) {
            addCriterion("JD84 >=", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84LessThan(Object value) {
            addCriterion("JD84 <", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84LessThanOrEqualTo(Object value) {
            addCriterion("JD84 <=", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84In(List<Object> values) {
            addCriterion("JD84 in", values, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotIn(List<Object> values) {
            addCriterion("JD84 not in", values, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84Between(Object value1, Object value2) {
            addCriterion("JD84 between", value1, value2, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotBetween(Object value1, Object value2) {
            addCriterion("JD84 not between", value1, value2, "jd84");
            return (Criteria) this;
        }

        public Criteria andWd84IsNull() {
            addCriterion("WD84 is null");
            return (Criteria) this;
        }

        public Criteria andWd84IsNotNull() {
            addCriterion("WD84 is not null");
            return (Criteria) this;
        }

        public Criteria andWd84EqualTo(Object value) {
            addCriterion("WD84 =", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotEqualTo(Object value) {
            addCriterion("WD84 <>", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84GreaterThan(Object value) {
            addCriterion("WD84 >", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84GreaterThanOrEqualTo(Object value) {
            addCriterion("WD84 >=", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84LessThan(Object value) {
            addCriterion("WD84 <", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84LessThanOrEqualTo(Object value) {
            addCriterion("WD84 <=", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84In(List<Object> values) {
            addCriterion("WD84 in", values, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotIn(List<Object> values) {
            addCriterion("WD84 not in", values, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84Between(Object value1, Object value2) {
            addCriterion("WD84 between", value1, value2, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotBetween(Object value1, Object value2) {
            addCriterion("WD84 not between", value1, value2, "wd84");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(Object value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(Object value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(Object value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(Object value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(Object value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(Object value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<Object> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<Object> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(Object value1, Object value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(Object value1, Object value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusIsNull() {
            addCriterion("UPDATE_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusIsNotNull() {
            addCriterion("UPDATE_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusEqualTo(Object value) {
            addCriterion("UPDATE_STATUS =", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotEqualTo(Object value) {
            addCriterion("UPDATE_STATUS <>", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusGreaterThan(Object value) {
            addCriterion("UPDATE_STATUS >", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusGreaterThanOrEqualTo(Object value) {
            addCriterion("UPDATE_STATUS >=", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusLessThan(Object value) {
            addCriterion("UPDATE_STATUS <", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusLessThanOrEqualTo(Object value) {
            addCriterion("UPDATE_STATUS <=", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusIn(List<Object> values) {
            addCriterion("UPDATE_STATUS in", values, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotIn(List<Object> values) {
            addCriterion("UPDATE_STATUS not in", values, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusBetween(Object value1, Object value2) {
            addCriterion("UPDATE_STATUS between", value1, value2, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotBetween(Object value1, Object value2) {
            addCriterion("UPDATE_STATUS not between", value1, value2, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("UUID is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("UUID is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(Object value) {
            addCriterion("UUID =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(Object value) {
            addCriterion("UUID <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(Object value) {
            addCriterion("UUID >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(Object value) {
            addCriterion("UUID >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(Object value) {
            addCriterion("UUID <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(Object value) {
            addCriterion("UUID <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<Object> values) {
            addCriterion("UUID in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<Object> values) {
            addCriterion("UUID not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(Object value1, Object value2) {
            addCriterion("UUID between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(Object value1, Object value2) {
            addCriterion("UUID not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("REGION is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("REGION is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(Object value) {
            addCriterion("REGION =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(Object value) {
            addCriterion("REGION <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(Object value) {
            addCriterion("REGION >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(Object value) {
            addCriterion("REGION >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(Object value) {
            addCriterion("REGION <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(Object value) {
            addCriterion("REGION <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<Object> values) {
            addCriterion("REGION in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<Object> values) {
            addCriterion("REGION not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(Object value1, Object value2) {
            addCriterion("REGION between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(Object value1, Object value2) {
            addCriterion("REGION not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andReserved3IsNull() {
            addCriterion("RESERVED3 is null");
            return (Criteria) this;
        }

        public Criteria andReserved3IsNotNull() {
            addCriterion("RESERVED3 is not null");
            return (Criteria) this;
        }

        public Criteria andReserved3EqualTo(Object value) {
            addCriterion("RESERVED3 =", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotEqualTo(Object value) {
            addCriterion("RESERVED3 <>", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3GreaterThan(Object value) {
            addCriterion("RESERVED3 >", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3GreaterThanOrEqualTo(Object value) {
            addCriterion("RESERVED3 >=", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3LessThan(Object value) {
            addCriterion("RESERVED3 <", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3LessThanOrEqualTo(Object value) {
            addCriterion("RESERVED3 <=", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3In(List<Object> values) {
            addCriterion("RESERVED3 in", values, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotIn(List<Object> values) {
            addCriterion("RESERVED3 not in", values, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3Between(Object value1, Object value2) {
            addCriterion("RESERVED3 between", value1, value2, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotBetween(Object value1, Object value2) {
            addCriterion("RESERVED3 not between", value1, value2, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved4IsNull() {
            addCriterion("RESERVED4 is null");
            return (Criteria) this;
        }

        public Criteria andReserved4IsNotNull() {
            addCriterion("RESERVED4 is not null");
            return (Criteria) this;
        }

        public Criteria andReserved4EqualTo(Object value) {
            addCriterion("RESERVED4 =", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotEqualTo(Object value) {
            addCriterion("RESERVED4 <>", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4GreaterThan(Object value) {
            addCriterion("RESERVED4 >", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4GreaterThanOrEqualTo(Object value) {
            addCriterion("RESERVED4 >=", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4LessThan(Object value) {
            addCriterion("RESERVED4 <", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4LessThanOrEqualTo(Object value) {
            addCriterion("RESERVED4 <=", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4In(List<Object> values) {
            addCriterion("RESERVED4 in", values, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotIn(List<Object> values) {
            addCriterion("RESERVED4 not in", values, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4Between(Object value1, Object value2) {
            addCriterion("RESERVED4 between", value1, value2, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotBetween(Object value1, Object value2) {
            addCriterion("RESERVED4 not between", value1, value2, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved5IsNull() {
            addCriterion("RESERVED5 is null");
            return (Criteria) this;
        }

        public Criteria andReserved5IsNotNull() {
            addCriterion("RESERVED5 is not null");
            return (Criteria) this;
        }

        public Criteria andReserved5EqualTo(Object value) {
            addCriterion("RESERVED5 =", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotEqualTo(Object value) {
            addCriterion("RESERVED5 <>", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5GreaterThan(Object value) {
            addCriterion("RESERVED5 >", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5GreaterThanOrEqualTo(Object value) {
            addCriterion("RESERVED5 >=", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5LessThan(Object value) {
            addCriterion("RESERVED5 <", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5LessThanOrEqualTo(Object value) {
            addCriterion("RESERVED5 <=", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5In(List<Object> values) {
            addCriterion("RESERVED5 in", values, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotIn(List<Object> values) {
            addCriterion("RESERVED5 not in", values, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5Between(Object value1, Object value2) {
            addCriterion("RESERVED5 between", value1, value2, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotBetween(Object value1, Object value2) {
            addCriterion("RESERVED5 not between", value1, value2, "reserved5");
            return (Criteria) this;
        }

        public Criteria andStreetIsNull() {
            addCriterion("STREET is null");
            return (Criteria) this;
        }

        public Criteria andStreetIsNotNull() {
            addCriterion("STREET is not null");
            return (Criteria) this;
        }

        public Criteria andStreetEqualTo(Object value) {
            addCriterion("STREET =", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotEqualTo(Object value) {
            addCriterion("STREET <>", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThan(Object value) {
            addCriterion("STREET >", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThanOrEqualTo(Object value) {
            addCriterion("STREET >=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThan(Object value) {
            addCriterion("STREET <", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThanOrEqualTo(Object value) {
            addCriterion("STREET <=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetIn(List<Object> values) {
            addCriterion("STREET in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotIn(List<Object> values) {
            addCriterion("STREET not in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetBetween(Object value1, Object value2) {
            addCriterion("STREET between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotBetween(Object value1, Object value2) {
            addCriterion("STREET not between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("DEVICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("DEVICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(String value) {
            addCriterion("DEVICE_TYPE =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(String value) {
            addCriterion("DEVICE_TYPE <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(String value) {
            addCriterion("DEVICE_TYPE >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_TYPE >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(String value) {
            addCriterion("DEVICE_TYPE <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_TYPE <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLike(String value) {
            addCriterion("DEVICE_TYPE like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotLike(String value) {
            addCriterion("DEVICE_TYPE not like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<String> values) {
            addCriterion("DEVICE_TYPE in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<String> values) {
            addCriterion("DEVICE_TYPE not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(String value1, String value2) {
            addCriterion("DEVICE_TYPE between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(String value1, String value2) {
            addCriterion("DEVICE_TYPE not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andJdszIsNull() {
            addCriterion("JDSZ is null");
            return (Criteria) this;
        }

        public Criteria andJdszIsNotNull() {
            addCriterion("JDSZ is not null");
            return (Criteria) this;
        }

        public Criteria andJdszEqualTo(Object value) {
            addCriterion("JDSZ =", value, "jdsz");
            return (Criteria) this;
        }

        public Criteria andJdszNotEqualTo(Object value) {
            addCriterion("JDSZ <>", value, "jdsz");
            return (Criteria) this;
        }

        public Criteria andJdszGreaterThan(Object value) {
            addCriterion("JDSZ >", value, "jdsz");
            return (Criteria) this;
        }

        public Criteria andJdszGreaterThanOrEqualTo(Object value) {
            addCriterion("JDSZ >=", value, "jdsz");
            return (Criteria) this;
        }

        public Criteria andJdszLessThan(Object value) {
            addCriterion("JDSZ <", value, "jdsz");
            return (Criteria) this;
        }

        public Criteria andJdszLessThanOrEqualTo(Object value) {
            addCriterion("JDSZ <=", value, "jdsz");
            return (Criteria) this;
        }

        public Criteria andJdszIn(List<Object> values) {
            addCriterion("JDSZ in", values, "jdsz");
            return (Criteria) this;
        }

        public Criteria andJdszNotIn(List<Object> values) {
            addCriterion("JDSZ not in", values, "jdsz");
            return (Criteria) this;
        }

        public Criteria andJdszBetween(Object value1, Object value2) {
            addCriterion("JDSZ between", value1, value2, "jdsz");
            return (Criteria) this;
        }

        public Criteria andJdszNotBetween(Object value1, Object value2) {
            addCriterion("JDSZ not between", value1, value2, "jdsz");
            return (Criteria) this;
        }

        public Criteria andWdszIsNull() {
            addCriterion("WDSZ is null");
            return (Criteria) this;
        }

        public Criteria andWdszIsNotNull() {
            addCriterion("WDSZ is not null");
            return (Criteria) this;
        }

        public Criteria andWdszEqualTo(Object value) {
            addCriterion("WDSZ =", value, "wdsz");
            return (Criteria) this;
        }

        public Criteria andWdszNotEqualTo(Object value) {
            addCriterion("WDSZ <>", value, "wdsz");
            return (Criteria) this;
        }

        public Criteria andWdszGreaterThan(Object value) {
            addCriterion("WDSZ >", value, "wdsz");
            return (Criteria) this;
        }

        public Criteria andWdszGreaterThanOrEqualTo(Object value) {
            addCriterion("WDSZ >=", value, "wdsz");
            return (Criteria) this;
        }

        public Criteria andWdszLessThan(Object value) {
            addCriterion("WDSZ <", value, "wdsz");
            return (Criteria) this;
        }

        public Criteria andWdszLessThanOrEqualTo(Object value) {
            addCriterion("WDSZ <=", value, "wdsz");
            return (Criteria) this;
        }

        public Criteria andWdszIn(List<Object> values) {
            addCriterion("WDSZ in", values, "wdsz");
            return (Criteria) this;
        }

        public Criteria andWdszNotIn(List<Object> values) {
            addCriterion("WDSZ not in", values, "wdsz");
            return (Criteria) this;
        }

        public Criteria andWdszBetween(Object value1, Object value2) {
            addCriterion("WDSZ between", value1, value2, "wdsz");
            return (Criteria) this;
        }

        public Criteria andWdszNotBetween(Object value1, Object value2) {
            addCriterion("WDSZ not between", value1, value2, "wdsz");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIsNull() {
            addCriterion("REGION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIsNotNull() {
            addCriterion("REGION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRegionCodeEqualTo(String value) {
            addCriterion("REGION_CODE =", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotEqualTo(String value) {
            addCriterion("REGION_CODE <>", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeGreaterThan(String value) {
            addCriterion("REGION_CODE >", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("REGION_CODE >=", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLessThan(String value) {
            addCriterion("REGION_CODE <", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLessThanOrEqualTo(String value) {
            addCriterion("REGION_CODE <=", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeLike(String value) {
            addCriterion("REGION_CODE like", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotLike(String value) {
            addCriterion("REGION_CODE not like", value, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeIn(List<String> values) {
            addCriterion("REGION_CODE in", values, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotIn(List<String> values) {
            addCriterion("REGION_CODE not in", values, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeBetween(String value1, String value2) {
            addCriterion("REGION_CODE between", value1, value2, "regionCode");
            return (Criteria) this;
        }

        public Criteria andRegionCodeNotBetween(String value1, String value2) {
            addCriterion("REGION_CODE not between", value1, value2, "regionCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeIsNull() {
            addCriterion("STREET_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStreetCodeIsNotNull() {
            addCriterion("STREET_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStreetCodeEqualTo(String value) {
            addCriterion("STREET_CODE =", value, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeNotEqualTo(String value) {
            addCriterion("STREET_CODE <>", value, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeGreaterThan(String value) {
            addCriterion("STREET_CODE >", value, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STREET_CODE >=", value, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeLessThan(String value) {
            addCriterion("STREET_CODE <", value, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeLessThanOrEqualTo(String value) {
            addCriterion("STREET_CODE <=", value, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeLike(String value) {
            addCriterion("STREET_CODE like", value, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeNotLike(String value) {
            addCriterion("STREET_CODE not like", value, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeIn(List<String> values) {
            addCriterion("STREET_CODE in", values, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeNotIn(List<String> values) {
            addCriterion("STREET_CODE not in", values, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeBetween(String value1, String value2) {
            addCriterion("STREET_CODE between", value1, value2, "streetCode");
            return (Criteria) this;
        }

        public Criteria andStreetCodeNotBetween(String value1, String value2) {
            addCriterion("STREET_CODE not between", value1, value2, "streetCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeIsNull() {
            addCriterion("COMMUNITY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeIsNotNull() {
            addCriterion("COMMUNITY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeEqualTo(String value) {
            addCriterion("COMMUNITY_CODE =", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeNotEqualTo(String value) {
            addCriterion("COMMUNITY_CODE <>", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeGreaterThan(String value) {
            addCriterion("COMMUNITY_CODE >", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COMMUNITY_CODE >=", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeLessThan(String value) {
            addCriterion("COMMUNITY_CODE <", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeLessThanOrEqualTo(String value) {
            addCriterion("COMMUNITY_CODE <=", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeLike(String value) {
            addCriterion("COMMUNITY_CODE like", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeNotLike(String value) {
            addCriterion("COMMUNITY_CODE not like", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeIn(List<String> values) {
            addCriterion("COMMUNITY_CODE in", values, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeNotIn(List<String> values) {
            addCriterion("COMMUNITY_CODE not in", values, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeBetween(String value1, String value2) {
            addCriterion("COMMUNITY_CODE between", value1, value2, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeNotBetween(String value1, String value2) {
            addCriterion("COMMUNITY_CODE not between", value1, value2, "communityCode");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}