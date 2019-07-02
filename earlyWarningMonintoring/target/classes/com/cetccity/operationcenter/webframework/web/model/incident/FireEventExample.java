package com.cetccity.operationcenter.webframework.web.model.incident;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FireEventExample {
    private Integer startRow;

    private Integer pageSize;

    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FireEventExample() {
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

        public Criteria andEmergencyNameIsNull() {
            addCriterion("emergency_name is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameIsNotNull() {
            addCriterion("emergency_name is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameEqualTo(String value) {
            addCriterion("emergency_name =", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameNotEqualTo(String value) {
            addCriterion("emergency_name <>", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameGreaterThan(String value) {
            addCriterion("emergency_name >", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameGreaterThanOrEqualTo(String value) {
            addCriterion("emergency_name >=", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameLessThan(String value) {
            addCriterion("emergency_name <", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameLessThanOrEqualTo(String value) {
            addCriterion("emergency_name <=", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameLike(String value) {
            addCriterion("emergency_name like", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameNotLike(String value) {
            addCriterion("emergency_name not like", value, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameIn(List<String> values) {
            addCriterion("emergency_name in", values, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameNotIn(List<String> values) {
            addCriterion("emergency_name not in", values, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameBetween(String value1, String value2) {
            addCriterion("emergency_name between", value1, value2, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyNameNotBetween(String value1, String value2) {
            addCriterion("emergency_name not between", value1, value2, "emergencyName");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryIsNull() {
            addCriterion("emergency_category is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryIsNotNull() {
            addCriterion("emergency_category is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryEqualTo(String value) {
            addCriterion("emergency_category =", value, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryNotEqualTo(String value) {
            addCriterion("emergency_category <>", value, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryGreaterThan(String value) {
            addCriterion("emergency_category >", value, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("emergency_category >=", value, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryLessThan(String value) {
            addCriterion("emergency_category <", value, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryLessThanOrEqualTo(String value) {
            addCriterion("emergency_category <=", value, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryLike(String value) {
            addCriterion("emergency_category like", value, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryNotLike(String value) {
            addCriterion("emergency_category not like", value, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryIn(List<String> values) {
            addCriterion("emergency_category in", values, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryNotIn(List<String> values) {
            addCriterion("emergency_category not in", values, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryBetween(String value1, String value2) {
            addCriterion("emergency_category between", value1, value2, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyCategoryNotBetween(String value1, String value2) {
            addCriterion("emergency_category not between", value1, value2, "emergencyCategory");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeIsNull() {
            addCriterion("emergency_time is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeIsNotNull() {
            addCriterion("emergency_time is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeEqualTo(String value) {
            addCriterion("emergency_time =", value, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeNotEqualTo(String value) {
            addCriterion("emergency_time <>", value, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeGreaterThan(String value) {
            addCriterion("emergency_time >", value, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("emergency_time >=", value, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeLessThan(String value) {
            addCriterion("emergency_time <", value, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeLessThanOrEqualTo(String value) {
            addCriterion("emergency_time <=", value, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeLike(String value) {
            addCriterion("emergency_time like", value, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeNotLike(String value) {
            addCriterion("emergency_time not like", value, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeIn(List<String> values) {
            addCriterion("emergency_time in", values, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeNotIn(List<String> values) {
            addCriterion("emergency_time not in", values, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeBetween(String value1, String value2) {
            addCriterion("emergency_time between", value1, value2, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyTimeNotBetween(String value1, String value2) {
            addCriterion("emergency_time not between", value1, value2, "emergencyTime");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressIsNull() {
            addCriterion("emergency_address is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressIsNotNull() {
            addCriterion("emergency_address is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressEqualTo(String value) {
            addCriterion("emergency_address =", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressNotEqualTo(String value) {
            addCriterion("emergency_address <>", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressGreaterThan(String value) {
            addCriterion("emergency_address >", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("emergency_address >=", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressLessThan(String value) {
            addCriterion("emergency_address <", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressLessThanOrEqualTo(String value) {
            addCriterion("emergency_address <=", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressLike(String value) {
            addCriterion("emergency_address like", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressNotLike(String value) {
            addCriterion("emergency_address not like", value, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressIn(List<String> values) {
            addCriterion("emergency_address in", values, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressNotIn(List<String> values) {
            addCriterion("emergency_address not in", values, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressBetween(String value1, String value2) {
            addCriterion("emergency_address between", value1, value2, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andEmergencyAddressNotBetween(String value1, String value2) {
            addCriterion("emergency_address not between", value1, value2, "emergencyAddress");
            return (Criteria) this;
        }

        public Criteria andJdIsNull() {
            addCriterion("jd is null");
            return (Criteria) this;
        }

        public Criteria andJdIsNotNull() {
            addCriterion("jd is not null");
            return (Criteria) this;
        }

        public Criteria andJdEqualTo(String value) {
            addCriterion("jd =", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotEqualTo(String value) {
            addCriterion("jd <>", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdGreaterThan(String value) {
            addCriterion("jd >", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdGreaterThanOrEqualTo(String value) {
            addCriterion("jd >=", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdLessThan(String value) {
            addCriterion("jd <", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdLessThanOrEqualTo(String value) {
            addCriterion("jd <=", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdLike(String value) {
            addCriterion("jd like", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotLike(String value) {
            addCriterion("jd not like", value, "jd");
            return (Criteria) this;
        }

        public Criteria andJdIn(List<String> values) {
            addCriterion("jd in", values, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotIn(List<String> values) {
            addCriterion("jd not in", values, "jd");
            return (Criteria) this;
        }

        public Criteria andJdBetween(String value1, String value2) {
            addCriterion("jd between", value1, value2, "jd");
            return (Criteria) this;
        }

        public Criteria andJdNotBetween(String value1, String value2) {
            addCriterion("jd not between", value1, value2, "jd");
            return (Criteria) this;
        }

        public Criteria andWdIsNull() {
            addCriterion("wd is null");
            return (Criteria) this;
        }

        public Criteria andWdIsNotNull() {
            addCriterion("wd is not null");
            return (Criteria) this;
        }

        public Criteria andWdEqualTo(String value) {
            addCriterion("wd =", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotEqualTo(String value) {
            addCriterion("wd <>", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdGreaterThan(String value) {
            addCriterion("wd >", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdGreaterThanOrEqualTo(String value) {
            addCriterion("wd >=", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLessThan(String value) {
            addCriterion("wd <", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLessThanOrEqualTo(String value) {
            addCriterion("wd <=", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdLike(String value) {
            addCriterion("wd like", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotLike(String value) {
            addCriterion("wd not like", value, "wd");
            return (Criteria) this;
        }

        public Criteria andWdIn(List<String> values) {
            addCriterion("wd in", values, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotIn(List<String> values) {
            addCriterion("wd not in", values, "wd");
            return (Criteria) this;
        }

        public Criteria andWdBetween(String value1, String value2) {
            addCriterion("wd between", value1, value2, "wd");
            return (Criteria) this;
        }

        public Criteria andWdNotBetween(String value1, String value2) {
            addCriterion("wd not between", value1, value2, "wd");
            return (Criteria) this;
        }

        public Criteria andJd84IsNull() {
            addCriterion("jd84 is null");
            return (Criteria) this;
        }

        public Criteria andJd84IsNotNull() {
            addCriterion("jd84 is not null");
            return (Criteria) this;
        }

        public Criteria andJd84EqualTo(String value) {
            addCriterion("jd84 =", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotEqualTo(String value) {
            addCriterion("jd84 <>", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84GreaterThan(String value) {
            addCriterion("jd84 >", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84GreaterThanOrEqualTo(String value) {
            addCriterion("jd84 >=", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84LessThan(String value) {
            addCriterion("jd84 <", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84LessThanOrEqualTo(String value) {
            addCriterion("jd84 <=", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84Like(String value) {
            addCriterion("jd84 like", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotLike(String value) {
            addCriterion("jd84 not like", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84In(List<String> values) {
            addCriterion("jd84 in", values, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotIn(List<String> values) {
            addCriterion("jd84 not in", values, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84Between(String value1, String value2) {
            addCriterion("jd84 between", value1, value2, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotBetween(String value1, String value2) {
            addCriterion("jd84 not between", value1, value2, "jd84");
            return (Criteria) this;
        }

        public Criteria andWd84IsNull() {
            addCriterion("wd84 is null");
            return (Criteria) this;
        }

        public Criteria andWd84IsNotNull() {
            addCriterion("wd84 is not null");
            return (Criteria) this;
        }

        public Criteria andWd84EqualTo(String value) {
            addCriterion("wd84 =", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotEqualTo(String value) {
            addCriterion("wd84 <>", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84GreaterThan(String value) {
            addCriterion("wd84 >", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84GreaterThanOrEqualTo(String value) {
            addCriterion("wd84 >=", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84LessThan(String value) {
            addCriterion("wd84 <", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84LessThanOrEqualTo(String value) {
            addCriterion("wd84 <=", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84Like(String value) {
            addCriterion("wd84 like", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotLike(String value) {
            addCriterion("wd84 not like", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84In(List<String> values) {
            addCriterion("wd84 in", values, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotIn(List<String> values) {
            addCriterion("wd84 not in", values, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84Between(String value1, String value2) {
            addCriterion("wd84 between", value1, value2, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotBetween(String value1, String value2) {
            addCriterion("wd84 not between", value1, value2, "wd84");
            return (Criteria) this;
        }

        public Criteria andReportingUnitIsNull() {
            addCriterion("reporting_unit is null");
            return (Criteria) this;
        }

        public Criteria andReportingUnitIsNotNull() {
            addCriterion("reporting_unit is not null");
            return (Criteria) this;
        }

        public Criteria andReportingUnitEqualTo(String value) {
            addCriterion("reporting_unit =", value, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitNotEqualTo(String value) {
            addCriterion("reporting_unit <>", value, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitGreaterThan(String value) {
            addCriterion("reporting_unit >", value, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitGreaterThanOrEqualTo(String value) {
            addCriterion("reporting_unit >=", value, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitLessThan(String value) {
            addCriterion("reporting_unit <", value, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitLessThanOrEqualTo(String value) {
            addCriterion("reporting_unit <=", value, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitLike(String value) {
            addCriterion("reporting_unit like", value, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitNotLike(String value) {
            addCriterion("reporting_unit not like", value, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitIn(List<String> values) {
            addCriterion("reporting_unit in", values, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitNotIn(List<String> values) {
            addCriterion("reporting_unit not in", values, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitBetween(String value1, String value2) {
            addCriterion("reporting_unit between", value1, value2, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andReportingUnitNotBetween(String value1, String value2) {
            addCriterion("reporting_unit not between", value1, value2, "reportingUnit");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeIsNull() {
            addCriterion("emergency_grade is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeIsNotNull() {
            addCriterion("emergency_grade is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeEqualTo(String value) {
            addCriterion("emergency_grade =", value, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeNotEqualTo(String value) {
            addCriterion("emergency_grade <>", value, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeGreaterThan(String value) {
            addCriterion("emergency_grade >", value, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeGreaterThanOrEqualTo(String value) {
            addCriterion("emergency_grade >=", value, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeLessThan(String value) {
            addCriterion("emergency_grade <", value, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeLessThanOrEqualTo(String value) {
            addCriterion("emergency_grade <=", value, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeLike(String value) {
            addCriterion("emergency_grade like", value, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeNotLike(String value) {
            addCriterion("emergency_grade not like", value, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeIn(List<String> values) {
            addCriterion("emergency_grade in", values, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeNotIn(List<String> values) {
            addCriterion("emergency_grade not in", values, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeBetween(String value1, String value2) {
            addCriterion("emergency_grade between", value1, value2, "emergencyGrade");
            return (Criteria) this;
        }

        public Criteria andEmergencyGradeNotBetween(String value1, String value2) {
            addCriterion("emergency_grade not between", value1, value2, "emergencyGrade");
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

        public Criteria andEmergencyDescIsNull() {
            addCriterion("emergency_desc is null");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescIsNotNull() {
            addCriterion("emergency_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescEqualTo(String value) {
            addCriterion("emergency_desc =", value, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescNotEqualTo(String value) {
            addCriterion("emergency_desc <>", value, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescGreaterThan(String value) {
            addCriterion("emergency_desc >", value, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescGreaterThanOrEqualTo(String value) {
            addCriterion("emergency_desc >=", value, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescLessThan(String value) {
            addCriterion("emergency_desc <", value, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescLessThanOrEqualTo(String value) {
            addCriterion("emergency_desc <=", value, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescLike(String value) {
            addCriterion("emergency_desc like", value, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescNotLike(String value) {
            addCriterion("emergency_desc not like", value, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescIn(List<String> values) {
            addCriterion("emergency_desc in", values, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescNotIn(List<String> values) {
            addCriterion("emergency_desc not in", values, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescBetween(String value1, String value2) {
            addCriterion("emergency_desc between", value1, value2, "emergencyDesc");
            return (Criteria) this;
        }

        public Criteria andEmergencyDescNotBetween(String value1, String value2) {
            addCriterion("emergency_desc not between", value1, value2, "emergencyDesc");
            return (Criteria) this;
        }






        public Criteria andOnSiteLiaisonIsNull() {
            addCriterion("on_site_liaison is null");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonIsNotNull() {
            addCriterion("on_site_liaison is not null");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonEqualTo(String value) {
            addCriterion("on_site_liaison =", value, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonNotEqualTo(String value) {
            addCriterion("on_site_liaison <>", value, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonGreaterThan(String value) {
            addCriterion("on_site_liaison >", value, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonGreaterThanOrEqualTo(String value) {
            addCriterion("on_site_liaison >=", value, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonLessThan(String value) {
            addCriterion("on_site_liaison <", value, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonLessThanOrEqualTo(String value) {
            addCriterion("on_site_liaison <=", value, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonLike(String value) {
            addCriterion("on_site_liaison like", value, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonNotLike(String value) {
            addCriterion("on_site_liaison not like", value, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonIn(List<String> values) {
            addCriterion("on_site_liaison in", values, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonNotIn(List<String> values) {
            addCriterion("on_site_liaison not in", values, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonBetween(String value1, String value2) {
            addCriterion("on_site_liaison between", value1, value2, "onSiteLiaison");
            return (Criteria) this;
        }

        public Criteria andOnSiteLiaisonNotBetween(String value1, String value2) {
            addCriterion("on_site_liaison not between", value1, value2, "onSiteLiaison");
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

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}