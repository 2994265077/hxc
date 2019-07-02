package com.cetccity.operationcenter.webframework.web.model.video;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class VideoPoliceModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VideoPoliceModelExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCameraidIsNull() {
            addCriterion("CAMERAID is null");
            return (Criteria) this;
        }

        public Criteria andCameraidIsNotNull() {
            addCriterion("CAMERAID is not null");
            return (Criteria) this;
        }

        public Criteria andCameraidEqualTo(String value) {
            addCriterion("CAMERAID =", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidNotEqualTo(String value) {
            addCriterion("CAMERAID <>", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidGreaterThan(String value) {
            addCriterion("CAMERAID >", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidGreaterThanOrEqualTo(String value) {
            addCriterion("CAMERAID >=", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidLessThan(String value) {
            addCriterion("CAMERAID <", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidLessThanOrEqualTo(String value) {
            addCriterion("CAMERAID <=", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidLike(String value) {
            addCriterion("CAMERAID like", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidNotLike(String value) {
            addCriterion("CAMERAID not like", value, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidIn(List<String> values) {
            addCriterion("CAMERAID in", values, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidNotIn(List<String> values) {
            addCriterion("CAMERAID not in", values, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidBetween(String value1, String value2) {
            addCriterion("CAMERAID between", value1, value2, "cameraid");
            return (Criteria) this;
        }

        public Criteria andCameraidNotBetween(String value1, String value2) {
            addCriterion("CAMERAID not between", value1, value2, "cameraid");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNull() {
            addCriterion("DISTRICT is null");
            return (Criteria) this;
        }

        public Criteria andDistrictIsNotNull() {
            addCriterion("DISTRICT is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictEqualTo(String value) {
            addCriterion("DISTRICT =", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotEqualTo(String value) {
            addCriterion("DISTRICT <>", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThan(String value) {
            addCriterion("DISTRICT >", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictGreaterThanOrEqualTo(String value) {
            addCriterion("DISTRICT >=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThan(String value) {
            addCriterion("DISTRICT <", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLessThanOrEqualTo(String value) {
            addCriterion("DISTRICT <=", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictLike(String value) {
            addCriterion("DISTRICT like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotLike(String value) {
            addCriterion("DISTRICT not like", value, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictIn(List<String> values) {
            addCriterion("DISTRICT in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotIn(List<String> values) {
            addCriterion("DISTRICT not in", values, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictBetween(String value1, String value2) {
            addCriterion("DISTRICT between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andDistrictNotBetween(String value1, String value2) {
            addCriterion("DISTRICT not between", value1, value2, "district");
            return (Criteria) this;
        }

        public Criteria andGbCodeIsNull() {
            addCriterion("GB_CODE is null");
            return (Criteria) this;
        }

        public Criteria andGbCodeIsNotNull() {
            addCriterion("GB_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andGbCodeEqualTo(String value) {
            addCriterion("GB_CODE =", value, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeNotEqualTo(String value) {
            addCriterion("GB_CODE <>", value, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeGreaterThan(String value) {
            addCriterion("GB_CODE >", value, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeGreaterThanOrEqualTo(String value) {
            addCriterion("GB_CODE >=", value, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeLessThan(String value) {
            addCriterion("GB_CODE <", value, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeLessThanOrEqualTo(String value) {
            addCriterion("GB_CODE <=", value, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeLike(String value) {
            addCriterion("GB_CODE like", value, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeNotLike(String value) {
            addCriterion("GB_CODE not like", value, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeIn(List<String> values) {
            addCriterion("GB_CODE in", values, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeNotIn(List<String> values) {
            addCriterion("GB_CODE not in", values, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeBetween(String value1, String value2) {
            addCriterion("GB_CODE between", value1, value2, "gbCode");
            return (Criteria) this;
        }

        public Criteria andGbCodeNotBetween(String value1, String value2) {
            addCriterion("GB_CODE not between", value1, value2, "gbCode");
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

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
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

        public Criteria andNameEqualTo(String value) {
            addCriterion("`NAME` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`NAME` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`NAME` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`NAME` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`NAME` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`NAME` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`NAME` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`NAME` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`NAME` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`NAME` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`NAME` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`NAME` not between", value1, value2, "name");
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

        public Criteria andJd84EqualTo(String value) {
            addCriterion("JD84 =", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotEqualTo(String value) {
            addCriterion("JD84 <>", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84GreaterThan(String value) {
            addCriterion("JD84 >", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84GreaterThanOrEqualTo(String value) {
            addCriterion("JD84 >=", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84LessThan(String value) {
            addCriterion("JD84 <", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84LessThanOrEqualTo(String value) {
            addCriterion("JD84 <=", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84Like(String value) {
            addCriterion("JD84 like", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotLike(String value) {
            addCriterion("JD84 not like", value, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84In(List<String> values) {
            addCriterion("JD84 in", values, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotIn(List<String> values) {
            addCriterion("JD84 not in", values, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84Between(String value1, String value2) {
            addCriterion("JD84 between", value1, value2, "jd84");
            return (Criteria) this;
        }

        public Criteria andJd84NotBetween(String value1, String value2) {
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

        public Criteria andWd84EqualTo(String value) {
            addCriterion("WD84 =", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotEqualTo(String value) {
            addCriterion("WD84 <>", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84GreaterThan(String value) {
            addCriterion("WD84 >", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84GreaterThanOrEqualTo(String value) {
            addCriterion("WD84 >=", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84LessThan(String value) {
            addCriterion("WD84 <", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84LessThanOrEqualTo(String value) {
            addCriterion("WD84 <=", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84Like(String value) {
            addCriterion("WD84 like", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotLike(String value) {
            addCriterion("WD84 not like", value, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84In(List<String> values) {
            addCriterion("WD84 in", values, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotIn(List<String> values) {
            addCriterion("WD84 not in", values, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84Between(String value1, String value2) {
            addCriterion("WD84 between", value1, value2, "wd84");
            return (Criteria) this;
        }

        public Criteria andWd84NotBetween(String value1, String value2) {
            addCriterion("WD84 not between", value1, value2, "wd84");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("IP is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("IP is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("IP =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("IP <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("IP >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("IP >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("IP <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("IP <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("IP like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("IP not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("IP in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("IP not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("IP between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("IP not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("START_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("START_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("START_DATE =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("START_DATE <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("START_DATE >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("START_DATE >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("START_DATE <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("START_DATE <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("START_DATE like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("START_DATE not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("START_DATE in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("START_DATE not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("START_DATE between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("START_DATE not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`STATE` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`STATE` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("`STATE` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("`STATE` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("`STATE` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("`STATE` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("`STATE` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("`STATE` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("`STATE` like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("`STATE` not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("`STATE` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("`STATE` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("`STATE` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("`STATE` not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andRecordIsNull() {
            addCriterion("RECORD is null");
            return (Criteria) this;
        }

        public Criteria andRecordIsNotNull() {
            addCriterion("RECORD is not null");
            return (Criteria) this;
        }

        public Criteria andRecordEqualTo(String value) {
            addCriterion("RECORD =", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordNotEqualTo(String value) {
            addCriterion("RECORD <>", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordGreaterThan(String value) {
            addCriterion("RECORD >", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD >=", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordLessThan(String value) {
            addCriterion("RECORD <", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordLessThanOrEqualTo(String value) {
            addCriterion("RECORD <=", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordLike(String value) {
            addCriterion("RECORD like", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordNotLike(String value) {
            addCriterion("RECORD not like", value, "record");
            return (Criteria) this;
        }

        public Criteria andRecordIn(List<String> values) {
            addCriterion("RECORD in", values, "record");
            return (Criteria) this;
        }

        public Criteria andRecordNotIn(List<String> values) {
            addCriterion("RECORD not in", values, "record");
            return (Criteria) this;
        }

        public Criteria andRecordBetween(String value1, String value2) {
            addCriterion("RECORD between", value1, value2, "record");
            return (Criteria) this;
        }

        public Criteria andRecordNotBetween(String value1, String value2) {
            addCriterion("RECORD not between", value1, value2, "record");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNull() {
            addCriterion("CATEGORY is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIsNotNull() {
            addCriterion("CATEGORY is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryEqualTo(String value) {
            addCriterion("CATEGORY =", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotEqualTo(String value) {
            addCriterion("CATEGORY <>", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThan(String value) {
            addCriterion("CATEGORY >", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY >=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThan(String value) {
            addCriterion("CATEGORY <", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY <=", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryLike(String value) {
            addCriterion("CATEGORY like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotLike(String value) {
            addCriterion("CATEGORY not like", value, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryIn(List<String> values) {
            addCriterion("CATEGORY in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotIn(List<String> values) {
            addCriterion("CATEGORY not in", values, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryBetween(String value1, String value2) {
            addCriterion("CATEGORY between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andCategoryNotBetween(String value1, String value2) {
            addCriterion("CATEGORY not between", value1, value2, "category");
            return (Criteria) this;
        }

        public Criteria andAppearanceIsNull() {
            addCriterion("APPEARANCE is null");
            return (Criteria) this;
        }

        public Criteria andAppearanceIsNotNull() {
            addCriterion("APPEARANCE is not null");
            return (Criteria) this;
        }

        public Criteria andAppearanceEqualTo(String value) {
            addCriterion("APPEARANCE =", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotEqualTo(String value) {
            addCriterion("APPEARANCE <>", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceGreaterThan(String value) {
            addCriterion("APPEARANCE >", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceGreaterThanOrEqualTo(String value) {
            addCriterion("APPEARANCE >=", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceLessThan(String value) {
            addCriterion("APPEARANCE <", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceLessThanOrEqualTo(String value) {
            addCriterion("APPEARANCE <=", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceLike(String value) {
            addCriterion("APPEARANCE like", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotLike(String value) {
            addCriterion("APPEARANCE not like", value, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceIn(List<String> values) {
            addCriterion("APPEARANCE in", values, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotIn(List<String> values) {
            addCriterion("APPEARANCE not in", values, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceBetween(String value1, String value2) {
            addCriterion("APPEARANCE between", value1, value2, "appearance");
            return (Criteria) this;
        }

        public Criteria andAppearanceNotBetween(String value1, String value2) {
            addCriterion("APPEARANCE not between", value1, value2, "appearance");
            return (Criteria) this;
        }

        public Criteria andResolutionIsNull() {
            addCriterion("RESOLUTION is null");
            return (Criteria) this;
        }

        public Criteria andResolutionIsNotNull() {
            addCriterion("RESOLUTION is not null");
            return (Criteria) this;
        }

        public Criteria andResolutionEqualTo(String value) {
            addCriterion("RESOLUTION =", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotEqualTo(String value) {
            addCriterion("RESOLUTION <>", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionGreaterThan(String value) {
            addCriterion("RESOLUTION >", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionGreaterThanOrEqualTo(String value) {
            addCriterion("RESOLUTION >=", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionLessThan(String value) {
            addCriterion("RESOLUTION <", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionLessThanOrEqualTo(String value) {
            addCriterion("RESOLUTION <=", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionLike(String value) {
            addCriterion("RESOLUTION like", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotLike(String value) {
            addCriterion("RESOLUTION not like", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionIn(List<String> values) {
            addCriterion("RESOLUTION in", values, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotIn(List<String> values) {
            addCriterion("RESOLUTION not in", values, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionBetween(String value1, String value2) {
            addCriterion("RESOLUTION between", value1, value2, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotBetween(String value1, String value2) {
            addCriterion("RESOLUTION not between", value1, value2, "resolution");
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
            addCriterionForJDBCDate("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATE_TIME not between", value1, value2, "createTime");
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

        public Criteria andUpdateStatusEqualTo(String value) {
            addCriterion("UPDATE_STATUS =", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotEqualTo(String value) {
            addCriterion("UPDATE_STATUS <>", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusGreaterThan(String value) {
            addCriterion("UPDATE_STATUS >", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_STATUS >=", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusLessThan(String value) {
            addCriterion("UPDATE_STATUS <", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_STATUS <=", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusLike(String value) {
            addCriterion("UPDATE_STATUS like", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotLike(String value) {
            addCriterion("UPDATE_STATUS not like", value, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusIn(List<String> values) {
            addCriterion("UPDATE_STATUS in", values, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotIn(List<String> values) {
            addCriterion("UPDATE_STATUS not in", values, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusBetween(String value1, String value2) {
            addCriterion("UPDATE_STATUS between", value1, value2, "updateStatus");
            return (Criteria) this;
        }

        public Criteria andUpdateStatusNotBetween(String value1, String value2) {
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

        public Criteria andUuidEqualTo(String value) {
            addCriterion("UUID =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("UUID <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("UUID >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("UUID >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("UUID <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("UUID <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("UUID like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("UUID not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("UUID in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("UUID not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("UUID between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("UUID not between", value1, value2, "uuid");
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