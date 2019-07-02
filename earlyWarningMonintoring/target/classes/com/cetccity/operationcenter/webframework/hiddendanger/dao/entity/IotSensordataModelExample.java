package com.cetccity.operationcenter.webframework.hiddendanger.dao.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class IotSensordataModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IotSensordataModelExample() {
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

        public Criteria andDataCodeIsNull() {
            addCriterion("DATA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andDataCodeIsNotNull() {
            addCriterion("DATA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andDataCodeEqualTo(Object value) {
            addCriterion("DATA_CODE =", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotEqualTo(Object value) {
            addCriterion("DATA_CODE <>", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeGreaterThan(Object value) {
            addCriterion("DATA_CODE >", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeGreaterThanOrEqualTo(Object value) {
            addCriterion("DATA_CODE >=", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeLessThan(Object value) {
            addCriterion("DATA_CODE <", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeLessThanOrEqualTo(Object value) {
            addCriterion("DATA_CODE <=", value, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeIn(List<Object> values) {
            addCriterion("DATA_CODE in", values, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotIn(List<Object> values) {
            addCriterion("DATA_CODE not in", values, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeBetween(Object value1, Object value2) {
            addCriterion("DATA_CODE between", value1, value2, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataCodeNotBetween(Object value1, Object value2) {
            addCriterion("DATA_CODE not between", value1, value2, "dataCode");
            return (Criteria) this;
        }

        public Criteria andDataValueIsNull() {
            addCriterion("DATA_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andDataValueIsNotNull() {
            addCriterion("DATA_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andDataValueEqualTo(Object value) {
            addCriterion("DATA_VALUE =", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueNotEqualTo(Object value) {
            addCriterion("DATA_VALUE <>", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueGreaterThan(Object value) {
            addCriterion("DATA_VALUE >", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueGreaterThanOrEqualTo(Object value) {
            addCriterion("DATA_VALUE >=", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueLessThan(Object value) {
            addCriterion("DATA_VALUE <", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueLessThanOrEqualTo(Object value) {
            addCriterion("DATA_VALUE <=", value, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueIn(List<Object> values) {
            addCriterion("DATA_VALUE in", values, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueNotIn(List<Object> values) {
            addCriterion("DATA_VALUE not in", values, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueBetween(Object value1, Object value2) {
            addCriterion("DATA_VALUE between", value1, value2, "dataValue");
            return (Criteria) this;
        }

        public Criteria andDataValueNotBetween(Object value1, Object value2) {
            addCriterion("DATA_VALUE not between", value1, value2, "dataValue");
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

        public Criteria andCreateTimeEqualTo(Object value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Object value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Object value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Object value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Object value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Object value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Object> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Object> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Object value1, Object value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Object value1, Object value2) {
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

        public Criteria andUpdateTimeEqualTo(Object value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Object value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Object value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Object value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Object value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Object value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Object> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Object> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Object value1, Object value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Object value1, Object value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
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

        public Criteria andReserved2IsNull() {
            addCriterion("RESERVED2 is null");
            return (Criteria) this;
        }

        public Criteria andReserved2IsNotNull() {
            addCriterion("RESERVED2 is not null");
            return (Criteria) this;
        }

        public Criteria andReserved2EqualTo(Object value) {
            addCriterion("RESERVED2 =", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotEqualTo(Object value) {
            addCriterion("RESERVED2 <>", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2GreaterThan(Object value) {
            addCriterion("RESERVED2 >", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2GreaterThanOrEqualTo(Object value) {
            addCriterion("RESERVED2 >=", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2LessThan(Object value) {
            addCriterion("RESERVED2 <", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2LessThanOrEqualTo(Object value) {
            addCriterion("RESERVED2 <=", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2In(List<Object> values) {
            addCriterion("RESERVED2 in", values, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotIn(List<Object> values) {
            addCriterion("RESERVED2 not in", values, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2Between(Object value1, Object value2) {
            addCriterion("RESERVED2 between", value1, value2, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotBetween(Object value1, Object value2) {
            addCriterion("RESERVED2 not between", value1, value2, "reserved2");
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