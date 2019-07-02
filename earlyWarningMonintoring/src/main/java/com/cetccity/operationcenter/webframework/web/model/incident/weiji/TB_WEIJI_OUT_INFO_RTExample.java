package com.cetccity.operationcenter.webframework.web.model.incident.weiji;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TB_WEIJI_OUT_INFO_RTExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TB_WEIJI_OUT_INFO_RTExample() {
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

        public Criteria andOrgCodeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("org_code =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("org_code >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("org_code <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("org_code like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("org_code not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("org_code in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("org_name is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("org_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("org_name =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("org_name <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("org_name >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("org_name >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("org_name <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("org_name <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("org_name like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("org_name not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("org_name in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("org_name not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("org_name between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("org_name not between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIsNull() {
            addCriterion("dept_code is null");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIsNotNull() {
            addCriterion("dept_code is not null");
            return (Criteria) this;
        }

        public Criteria andDeptCodeEqualTo(String value) {
            addCriterion("dept_code =", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotEqualTo(String value) {
            addCriterion("dept_code <>", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeGreaterThan(String value) {
            addCriterion("dept_code >", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeGreaterThanOrEqualTo(String value) {
            addCriterion("dept_code >=", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLessThan(String value) {
            addCriterion("dept_code <", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLessThanOrEqualTo(String value) {
            addCriterion("dept_code <=", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeLike(String value) {
            addCriterion("dept_code like", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotLike(String value) {
            addCriterion("dept_code not like", value, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeIn(List<String> values) {
            addCriterion("dept_code in", values, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotIn(List<String> values) {
            addCriterion("dept_code not in", values, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeBetween(String value1, String value2) {
            addCriterion("dept_code between", value1, value2, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptCodeNotBetween(String value1, String value2) {
            addCriterion("dept_code not between", value1, value2, "deptCode");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("dept_name is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("dept_name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("dept_name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("dept_name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("dept_name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("dept_name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("dept_name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("dept_name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("dept_name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("dept_name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("dept_name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("dept_name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("dept_name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andRegisterNoIsNull() {
            addCriterion("register_no is null");
            return (Criteria) this;
        }

        public Criteria andRegisterNoIsNotNull() {
            addCriterion("register_no is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterNoEqualTo(Integer value) {
            addCriterion("register_no =", value, "registerNo");
            return (Criteria) this;
        }

        public Criteria andRegisterNoNotEqualTo(Integer value) {
            addCriterion("register_no <>", value, "registerNo");
            return (Criteria) this;
        }

        public Criteria andRegisterNoGreaterThan(Integer value) {
            addCriterion("register_no >", value, "registerNo");
            return (Criteria) this;
        }

        public Criteria andRegisterNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("register_no >=", value, "registerNo");
            return (Criteria) this;
        }

        public Criteria andRegisterNoLessThan(Integer value) {
            addCriterion("register_no <", value, "registerNo");
            return (Criteria) this;
        }

        public Criteria andRegisterNoLessThanOrEqualTo(Integer value) {
            addCriterion("register_no <=", value, "registerNo");
            return (Criteria) this;
        }

        public Criteria andRegisterNoIn(List<Integer> values) {
            addCriterion("register_no in", values, "registerNo");
            return (Criteria) this;
        }

        public Criteria andRegisterNoNotIn(List<Integer> values) {
            addCriterion("register_no not in", values, "registerNo");
            return (Criteria) this;
        }

        public Criteria andRegisterNoBetween(Integer value1, Integer value2) {
            addCriterion("register_no between", value1, value2, "registerNo");
            return (Criteria) this;
        }

        public Criteria andRegisterNoNotBetween(Integer value1, Integer value2) {
            addCriterion("register_no not between", value1, value2, "registerNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoIsNull() {
            addCriterion("visit_no is null");
            return (Criteria) this;
        }

        public Criteria andVisitNoIsNotNull() {
            addCriterion("visit_no is not null");
            return (Criteria) this;
        }

        public Criteria andVisitNoEqualTo(Integer value) {
            addCriterion("visit_no =", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoNotEqualTo(Integer value) {
            addCriterion("visit_no <>", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoGreaterThan(Integer value) {
            addCriterion("visit_no >", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("visit_no >=", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoLessThan(Integer value) {
            addCriterion("visit_no <", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoLessThanOrEqualTo(Integer value) {
            addCriterion("visit_no <=", value, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoIn(List<Integer> values) {
            addCriterion("visit_no in", values, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoNotIn(List<Integer> values) {
            addCriterion("visit_no not in", values, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoBetween(Integer value1, Integer value2) {
            addCriterion("visit_no between", value1, value2, "visitNo");
            return (Criteria) this;
        }

        public Criteria andVisitNoNotBetween(Integer value1, Integer value2) {
            addCriterion("visit_no not between", value1, value2, "visitNo");
            return (Criteria) this;
        }

        public Criteria andWaitingNoIsNull() {
            addCriterion("waiting_no is null");
            return (Criteria) this;
        }

        public Criteria andWaitingNoIsNotNull() {
            addCriterion("waiting_no is not null");
            return (Criteria) this;
        }

        public Criteria andWaitingNoEqualTo(Integer value) {
            addCriterion("waiting_no =", value, "waitingNo");
            return (Criteria) this;
        }

        public Criteria andWaitingNoNotEqualTo(Integer value) {
            addCriterion("waiting_no <>", value, "waitingNo");
            return (Criteria) this;
        }

        public Criteria andWaitingNoGreaterThan(Integer value) {
            addCriterion("waiting_no >", value, "waitingNo");
            return (Criteria) this;
        }

        public Criteria andWaitingNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("waiting_no >=", value, "waitingNo");
            return (Criteria) this;
        }

        public Criteria andWaitingNoLessThan(Integer value) {
            addCriterion("waiting_no <", value, "waitingNo");
            return (Criteria) this;
        }

        public Criteria andWaitingNoLessThanOrEqualTo(Integer value) {
            addCriterion("waiting_no <=", value, "waitingNo");
            return (Criteria) this;
        }

        public Criteria andWaitingNoIn(List<Integer> values) {
            addCriterion("waiting_no in", values, "waitingNo");
            return (Criteria) this;
        }

        public Criteria andWaitingNoNotIn(List<Integer> values) {
            addCriterion("waiting_no not in", values, "waitingNo");
            return (Criteria) this;
        }

        public Criteria andWaitingNoBetween(Integer value1, Integer value2) {
            addCriterion("waiting_no between", value1, value2, "waitingNo");
            return (Criteria) this;
        }

        public Criteria andWaitingNoNotBetween(Integer value1, Integer value2) {
            addCriterion("waiting_no not between", value1, value2, "waitingNo");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeIsNull() {
            addCriterion("rec_create_time is null");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeIsNotNull() {
            addCriterion("rec_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeEqualTo(String value) {
            addCriterion("rec_create_time =", value, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeNotEqualTo(String value) {
            addCriterion("rec_create_time <>", value, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeGreaterThan(String value) {
            addCriterion("rec_create_time >", value, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("rec_create_time >=", value, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeLessThan(String value) {
            addCriterion("rec_create_time <", value, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("rec_create_time <=", value, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeLike(String value) {
            addCriterion("rec_create_time like", value, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeNotLike(String value) {
            addCriterion("rec_create_time not like", value, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeIn(List<String> values) {
            addCriterion("rec_create_time in", values, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeNotIn(List<String> values) {
            addCriterion("rec_create_time not in", values, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeBetween(String value1, String value2) {
            addCriterion("rec_create_time between", value1, value2, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecCreateTimeNotBetween(String value1, String value2) {
            addCriterion("rec_create_time not between", value1, value2, "recCreateTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeIsNull() {
            addCriterion("rec_modify_time is null");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeIsNotNull() {
            addCriterion("rec_modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeEqualTo(String value) {
            addCriterion("rec_modify_time =", value, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeNotEqualTo(String value) {
            addCriterion("rec_modify_time <>", value, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeGreaterThan(String value) {
            addCriterion("rec_modify_time >", value, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeGreaterThanOrEqualTo(String value) {
            addCriterion("rec_modify_time >=", value, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeLessThan(String value) {
            addCriterion("rec_modify_time <", value, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeLessThanOrEqualTo(String value) {
            addCriterion("rec_modify_time <=", value, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeLike(String value) {
            addCriterion("rec_modify_time like", value, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeNotLike(String value) {
            addCriterion("rec_modify_time not like", value, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeIn(List<String> values) {
            addCriterion("rec_modify_time in", values, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeNotIn(List<String> values) {
            addCriterion("rec_modify_time not in", values, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeBetween(String value1, String value2) {
            addCriterion("rec_modify_time between", value1, value2, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andRecModifyTimeNotBetween(String value1, String value2) {
            addCriterion("rec_modify_time not between", value1, value2, "recModifyTime");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andStreetIsNull() {
            addCriterion("street is null");
            return (Criteria) this;
        }

        public Criteria andStreetIsNotNull() {
            addCriterion("street is not null");
            return (Criteria) this;
        }

        public Criteria andStreetEqualTo(String value) {
            addCriterion("street =", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotEqualTo(String value) {
            addCriterion("street <>", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThan(String value) {
            addCriterion("street >", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetGreaterThanOrEqualTo(String value) {
            addCriterion("street >=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThan(String value) {
            addCriterion("street <", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLessThanOrEqualTo(String value) {
            addCriterion("street <=", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetLike(String value) {
            addCriterion("street like", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotLike(String value) {
            addCriterion("street not like", value, "street");
            return (Criteria) this;
        }

        public Criteria andStreetIn(List<String> values) {
            addCriterion("street in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotIn(List<String> values) {
            addCriterion("street not in", values, "street");
            return (Criteria) this;
        }

        public Criteria andStreetBetween(String value1, String value2) {
            addCriterion("street between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andStreetNotBetween(String value1, String value2) {
            addCriterion("street not between", value1, value2, "street");
            return (Criteria) this;
        }

        public Criteria andCommunityIsNull() {
            addCriterion("community is null");
            return (Criteria) this;
        }

        public Criteria andCommunityIsNotNull() {
            addCriterion("community is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityEqualTo(String value) {
            addCriterion("community =", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityNotEqualTo(String value) {
            addCriterion("community <>", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityGreaterThan(String value) {
            addCriterion("community >", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityGreaterThanOrEqualTo(String value) {
            addCriterion("community >=", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityLessThan(String value) {
            addCriterion("community <", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityLessThanOrEqualTo(String value) {
            addCriterion("community <=", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityLike(String value) {
            addCriterion("community like", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityNotLike(String value) {
            addCriterion("community not like", value, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityIn(List<String> values) {
            addCriterion("community in", values, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityNotIn(List<String> values) {
            addCriterion("community not in", values, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityBetween(String value1, String value2) {
            addCriterion("community between", value1, value2, "community");
            return (Criteria) this;
        }

        public Criteria andCommunityNotBetween(String value1, String value2) {
            addCriterion("community not between", value1, value2, "community");
            return (Criteria) this;
        }

        public Criteria andGridIsNull() {
            addCriterion("grid is null");
            return (Criteria) this;
        }

        public Criteria andGridIsNotNull() {
            addCriterion("grid is not null");
            return (Criteria) this;
        }

        public Criteria andGridEqualTo(String value) {
            addCriterion("grid =", value, "grid");
            return (Criteria) this;
        }

        public Criteria andGridNotEqualTo(String value) {
            addCriterion("grid <>", value, "grid");
            return (Criteria) this;
        }

        public Criteria andGridGreaterThan(String value) {
            addCriterion("grid >", value, "grid");
            return (Criteria) this;
        }

        public Criteria andGridGreaterThanOrEqualTo(String value) {
            addCriterion("grid >=", value, "grid");
            return (Criteria) this;
        }

        public Criteria andGridLessThan(String value) {
            addCriterion("grid <", value, "grid");
            return (Criteria) this;
        }

        public Criteria andGridLessThanOrEqualTo(String value) {
            addCriterion("grid <=", value, "grid");
            return (Criteria) this;
        }

        public Criteria andGridLike(String value) {
            addCriterion("grid like", value, "grid");
            return (Criteria) this;
        }

        public Criteria andGridNotLike(String value) {
            addCriterion("grid not like", value, "grid");
            return (Criteria) this;
        }

        public Criteria andGridIn(List<String> values) {
            addCriterion("grid in", values, "grid");
            return (Criteria) this;
        }

        public Criteria andGridNotIn(List<String> values) {
            addCriterion("grid not in", values, "grid");
            return (Criteria) this;
        }

        public Criteria andGridBetween(String value1, String value2) {
            addCriterion("grid between", value1, value2, "grid");
            return (Criteria) this;
        }

        public Criteria andGridNotBetween(String value1, String value2) {
            addCriterion("grid not between", value1, value2, "grid");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
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