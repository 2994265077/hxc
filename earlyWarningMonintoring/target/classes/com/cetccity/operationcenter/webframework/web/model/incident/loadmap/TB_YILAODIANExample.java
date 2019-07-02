package com.cetccity.operationcenter.webframework.web.model.incident.loadmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TB_YILAODIANExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TB_YILAODIANExample() {
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

        public Criteria andRoadNameIsNull() {
            addCriterion("road_name is null");
            return (Criteria) this;
        }

        public Criteria andRoadNameIsNotNull() {
            addCriterion("road_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoadNameEqualTo(String value) {
            addCriterion("road_name =", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotEqualTo(String value) {
            addCriterion("road_name <>", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameGreaterThan(String value) {
            addCriterion("road_name >", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameGreaterThanOrEqualTo(String value) {
            addCriterion("road_name >=", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameLessThan(String value) {
            addCriterion("road_name <", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameLessThanOrEqualTo(String value) {
            addCriterion("road_name <=", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameLike(String value) {
            addCriterion("road_name like", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotLike(String value) {
            addCriterion("road_name not like", value, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameIn(List<String> values) {
            addCriterion("road_name in", values, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotIn(List<String> values) {
            addCriterion("road_name not in", values, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameBetween(String value1, String value2) {
            addCriterion("road_name between", value1, value2, "roadName");
            return (Criteria) this;
        }

        public Criteria andRoadNameNotBetween(String value1, String value2) {
            addCriterion("road_name not between", value1, value2, "roadName");
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

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1IsNull() {
            addCriterion("street_resp_person_name_1 is null");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1IsNotNull() {
            addCriterion("street_resp_person_name_1 is not null");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1EqualTo(String value) {
            addCriterion("street_resp_person_name_1 =", value, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1NotEqualTo(String value) {
            addCriterion("street_resp_person_name_1 <>", value, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1GreaterThan(String value) {
            addCriterion("street_resp_person_name_1 >", value, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1GreaterThanOrEqualTo(String value) {
            addCriterion("street_resp_person_name_1 >=", value, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1LessThan(String value) {
            addCriterion("street_resp_person_name_1 <", value, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1LessThanOrEqualTo(String value) {
            addCriterion("street_resp_person_name_1 <=", value, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1Like(String value) {
            addCriterion("street_resp_person_name_1 like", value, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1NotLike(String value) {
            addCriterion("street_resp_person_name_1 not like", value, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1In(List<String> values) {
            addCriterion("street_resp_person_name_1 in", values, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1NotIn(List<String> values) {
            addCriterion("street_resp_person_name_1 not in", values, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1Between(String value1, String value2) {
            addCriterion("street_resp_person_name_1 between", value1, value2, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName1NotBetween(String value1, String value2) {
            addCriterion("street_resp_person_name_1 not between", value1, value2, "streetRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1IsNull() {
            addCriterion("street_resp_person_tel_1 is null");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1IsNotNull() {
            addCriterion("street_resp_person_tel_1 is not null");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1EqualTo(String value) {
            addCriterion("street_resp_person_tel_1 =", value, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1NotEqualTo(String value) {
            addCriterion("street_resp_person_tel_1 <>", value, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1GreaterThan(String value) {
            addCriterion("street_resp_person_tel_1 >", value, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1GreaterThanOrEqualTo(String value) {
            addCriterion("street_resp_person_tel_1 >=", value, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1LessThan(String value) {
            addCriterion("street_resp_person_tel_1 <", value, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1LessThanOrEqualTo(String value) {
            addCriterion("street_resp_person_tel_1 <=", value, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1Like(String value) {
            addCriterion("street_resp_person_tel_1 like", value, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1NotLike(String value) {
            addCriterion("street_resp_person_tel_1 not like", value, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1In(List<String> values) {
            addCriterion("street_resp_person_tel_1 in", values, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1NotIn(List<String> values) {
            addCriterion("street_resp_person_tel_1 not in", values, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1Between(String value1, String value2) {
            addCriterion("street_resp_person_tel_1 between", value1, value2, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel1NotBetween(String value1, String value2) {
            addCriterion("street_resp_person_tel_1 not between", value1, value2, "streetRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2IsNull() {
            addCriterion("street_resp_person_name_2 is null");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2IsNotNull() {
            addCriterion("street_resp_person_name_2 is not null");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2EqualTo(String value) {
            addCriterion("street_resp_person_name_2 =", value, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2NotEqualTo(String value) {
            addCriterion("street_resp_person_name_2 <>", value, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2GreaterThan(String value) {
            addCriterion("street_resp_person_name_2 >", value, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2GreaterThanOrEqualTo(String value) {
            addCriterion("street_resp_person_name_2 >=", value, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2LessThan(String value) {
            addCriterion("street_resp_person_name_2 <", value, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2LessThanOrEqualTo(String value) {
            addCriterion("street_resp_person_name_2 <=", value, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2Like(String value) {
            addCriterion("street_resp_person_name_2 like", value, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2NotLike(String value) {
            addCriterion("street_resp_person_name_2 not like", value, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2In(List<String> values) {
            addCriterion("street_resp_person_name_2 in", values, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2NotIn(List<String> values) {
            addCriterion("street_resp_person_name_2 not in", values, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2Between(String value1, String value2) {
            addCriterion("street_resp_person_name_2 between", value1, value2, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonName2NotBetween(String value1, String value2) {
            addCriterion("street_resp_person_name_2 not between", value1, value2, "streetRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2IsNull() {
            addCriterion("street_resp_person_tel_2 is null");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2IsNotNull() {
            addCriterion("street_resp_person_tel_2 is not null");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2EqualTo(String value) {
            addCriterion("street_resp_person_tel_2 =", value, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2NotEqualTo(String value) {
            addCriterion("street_resp_person_tel_2 <>", value, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2GreaterThan(String value) {
            addCriterion("street_resp_person_tel_2 >", value, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2GreaterThanOrEqualTo(String value) {
            addCriterion("street_resp_person_tel_2 >=", value, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2LessThan(String value) {
            addCriterion("street_resp_person_tel_2 <", value, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2LessThanOrEqualTo(String value) {
            addCriterion("street_resp_person_tel_2 <=", value, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2Like(String value) {
            addCriterion("street_resp_person_tel_2 like", value, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2NotLike(String value) {
            addCriterion("street_resp_person_tel_2 not like", value, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2In(List<String> values) {
            addCriterion("street_resp_person_tel_2 in", values, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2NotIn(List<String> values) {
            addCriterion("street_resp_person_tel_2 not in", values, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2Between(String value1, String value2) {
            addCriterion("street_resp_person_tel_2 between", value1, value2, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andStreetRespPersonTel2NotBetween(String value1, String value2) {
            addCriterion("street_resp_person_tel_2 not between", value1, value2, "streetRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1IsNull() {
            addCriterion("water_group_resp_person_name1 is null");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1IsNotNull() {
            addCriterion("water_group_resp_person_name1 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1EqualTo(String value) {
            addCriterion("water_group_resp_person_name1 =", value, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1NotEqualTo(String value) {
            addCriterion("water_group_resp_person_name1 <>", value, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1GreaterThan(String value) {
            addCriterion("water_group_resp_person_name1 >", value, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1GreaterThanOrEqualTo(String value) {
            addCriterion("water_group_resp_person_name1 >=", value, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1LessThan(String value) {
            addCriterion("water_group_resp_person_name1 <", value, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1LessThanOrEqualTo(String value) {
            addCriterion("water_group_resp_person_name1 <=", value, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1Like(String value) {
            addCriterion("water_group_resp_person_name1 like", value, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1NotLike(String value) {
            addCriterion("water_group_resp_person_name1 not like", value, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1In(List<String> values) {
            addCriterion("water_group_resp_person_name1 in", values, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1NotIn(List<String> values) {
            addCriterion("water_group_resp_person_name1 not in", values, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1Between(String value1, String value2) {
            addCriterion("water_group_resp_person_name1 between", value1, value2, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName1NotBetween(String value1, String value2) {
            addCriterion("water_group_resp_person_name1 not between", value1, value2, "waterGroupRespPersonName1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1IsNull() {
            addCriterion("water_group_resp_person_tel1 is null");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1IsNotNull() {
            addCriterion("water_group_resp_person_tel1 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1EqualTo(String value) {
            addCriterion("water_group_resp_person_tel1 =", value, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1NotEqualTo(String value) {
            addCriterion("water_group_resp_person_tel1 <>", value, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1GreaterThan(String value) {
            addCriterion("water_group_resp_person_tel1 >", value, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1GreaterThanOrEqualTo(String value) {
            addCriterion("water_group_resp_person_tel1 >=", value, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1LessThan(String value) {
            addCriterion("water_group_resp_person_tel1 <", value, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1LessThanOrEqualTo(String value) {
            addCriterion("water_group_resp_person_tel1 <=", value, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1Like(String value) {
            addCriterion("water_group_resp_person_tel1 like", value, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1NotLike(String value) {
            addCriterion("water_group_resp_person_tel1 not like", value, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1In(List<String> values) {
            addCriterion("water_group_resp_person_tel1 in", values, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1NotIn(List<String> values) {
            addCriterion("water_group_resp_person_tel1 not in", values, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1Between(String value1, String value2) {
            addCriterion("water_group_resp_person_tel1 between", value1, value2, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel1NotBetween(String value1, String value2) {
            addCriterion("water_group_resp_person_tel1 not between", value1, value2, "waterGroupRespPersonTel1");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2IsNull() {
            addCriterion("water_group_resp_person_name2 is null");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2IsNotNull() {
            addCriterion("water_group_resp_person_name2 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2EqualTo(String value) {
            addCriterion("water_group_resp_person_name2 =", value, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2NotEqualTo(String value) {
            addCriterion("water_group_resp_person_name2 <>", value, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2GreaterThan(String value) {
            addCriterion("water_group_resp_person_name2 >", value, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2GreaterThanOrEqualTo(String value) {
            addCriterion("water_group_resp_person_name2 >=", value, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2LessThan(String value) {
            addCriterion("water_group_resp_person_name2 <", value, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2LessThanOrEqualTo(String value) {
            addCriterion("water_group_resp_person_name2 <=", value, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2Like(String value) {
            addCriterion("water_group_resp_person_name2 like", value, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2NotLike(String value) {
            addCriterion("water_group_resp_person_name2 not like", value, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2In(List<String> values) {
            addCriterion("water_group_resp_person_name2 in", values, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2NotIn(List<String> values) {
            addCriterion("water_group_resp_person_name2 not in", values, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2Between(String value1, String value2) {
            addCriterion("water_group_resp_person_name2 between", value1, value2, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonName2NotBetween(String value1, String value2) {
            addCriterion("water_group_resp_person_name2 not between", value1, value2, "waterGroupRespPersonName2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2IsNull() {
            addCriterion("water_group_resp_person_tel2 is null");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2IsNotNull() {
            addCriterion("water_group_resp_person_tel2 is not null");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2EqualTo(String value) {
            addCriterion("water_group_resp_person_tel2 =", value, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2NotEqualTo(String value) {
            addCriterion("water_group_resp_person_tel2 <>", value, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2GreaterThan(String value) {
            addCriterion("water_group_resp_person_tel2 >", value, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2GreaterThanOrEqualTo(String value) {
            addCriterion("water_group_resp_person_tel2 >=", value, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2LessThan(String value) {
            addCriterion("water_group_resp_person_tel2 <", value, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2LessThanOrEqualTo(String value) {
            addCriterion("water_group_resp_person_tel2 <=", value, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2Like(String value) {
            addCriterion("water_group_resp_person_tel2 like", value, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2NotLike(String value) {
            addCriterion("water_group_resp_person_tel2 not like", value, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2In(List<String> values) {
            addCriterion("water_group_resp_person_tel2 in", values, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2NotIn(List<String> values) {
            addCriterion("water_group_resp_person_tel2 not in", values, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2Between(String value1, String value2) {
            addCriterion("water_group_resp_person_tel2 between", value1, value2, "waterGroupRespPersonTel2");
            return (Criteria) this;
        }

        public Criteria andWaterGroupRespPersonTel2NotBetween(String value1, String value2) {
            addCriterion("water_group_resp_person_tel2 not between", value1, value2, "waterGroupRespPersonTel2");
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