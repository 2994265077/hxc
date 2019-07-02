package com.cetccity.operationcenter.webframework.hiddendanger.dao.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class IotEventModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IotEventModelExample() {
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

        public Criteria andEventIdIsNull() {
            addCriterion("EVENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andEventIdIsNotNull() {
            addCriterion("EVENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEventIdEqualTo(Object value) {
            addCriterion("EVENT_ID =", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotEqualTo(Object value) {
            addCriterion("EVENT_ID <>", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdGreaterThan(Object value) {
            addCriterion("EVENT_ID >", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdGreaterThanOrEqualTo(Object value) {
            addCriterion("EVENT_ID >=", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdLessThan(Object value) {
            addCriterion("EVENT_ID <", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdLessThanOrEqualTo(Object value) {
            addCriterion("EVENT_ID <=", value, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdIn(List<Object> values) {
            addCriterion("EVENT_ID in", values, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotIn(List<Object> values) {
            addCriterion("EVENT_ID not in", values, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdBetween(Object value1, Object value2) {
            addCriterion("EVENT_ID between", value1, value2, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventIdNotBetween(Object value1, Object value2) {
            addCriterion("EVENT_ID not between", value1, value2, "eventId");
            return (Criteria) this;
        }

        public Criteria andEventCodeIsNull() {
            addCriterion("EVENT_CODE is null");
            return (Criteria) this;
        }

        public Criteria andEventCodeIsNotNull() {
            addCriterion("EVENT_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andEventCodeEqualTo(Object value) {
            addCriterion("EVENT_CODE =", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeNotEqualTo(Object value) {
            addCriterion("EVENT_CODE <>", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeGreaterThan(Object value) {
            addCriterion("EVENT_CODE >", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeGreaterThanOrEqualTo(Object value) {
            addCriterion("EVENT_CODE >=", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeLessThan(Object value) {
            addCriterion("EVENT_CODE <", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeLessThanOrEqualTo(Object value) {
            addCriterion("EVENT_CODE <=", value, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeIn(List<Object> values) {
            addCriterion("EVENT_CODE in", values, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeNotIn(List<Object> values) {
            addCriterion("EVENT_CODE not in", values, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeBetween(Object value1, Object value2) {
            addCriterion("EVENT_CODE between", value1, value2, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventCodeNotBetween(Object value1, Object value2) {
            addCriterion("EVENT_CODE not between", value1, value2, "eventCode");
            return (Criteria) this;
        }

        public Criteria andEventNameIsNull() {
            addCriterion("EVENT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEventNameIsNotNull() {
            addCriterion("EVENT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEventNameEqualTo(Object value) {
            addCriterion("EVENT_NAME =", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotEqualTo(Object value) {
            addCriterion("EVENT_NAME <>", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameGreaterThan(Object value) {
            addCriterion("EVENT_NAME >", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameGreaterThanOrEqualTo(Object value) {
            addCriterion("EVENT_NAME >=", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLessThan(Object value) {
            addCriterion("EVENT_NAME <", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLessThanOrEqualTo(Object value) {
            addCriterion("EVENT_NAME <=", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameIn(List<Object> values) {
            addCriterion("EVENT_NAME in", values, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotIn(List<Object> values) {
            addCriterion("EVENT_NAME not in", values, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameBetween(Object value1, Object value2) {
            addCriterion("EVENT_NAME between", value1, value2, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotBetween(Object value1, Object value2) {
            addCriterion("EVENT_NAME not between", value1, value2, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventLevelIsNull() {
            addCriterion("EVENT_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andEventLevelIsNotNull() {
            addCriterion("EVENT_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andEventLevelEqualTo(Object value) {
            addCriterion("EVENT_LEVEL =", value, "eventLevel");
            return (Criteria) this;
        }

        public Criteria andEventLevelNotEqualTo(Object value) {
            addCriterion("EVENT_LEVEL <>", value, "eventLevel");
            return (Criteria) this;
        }

        public Criteria andEventLevelGreaterThan(Object value) {
            addCriterion("EVENT_LEVEL >", value, "eventLevel");
            return (Criteria) this;
        }

        public Criteria andEventLevelGreaterThanOrEqualTo(Object value) {
            addCriterion("EVENT_LEVEL >=", value, "eventLevel");
            return (Criteria) this;
        }

        public Criteria andEventLevelLessThan(Object value) {
            addCriterion("EVENT_LEVEL <", value, "eventLevel");
            return (Criteria) this;
        }

        public Criteria andEventLevelLessThanOrEqualTo(Object value) {
            addCriterion("EVENT_LEVEL <=", value, "eventLevel");
            return (Criteria) this;
        }

        public Criteria andEventLevelIn(List<Object> values) {
            addCriterion("EVENT_LEVEL in", values, "eventLevel");
            return (Criteria) this;
        }

        public Criteria andEventLevelNotIn(List<Object> values) {
            addCriterion("EVENT_LEVEL not in", values, "eventLevel");
            return (Criteria) this;
        }

        public Criteria andEventLevelBetween(Object value1, Object value2) {
            addCriterion("EVENT_LEVEL between", value1, value2, "eventLevel");
            return (Criteria) this;
        }

        public Criteria andEventLevelNotBetween(Object value1, Object value2) {
            addCriterion("EVENT_LEVEL not between", value1, value2, "eventLevel");
            return (Criteria) this;
        }

        public Criteria andEventStateIsNull() {
            addCriterion("EVENT_STATE is null");
            return (Criteria) this;
        }

        public Criteria andEventStateIsNotNull() {
            addCriterion("EVENT_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andEventStateEqualTo(Object value) {
            addCriterion("EVENT_STATE =", value, "eventState");
            return (Criteria) this;
        }

        public Criteria andEventStateNotEqualTo(Object value) {
            addCriterion("EVENT_STATE <>", value, "eventState");
            return (Criteria) this;
        }

        public Criteria andEventStateGreaterThan(Object value) {
            addCriterion("EVENT_STATE >", value, "eventState");
            return (Criteria) this;
        }

        public Criteria andEventStateGreaterThanOrEqualTo(Object value) {
            addCriterion("EVENT_STATE >=", value, "eventState");
            return (Criteria) this;
        }

        public Criteria andEventStateLessThan(Object value) {
            addCriterion("EVENT_STATE <", value, "eventState");
            return (Criteria) this;
        }

        public Criteria andEventStateLessThanOrEqualTo(Object value) {
            addCriterion("EVENT_STATE <=", value, "eventState");
            return (Criteria) this;
        }

        public Criteria andEventStateIn(List<Object> values) {
            addCriterion("EVENT_STATE in", values, "eventState");
            return (Criteria) this;
        }

        public Criteria andEventStateNotIn(List<Object> values) {
            addCriterion("EVENT_STATE not in", values, "eventState");
            return (Criteria) this;
        }

        public Criteria andEventStateBetween(Object value1, Object value2) {
            addCriterion("EVENT_STATE between", value1, value2, "eventState");
            return (Criteria) this;
        }

        public Criteria andEventStateNotBetween(Object value1, Object value2) {
            addCriterion("EVENT_STATE not between", value1, value2, "eventState");
            return (Criteria) this;
        }

        public Criteria andProduceTimeIsNull() {
            addCriterion("PRODUCE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProduceTimeIsNotNull() {
            addCriterion("PRODUCE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProduceTimeEqualTo(Date value) {
            addCriterionForJDBCDate("PRODUCE_TIME =", value, "produceTime");
            return (Criteria) this;
        }

        public Criteria andProduceTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("PRODUCE_TIME <>", value, "produceTime");
            return (Criteria) this;
        }

        public Criteria andProduceTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("PRODUCE_TIME >", value, "produceTime");
            return (Criteria) this;
        }

        public Criteria andProduceTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PRODUCE_TIME >=", value, "produceTime");
            return (Criteria) this;
        }

        public Criteria andProduceTimeLessThan(Date value) {
            addCriterionForJDBCDate("PRODUCE_TIME <", value, "produceTime");
            return (Criteria) this;
        }

        public Criteria andProduceTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PRODUCE_TIME <=", value, "produceTime");
            return (Criteria) this;
        }

        public Criteria andProduceTimeIn(List<Date> values) {
            addCriterionForJDBCDate("PRODUCE_TIME in", values, "produceTime");
            return (Criteria) this;
        }

        public Criteria andProduceTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("PRODUCE_TIME not in", values, "produceTime");
            return (Criteria) this;
        }

        public Criteria andProduceTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PRODUCE_TIME between", value1, value2, "produceTime");
            return (Criteria) this;
        }

        public Criteria andProduceTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PRODUCE_TIME not between", value1, value2, "produceTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("END_TIME not between", value1, value2, "endTime");
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

        public Criteria andUpdateStateIsNull() {
            addCriterion("UPDATE_STATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStateIsNotNull() {
            addCriterion("UPDATE_STATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStateEqualTo(Object value) {
            addCriterion("UPDATE_STATE =", value, "updateState");
            return (Criteria) this;
        }

        public Criteria andUpdateStateNotEqualTo(Object value) {
            addCriterion("UPDATE_STATE <>", value, "updateState");
            return (Criteria) this;
        }

        public Criteria andUpdateStateGreaterThan(Object value) {
            addCriterion("UPDATE_STATE >", value, "updateState");
            return (Criteria) this;
        }

        public Criteria andUpdateStateGreaterThanOrEqualTo(Object value) {
            addCriterion("UPDATE_STATE >=", value, "updateState");
            return (Criteria) this;
        }

        public Criteria andUpdateStateLessThan(Object value) {
            addCriterion("UPDATE_STATE <", value, "updateState");
            return (Criteria) this;
        }

        public Criteria andUpdateStateLessThanOrEqualTo(Object value) {
            addCriterion("UPDATE_STATE <=", value, "updateState");
            return (Criteria) this;
        }

        public Criteria andUpdateStateIn(List<Object> values) {
            addCriterion("UPDATE_STATE in", values, "updateState");
            return (Criteria) this;
        }

        public Criteria andUpdateStateNotIn(List<Object> values) {
            addCriterion("UPDATE_STATE not in", values, "updateState");
            return (Criteria) this;
        }

        public Criteria andUpdateStateBetween(Object value1, Object value2) {
            addCriterion("UPDATE_STATE between", value1, value2, "updateState");
            return (Criteria) this;
        }

        public Criteria andUpdateStateNotBetween(Object value1, Object value2) {
            addCriterion("UPDATE_STATE not between", value1, value2, "updateState");
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

        public Criteria andParaCodeIsNull() {
            addCriterion("PARA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andParaCodeIsNotNull() {
            addCriterion("PARA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andParaCodeEqualTo(Object value) {
            addCriterion("PARA_CODE =", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeNotEqualTo(Object value) {
            addCriterion("PARA_CODE <>", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeGreaterThan(Object value) {
            addCriterion("PARA_CODE >", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeGreaterThanOrEqualTo(Object value) {
            addCriterion("PARA_CODE >=", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeLessThan(Object value) {
            addCriterion("PARA_CODE <", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeLessThanOrEqualTo(Object value) {
            addCriterion("PARA_CODE <=", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeIn(List<Object> values) {
            addCriterion("PARA_CODE in", values, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeNotIn(List<Object> values) {
            addCriterion("PARA_CODE not in", values, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeBetween(Object value1, Object value2) {
            addCriterion("PARA_CODE between", value1, value2, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeNotBetween(Object value1, Object value2) {
            addCriterion("PARA_CODE not between", value1, value2, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaValueIsNull() {
            addCriterion("PARA_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andParaValueIsNotNull() {
            addCriterion("PARA_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andParaValueEqualTo(Object value) {
            addCriterion("PARA_VALUE =", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotEqualTo(Object value) {
            addCriterion("PARA_VALUE <>", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueGreaterThan(Object value) {
            addCriterion("PARA_VALUE >", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueGreaterThanOrEqualTo(Object value) {
            addCriterion("PARA_VALUE >=", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueLessThan(Object value) {
            addCriterion("PARA_VALUE <", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueLessThanOrEqualTo(Object value) {
            addCriterion("PARA_VALUE <=", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueIn(List<Object> values) {
            addCriterion("PARA_VALUE in", values, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotIn(List<Object> values) {
            addCriterion("PARA_VALUE not in", values, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueBetween(Object value1, Object value2) {
            addCriterion("PARA_VALUE between", value1, value2, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotBetween(Object value1, Object value2) {
            addCriterion("PARA_VALUE not between", value1, value2, "paraValue");
            return (Criteria) this;
        }

        public Criteria andIsProcessedIsNull() {
            addCriterion("IS_PROCESSED is null");
            return (Criteria) this;
        }

        public Criteria andIsProcessedIsNotNull() {
            addCriterion("IS_PROCESSED is not null");
            return (Criteria) this;
        }

        public Criteria andIsProcessedEqualTo(String value) {
            addCriterion("IS_PROCESSED =", value, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedNotEqualTo(String value) {
            addCriterion("IS_PROCESSED <>", value, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedGreaterThan(String value) {
            addCriterion("IS_PROCESSED >", value, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedGreaterThanOrEqualTo(String value) {
            addCriterion("IS_PROCESSED >=", value, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedLessThan(String value) {
            addCriterion("IS_PROCESSED <", value, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedLessThanOrEqualTo(String value) {
            addCriterion("IS_PROCESSED <=", value, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedLike(String value) {
            addCriterion("IS_PROCESSED like", value, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedNotLike(String value) {
            addCriterion("IS_PROCESSED not like", value, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedIn(List<String> values) {
            addCriterion("IS_PROCESSED in", values, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedNotIn(List<String> values) {
            addCriterion("IS_PROCESSED not in", values, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedBetween(String value1, String value2) {
            addCriterion("IS_PROCESSED between", value1, value2, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andIsProcessedNotBetween(String value1, String value2) {
            addCriterion("IS_PROCESSED not between", value1, value2, "isProcessed");
            return (Criteria) this;
        }

        public Criteria andProcessorIsNull() {
            addCriterion("PROCESSOR is null");
            return (Criteria) this;
        }

        public Criteria andProcessorIsNotNull() {
            addCriterion("PROCESSOR is not null");
            return (Criteria) this;
        }

        public Criteria andProcessorEqualTo(String value) {
            addCriterion("PROCESSOR =", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotEqualTo(String value) {
            addCriterion("PROCESSOR <>", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorGreaterThan(String value) {
            addCriterion("PROCESSOR >", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESSOR >=", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorLessThan(String value) {
            addCriterion("PROCESSOR <", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorLessThanOrEqualTo(String value) {
            addCriterion("PROCESSOR <=", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorLike(String value) {
            addCriterion("PROCESSOR like", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotLike(String value) {
            addCriterion("PROCESSOR not like", value, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorIn(List<String> values) {
            addCriterion("PROCESSOR in", values, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotIn(List<String> values) {
            addCriterion("PROCESSOR not in", values, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorBetween(String value1, String value2) {
            addCriterion("PROCESSOR between", value1, value2, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessorNotBetween(String value1, String value2) {
            addCriterion("PROCESSOR not between", value1, value2, "processor");
            return (Criteria) this;
        }

        public Criteria andProcessUnitIsNull() {
            addCriterion("PROCESS_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andProcessUnitIsNotNull() {
            addCriterion("PROCESS_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andProcessUnitEqualTo(String value) {
            addCriterion("PROCESS_UNIT =", value, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitNotEqualTo(String value) {
            addCriterion("PROCESS_UNIT <>", value, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitGreaterThan(String value) {
            addCriterion("PROCESS_UNIT >", value, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_UNIT >=", value, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitLessThan(String value) {
            addCriterion("PROCESS_UNIT <", value, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_UNIT <=", value, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitLike(String value) {
            addCriterion("PROCESS_UNIT like", value, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitNotLike(String value) {
            addCriterion("PROCESS_UNIT not like", value, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitIn(List<String> values) {
            addCriterion("PROCESS_UNIT in", values, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitNotIn(List<String> values) {
            addCriterion("PROCESS_UNIT not in", values, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitBetween(String value1, String value2) {
            addCriterion("PROCESS_UNIT between", value1, value2, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessUnitNotBetween(String value1, String value2) {
            addCriterion("PROCESS_UNIT not between", value1, value2, "processUnit");
            return (Criteria) this;
        }

        public Criteria andProcessSystemIsNull() {
            addCriterion("PROCESS_SYSTEM is null");
            return (Criteria) this;
        }

        public Criteria andProcessSystemIsNotNull() {
            addCriterion("PROCESS_SYSTEM is not null");
            return (Criteria) this;
        }

        public Criteria andProcessSystemEqualTo(String value) {
            addCriterion("PROCESS_SYSTEM =", value, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemNotEqualTo(String value) {
            addCriterion("PROCESS_SYSTEM <>", value, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemGreaterThan(String value) {
            addCriterion("PROCESS_SYSTEM >", value, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_SYSTEM >=", value, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemLessThan(String value) {
            addCriterion("PROCESS_SYSTEM <", value, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_SYSTEM <=", value, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemLike(String value) {
            addCriterion("PROCESS_SYSTEM like", value, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemNotLike(String value) {
            addCriterion("PROCESS_SYSTEM not like", value, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemIn(List<String> values) {
            addCriterion("PROCESS_SYSTEM in", values, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemNotIn(List<String> values) {
            addCriterion("PROCESS_SYSTEM not in", values, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemBetween(String value1, String value2) {
            addCriterion("PROCESS_SYSTEM between", value1, value2, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessSystemNotBetween(String value1, String value2) {
            addCriterion("PROCESS_SYSTEM not between", value1, value2, "processSystem");
            return (Criteria) this;
        }

        public Criteria andProcessTimeIsNull() {
            addCriterion("PROCESS_TIME is null");
            return (Criteria) this;
        }

        public Criteria andProcessTimeIsNotNull() {
            addCriterion("PROCESS_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andProcessTimeEqualTo(Date value) {
            addCriterionForJDBCDate("PROCESS_TIME =", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("PROCESS_TIME <>", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("PROCESS_TIME >", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PROCESS_TIME >=", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeLessThan(Date value) {
            addCriterionForJDBCDate("PROCESS_TIME <", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("PROCESS_TIME <=", value, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeIn(List<Date> values) {
            addCriterionForJDBCDate("PROCESS_TIME in", values, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("PROCESS_TIME not in", values, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PROCESS_TIME between", value1, value2, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("PROCESS_TIME not between", value1, value2, "processTime");
            return (Criteria) this;
        }

        public Criteria andProcessWayIsNull() {
            addCriterion("PROCESS_WAY is null");
            return (Criteria) this;
        }

        public Criteria andProcessWayIsNotNull() {
            addCriterion("PROCESS_WAY is not null");
            return (Criteria) this;
        }

        public Criteria andProcessWayEqualTo(String value) {
            addCriterion("PROCESS_WAY =", value, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayNotEqualTo(String value) {
            addCriterion("PROCESS_WAY <>", value, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayGreaterThan(String value) {
            addCriterion("PROCESS_WAY >", value, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayGreaterThanOrEqualTo(String value) {
            addCriterion("PROCESS_WAY >=", value, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayLessThan(String value) {
            addCriterion("PROCESS_WAY <", value, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayLessThanOrEqualTo(String value) {
            addCriterion("PROCESS_WAY <=", value, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayLike(String value) {
            addCriterion("PROCESS_WAY like", value, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayNotLike(String value) {
            addCriterion("PROCESS_WAY not like", value, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayIn(List<String> values) {
            addCriterion("PROCESS_WAY in", values, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayNotIn(List<String> values) {
            addCriterion("PROCESS_WAY not in", values, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayBetween(String value1, String value2) {
            addCriterion("PROCESS_WAY between", value1, value2, "processWay");
            return (Criteria) this;
        }

        public Criteria andProcessWayNotBetween(String value1, String value2) {
            addCriterion("PROCESS_WAY not between", value1, value2, "processWay");
            return (Criteria) this;
        }

        public Criteria andReserved1IsNull() {
            addCriterion("RESERVED1 is null");
            return (Criteria) this;
        }

        public Criteria andReserved1IsNotNull() {
            addCriterion("RESERVED1 is not null");
            return (Criteria) this;
        }

        public Criteria andReserved1EqualTo(String value) {
            addCriterion("RESERVED1 =", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1NotEqualTo(String value) {
            addCriterion("RESERVED1 <>", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1GreaterThan(String value) {
            addCriterion("RESERVED1 >", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1GreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED1 >=", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1LessThan(String value) {
            addCriterion("RESERVED1 <", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1LessThanOrEqualTo(String value) {
            addCriterion("RESERVED1 <=", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1Like(String value) {
            addCriterion("RESERVED1 like", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1NotLike(String value) {
            addCriterion("RESERVED1 not like", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1In(List<String> values) {
            addCriterion("RESERVED1 in", values, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1NotIn(List<String> values) {
            addCriterion("RESERVED1 not in", values, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1Between(String value1, String value2) {
            addCriterion("RESERVED1 between", value1, value2, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1NotBetween(String value1, String value2) {
            addCriterion("RESERVED1 not between", value1, value2, "reserved1");
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

        public Criteria andReserved2EqualTo(String value) {
            addCriterion("RESERVED2 =", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotEqualTo(String value) {
            addCriterion("RESERVED2 <>", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2GreaterThan(String value) {
            addCriterion("RESERVED2 >", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2GreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED2 >=", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2LessThan(String value) {
            addCriterion("RESERVED2 <", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2LessThanOrEqualTo(String value) {
            addCriterion("RESERVED2 <=", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2Like(String value) {
            addCriterion("RESERVED2 like", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotLike(String value) {
            addCriterion("RESERVED2 not like", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2In(List<String> values) {
            addCriterion("RESERVED2 in", values, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotIn(List<String> values) {
            addCriterion("RESERVED2 not in", values, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2Between(String value1, String value2) {
            addCriterion("RESERVED2 between", value1, value2, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotBetween(String value1, String value2) {
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

        public Criteria andReserved3EqualTo(String value) {
            addCriterion("RESERVED3 =", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotEqualTo(String value) {
            addCriterion("RESERVED3 <>", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3GreaterThan(String value) {
            addCriterion("RESERVED3 >", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3GreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED3 >=", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3LessThan(String value) {
            addCriterion("RESERVED3 <", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3LessThanOrEqualTo(String value) {
            addCriterion("RESERVED3 <=", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3Like(String value) {
            addCriterion("RESERVED3 like", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotLike(String value) {
            addCriterion("RESERVED3 not like", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3In(List<String> values) {
            addCriterion("RESERVED3 in", values, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotIn(List<String> values) {
            addCriterion("RESERVED3 not in", values, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3Between(String value1, String value2) {
            addCriterion("RESERVED3 between", value1, value2, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotBetween(String value1, String value2) {
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

        public Criteria andReserved4EqualTo(String value) {
            addCriterion("RESERVED4 =", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotEqualTo(String value) {
            addCriterion("RESERVED4 <>", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4GreaterThan(String value) {
            addCriterion("RESERVED4 >", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4GreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED4 >=", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4LessThan(String value) {
            addCriterion("RESERVED4 <", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4LessThanOrEqualTo(String value) {
            addCriterion("RESERVED4 <=", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4Like(String value) {
            addCriterion("RESERVED4 like", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotLike(String value) {
            addCriterion("RESERVED4 not like", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4In(List<String> values) {
            addCriterion("RESERVED4 in", values, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotIn(List<String> values) {
            addCriterion("RESERVED4 not in", values, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4Between(String value1, String value2) {
            addCriterion("RESERVED4 between", value1, value2, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotBetween(String value1, String value2) {
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

        public Criteria andReserved5EqualTo(String value) {
            addCriterion("RESERVED5 =", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotEqualTo(String value) {
            addCriterion("RESERVED5 <>", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5GreaterThan(String value) {
            addCriterion("RESERVED5 >", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5GreaterThanOrEqualTo(String value) {
            addCriterion("RESERVED5 >=", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5LessThan(String value) {
            addCriterion("RESERVED5 <", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5LessThanOrEqualTo(String value) {
            addCriterion("RESERVED5 <=", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5Like(String value) {
            addCriterion("RESERVED5 like", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotLike(String value) {
            addCriterion("RESERVED5 not like", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5In(List<String> values) {
            addCriterion("RESERVED5 in", values, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotIn(List<String> values) {
            addCriterion("RESERVED5 not in", values, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5Between(String value1, String value2) {
            addCriterion("RESERVED5 between", value1, value2, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotBetween(String value1, String value2) {
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