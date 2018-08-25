package com.gzw.debit.dal.query;


import com.gzw.debit.dal.querybase.BaseCriteria;
import com.gzw.debit.dal.querybase.BaseQuery;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDate;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.List;

public class BuryQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    public BuryQuery() {
        super();
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        super.oredCriteria.add(criteria);
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

    /**
     * This class corresponds to the database table debit_bury
     */
         protected abstract static class GeneratedCriteria extends BaseCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria anIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(LocalDateTime value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(LocalDateTime value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(LocalDateTime value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(LocalDateTime value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<LocalDateTime> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<LocalDateTime> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(LocalDateTime value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(LocalDateTime value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(LocalDateTime value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(LocalDateTime value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<LocalDateTime> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<LocalDateTime> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(Long value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(Long value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(Long value) {
            addCriterion("product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(Long value) {
            addCriterion("product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andListCountIsNull() {
            addCriterion("list_count is null");
            return (Criteria) this;
        }

        public Criteria andListCountIsNotNull() {
            addCriterion("list_count is not null");
            return (Criteria) this;
        }

        public Criteria andListCountEqualTo(Integer value) {
            addCriterion("list_count =", value, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountNotEqualTo(Integer value) {
            addCriterion("list_count <>", value, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountGreaterThan(Integer value) {
            addCriterion("list_count >", value, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("list_count >=", value, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountLessThan(Integer value) {
            addCriterion("list_count <", value, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountLessThanOrEqualTo(Integer value) {
            addCriterion("list_count <=", value, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountLike(Integer value) {
            addCriterion("list_count like", value, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountNotLike(Integer value) {
            addCriterion("list_count not like", value, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountIn(List<Integer> values) {
            addCriterion("list_count in", values, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountNotIn(List<Integer> values) {
            addCriterion("list_count not in", values, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountBetween(Integer value1, Integer value2) {
            addCriterion("list_count between", value1, value2, "listCount");
            return (Criteria) this;
        }

        public Criteria andListCountNotBetween(Integer value1, Integer value2) {
            addCriterion("list_count not between", value1, value2, "listCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountIsNull() {
            addCriterion("detail_count is null");
            return (Criteria) this;
        }

        public Criteria andDetailCountIsNotNull() {
            addCriterion("detail_count is not null");
            return (Criteria) this;
        }

        public Criteria andDetailCountEqualTo(Integer value) {
            addCriterion("detail_count =", value, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountNotEqualTo(Integer value) {
            addCriterion("detail_count <>", value, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountGreaterThan(Integer value) {
            addCriterion("detail_count >", value, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("detail_count >=", value, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountLessThan(Integer value) {
            addCriterion("detail_count <", value, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountLessThanOrEqualTo(Integer value) {
            addCriterion("detail_count <=", value, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountLike(Integer value) {
            addCriterion("detail_count like", value, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountNotLike(Integer value) {
            addCriterion("detail_count not like", value, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountIn(List<Integer> values) {
            addCriterion("detail_count in", values, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountNotIn(List<Integer> values) {
            addCriterion("detail_count not in", values, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountBetween(Integer value1, Integer value2) {
            addCriterion("detail_count between", value1, value2, "detailCount");
            return (Criteria) this;
        }

        public Criteria andDetailCountNotBetween(Integer value1, Integer value2) {
            addCriterion("detail_count not between", value1, value2, "detailCount");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(Integer value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(Integer value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andPackagetypeIsNull() {
            addCriterion("packagetype is null");
            return (Criteria) this;
        }

        public Criteria andPackagetypeIsNotNull() {
            addCriterion("packagetype is not null");
            return (Criteria) this;
        }

        public Criteria andPackagetypeEqualTo(Integer value) {
            addCriterion("packagetype =", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeNotEqualTo(Integer value) {
            addCriterion("packagetype <>", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeGreaterThan(Integer value) {
            addCriterion("packagetype >", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("packagetype >=", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeLessThan(Integer value) {
            addCriterion("packagetype <", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeLessThanOrEqualTo(Integer value) {
            addCriterion("packagetype <=", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeLike(Integer value) {
            addCriterion("packagetype like", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeNotLike(Integer value) {
            addCriterion("packagetype not like", value, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeIn(List<Integer> values) {
            addCriterion("packagetype in", values, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeNotIn(List<Integer> values) {
            addCriterion("packagetype not in", values, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeBetween(Integer value1, Integer value2) {
            addCriterion("packagetype between", value1, value2, "packagetype");
            return (Criteria) this;
        }

        public Criteria andPackagetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("packagetype not between", value1, value2, "packagetype");
            return (Criteria) this;
        }

        public Criteria andIsBannerIsNull() {
            addCriterion("is_banner is null");
            return (Criteria) this;
        }

        public Criteria andIsBannerIsNotNull() {
            addCriterion("is_banner is not null");
            return (Criteria) this;
        }

        public Criteria andIsBannerEqualTo(Integer value) {
            addCriterion("is_banner =", value, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerNotEqualTo(Integer value) {
            addCriterion("is_banner <>", value, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerGreaterThan(Integer value) {
            addCriterion("is_banner >", value, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_banner >=", value, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerLessThan(Integer value) {
            addCriterion("is_banner <", value, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerLessThanOrEqualTo(Integer value) {
            addCriterion("is_banner <=", value, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerLike(Integer value) {
            addCriterion("is_banner like", value, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerNotLike(Integer value) {
            addCriterion("is_banner not like", value, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerIn(List<Integer> values) {
            addCriterion("is_banner in", values, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerNotIn(List<Integer> values) {
            addCriterion("is_banner not in", values, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerBetween(Integer value1, Integer value2) {
            addCriterion("is_banner between", value1, value2, "isBanner");
            return (Criteria) this;
        }

        public Criteria andIsBannerNotBetween(Integer value1, Integer value2) {
            addCriterion("is_banner not between", value1, value2, "isBanner");
            return (Criteria) this;
        }

    }

    /**
     * This class corresponds to the database table debit_bury
    */
    public  static class Criteria extends GeneratedCriteria{
        protected Criteria() {
            super();
        }
    }



    @Override
    public String toString(){
        return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
    }
}