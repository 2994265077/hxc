package com.cetccity.operationcenter.webframework.web.model.incident;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisposalProcessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DisposalProcessExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdIsNull() {
            addCriterion("emergency_id is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdIsNotNull() {
            addCriterion("emergency_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdEqualTo(String value) {
            addCriterion("emergency_id =", value, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdNotEqualTo(String value) {
            addCriterion("emergency_id <>", value, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdGreaterThan(String value) {
            addCriterion("emergency_id >", value, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdGreaterThanOrEqualTo(String value) {
            addCriterion("emergency_id >=", value, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdLessThan(String value) {
            addCriterion("emergency_id <", value, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdLessThanOrEqualTo(String value) {
            addCriterion("emergency_id <=", value, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdLike(String value) {
            addCriterion("emergency_id like", value, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdNotLike(String value) {
            addCriterion("emergency_id not like", value, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdIn(List<String> values) {
            addCriterion("emergency_id in", values, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdNotIn(List<String> values) {
            addCriterion("emergency_id not in", values, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdBetween(String value1, String value2) {
            addCriterion("emergency_id between", value1, value2, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andEmergencyIdNotBetween(String value1, String value2) {
            addCriterion("emergency_id not between", value1, value2, "emergencyId");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordIsNull() {
            addCriterion("disposal_record is null");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordIsNotNull() {
            addCriterion("disposal_record is not null");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordEqualTo(String value) {
            addCriterion("disposal_record =", value, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordNotEqualTo(String value) {
            addCriterion("disposal_record <>", value, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordGreaterThan(String value) {
            addCriterion("disposal_record >", value, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordGreaterThanOrEqualTo(String value) {
            addCriterion("disposal_record >=", value, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordLessThan(String value) {
            addCriterion("disposal_record <", value, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordLessThanOrEqualTo(String value) {
            addCriterion("disposal_record <=", value, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordLike(String value) {
            addCriterion("disposal_record like", value, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordNotLike(String value) {
            addCriterion("disposal_record not like", value, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordIn(List<String> values) {
            addCriterion("disposal_record in", values, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordNotIn(List<String> values) {
            addCriterion("disposal_record not in", values, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordBetween(String value1, String value2) {
            addCriterion("disposal_record between", value1, value2, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalRecordNotBetween(String value1, String value2) {
            addCriterion("disposal_record not between", value1, value2, "disposalRecord");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeIsNull() {
            addCriterion("disposal_time is null");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeIsNotNull() {
            addCriterion("disposal_time is not null");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeEqualTo(String value) {
            addCriterion("disposal_time =", value, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeNotEqualTo(String value) {
            addCriterion("disposal_time <>", value, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeGreaterThan(String value) {
            addCriterion("disposal_time >", value, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeGreaterThanOrEqualTo(String value) {
            addCriterion("disposal_time >=", value, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeLessThan(String value) {
            addCriterion("disposal_time <", value, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeLessThanOrEqualTo(String value) {
            addCriterion("disposal_time <=", value, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeLike(String value) {
            addCriterion("disposal_time like", value, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeNotLike(String value) {
            addCriterion("disposal_time not like", value, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeIn(List<String> values) {
            addCriterion("disposal_time in", values, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeNotIn(List<String> values) {
            addCriterion("disposal_time not in", values, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeBetween(String value1, String value2) {
            addCriterion("disposal_time between", value1, value2, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalTimeNotBetween(String value1, String value2) {
            addCriterion("disposal_time not between", value1, value2, "disposalTime");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusIsNull() {
            addCriterion("disposal_status is null");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusIsNotNull() {
            addCriterion("disposal_status is not null");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusEqualTo(String value) {
            addCriterion("disposal_status =", value, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusNotEqualTo(String value) {
            addCriterion("disposal_status <>", value, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusGreaterThan(String value) {
            addCriterion("disposal_status >", value, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusGreaterThanOrEqualTo(String value) {
            addCriterion("disposal_status >=", value, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusLessThan(String value) {
            addCriterion("disposal_status <", value, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusLessThanOrEqualTo(String value) {
            addCriterion("disposal_status <=", value, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusLike(String value) {
            addCriterion("disposal_status like", value, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusNotLike(String value) {
            addCriterion("disposal_status not like", value, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusIn(List<String> values) {
            addCriterion("disposal_status in", values, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusNotIn(List<String> values) {
            addCriterion("disposal_status not in", values, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusBetween(String value1, String value2) {
            addCriterion("disposal_status between", value1, value2, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andDisposalStatusNotBetween(String value1, String value2) {
            addCriterion("disposal_status not between", value1, value2, "disposalStatus");
            return (Criteria) this;
        }

        public Criteria andReserved1IsNull() {
            addCriterion("reserved1 is null");
            return (Criteria) this;
        }

        public Criteria andReserved1IsNotNull() {
            addCriterion("reserved1 is not null");
            return (Criteria) this;
        }

        public Criteria andReserved1EqualTo(String value) {
            addCriterion("reserved1 =", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1NotEqualTo(String value) {
            addCriterion("reserved1 <>", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1GreaterThan(String value) {
            addCriterion("reserved1 >", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1GreaterThanOrEqualTo(String value) {
            addCriterion("reserved1 >=", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1LessThan(String value) {
            addCriterion("reserved1 <", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1LessThanOrEqualTo(String value) {
            addCriterion("reserved1 <=", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1Like(String value) {
            addCriterion("reserved1 like", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1NotLike(String value) {
            addCriterion("reserved1 not like", value, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1In(List<String> values) {
            addCriterion("reserved1 in", values, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1NotIn(List<String> values) {
            addCriterion("reserved1 not in", values, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1Between(String value1, String value2) {
            addCriterion("reserved1 between", value1, value2, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved1NotBetween(String value1, String value2) {
            addCriterion("reserved1 not between", value1, value2, "reserved1");
            return (Criteria) this;
        }

        public Criteria andReserved2IsNull() {
            addCriterion("reserved2 is null");
            return (Criteria) this;
        }

        public Criteria andReserved2IsNotNull() {
            addCriterion("reserved2 is not null");
            return (Criteria) this;
        }

        public Criteria andReserved2EqualTo(String value) {
            addCriterion("reserved2 =", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotEqualTo(String value) {
            addCriterion("reserved2 <>", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2GreaterThan(String value) {
            addCriterion("reserved2 >", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2GreaterThanOrEqualTo(String value) {
            addCriterion("reserved2 >=", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2LessThan(String value) {
            addCriterion("reserved2 <", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2LessThanOrEqualTo(String value) {
            addCriterion("reserved2 <=", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2Like(String value) {
            addCriterion("reserved2 like", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotLike(String value) {
            addCriterion("reserved2 not like", value, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2In(List<String> values) {
            addCriterion("reserved2 in", values, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotIn(List<String> values) {
            addCriterion("reserved2 not in", values, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2Between(String value1, String value2) {
            addCriterion("reserved2 between", value1, value2, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved2NotBetween(String value1, String value2) {
            addCriterion("reserved2 not between", value1, value2, "reserved2");
            return (Criteria) this;
        }

        public Criteria andReserved3IsNull() {
            addCriterion("reserved3 is null");
            return (Criteria) this;
        }

        public Criteria andReserved3IsNotNull() {
            addCriterion("reserved3 is not null");
            return (Criteria) this;
        }

        public Criteria andReserved3EqualTo(String value) {
            addCriterion("reserved3 =", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotEqualTo(String value) {
            addCriterion("reserved3 <>", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3GreaterThan(String value) {
            addCriterion("reserved3 >", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3GreaterThanOrEqualTo(String value) {
            addCriterion("reserved3 >=", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3LessThan(String value) {
            addCriterion("reserved3 <", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3LessThanOrEqualTo(String value) {
            addCriterion("reserved3 <=", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3Like(String value) {
            addCriterion("reserved3 like", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotLike(String value) {
            addCriterion("reserved3 not like", value, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3In(List<String> values) {
            addCriterion("reserved3 in", values, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotIn(List<String> values) {
            addCriterion("reserved3 not in", values, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3Between(String value1, String value2) {
            addCriterion("reserved3 between", value1, value2, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved3NotBetween(String value1, String value2) {
            addCriterion("reserved3 not between", value1, value2, "reserved3");
            return (Criteria) this;
        }

        public Criteria andReserved4IsNull() {
            addCriterion("reserved4 is null");
            return (Criteria) this;
        }

        public Criteria andReserved4IsNotNull() {
            addCriterion("reserved4 is not null");
            return (Criteria) this;
        }

        public Criteria andReserved4EqualTo(String value) {
            addCriterion("reserved4 =", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotEqualTo(String value) {
            addCriterion("reserved4 <>", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4GreaterThan(String value) {
            addCriterion("reserved4 >", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4GreaterThanOrEqualTo(String value) {
            addCriterion("reserved4 >=", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4LessThan(String value) {
            addCriterion("reserved4 <", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4LessThanOrEqualTo(String value) {
            addCriterion("reserved4 <=", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4Like(String value) {
            addCriterion("reserved4 like", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotLike(String value) {
            addCriterion("reserved4 not like", value, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4In(List<String> values) {
            addCriterion("reserved4 in", values, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotIn(List<String> values) {
            addCriterion("reserved4 not in", values, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4Between(String value1, String value2) {
            addCriterion("reserved4 between", value1, value2, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved4NotBetween(String value1, String value2) {
            addCriterion("reserved4 not between", value1, value2, "reserved4");
            return (Criteria) this;
        }

        public Criteria andReserved5IsNull() {
            addCriterion("reserved5 is null");
            return (Criteria) this;
        }

        public Criteria andReserved5IsNotNull() {
            addCriterion("reserved5 is not null");
            return (Criteria) this;
        }

        public Criteria andReserved5EqualTo(String value) {
            addCriterion("reserved5 =", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotEqualTo(String value) {
            addCriterion("reserved5 <>", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5GreaterThan(String value) {
            addCriterion("reserved5 >", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5GreaterThanOrEqualTo(String value) {
            addCriterion("reserved5 >=", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5LessThan(String value) {
            addCriterion("reserved5 <", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5LessThanOrEqualTo(String value) {
            addCriterion("reserved5 <=", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5Like(String value) {
            addCriterion("reserved5 like", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotLike(String value) {
            addCriterion("reserved5 not like", value, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5In(List<String> values) {
            addCriterion("reserved5 in", values, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotIn(List<String> values) {
            addCriterion("reserved5 not in", values, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5Between(String value1, String value2) {
            addCriterion("reserved5 between", value1, value2, "reserved5");
            return (Criteria) this;
        }

        public Criteria andReserved5NotBetween(String value1, String value2) {
            addCriterion("reserved5 not between", value1, value2, "reserved5");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusIsNull() {
            addCriterion("update_status is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusIsNotNull() {
            addCriterion("update_status is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusEqualTo(String value) {
            addCriterion("update_status =", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotEqualTo(String value) {
            addCriterion("update_status <>", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusGreaterThan(String value) {
            addCriterion("update_status >", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusGreaterThanOrEqualTo(String value) {
            addCriterion("update_status >=", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusLessThan(String value) {
            addCriterion("update_status <", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusLessThanOrEqualTo(String value) {
            addCriterion("update_status <=", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusLike(String value) {
            addCriterion("update_status like", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotLike(String value) {
            addCriterion("update_status not like", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusIn(List<String> values) {
            addCriterion("update_status in", values, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotIn(List<String> values) {
            addCriterion("update_status not in", values, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusBetween(String value1, String value2) {
            addCriterion("update_status between", value1, value2, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotBetween(String value1, String value2) {
            addCriterion("update_status not between", value1, value2, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

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