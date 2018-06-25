package com.gzw.debit.dal.query;


import com.gzw.debit.dal.querybase.BaseCriteria;
import com.gzw.debit.dal.querybase.BaseQuery;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.time.LocalDate;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.List;

public class BorrowQuery extends BaseQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    public BorrowQuery() {
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
     * This class corresponds to the database table debit_borrow
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

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderIsNull() {
            addCriterion("borrow_order is null");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderIsNotNull() {
            addCriterion("borrow_order is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderEqualTo(Integer value) {
            addCriterion("borrow_order =", value, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderNotEqualTo(Integer value) {
            addCriterion("borrow_order <>", value, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderGreaterThan(Integer value) {
            addCriterion("borrow_order >", value, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("borrow_order >=", value, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderLessThan(Integer value) {
            addCriterion("borrow_order <", value, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderLessThanOrEqualTo(Integer value) {
            addCriterion("borrow_order <=", value, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderLike(Integer value) {
            addCriterion("borrow_order like", value, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderNotLike(Integer value) {
            addCriterion("borrow_order not like", value, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderIn(List<Integer> values) {
            addCriterion("borrow_order in", values, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderNotIn(List<Integer> values) {
            addCriterion("borrow_order not in", values, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderBetween(Integer value1, Integer value2) {
            addCriterion("borrow_order between", value1, value2, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andBorrowOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("borrow_order not between", value1, value2, "borrowOrder");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductDetailIsNull() {
            addCriterion("product_detail is null");
            return (Criteria) this;
        }

        public Criteria andProductDetailIsNotNull() {
            addCriterion("product_detail is not null");
            return (Criteria) this;
        }

        public Criteria andProductDetailEqualTo(String value) {
            addCriterion("product_detail =", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotEqualTo(String value) {
            addCriterion("product_detail <>", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailGreaterThan(String value) {
            addCriterion("product_detail >", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailGreaterThanOrEqualTo(String value) {
            addCriterion("product_detail >=", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailLessThan(String value) {
            addCriterion("product_detail <", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailLessThanOrEqualTo(String value) {
            addCriterion("product_detail <=", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailLike(String value) {
            addCriterion("product_detail like", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotLike(String value) {
            addCriterion("product_detail not like", value, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailIn(List<String> values) {
            addCriterion("product_detail in", values, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotIn(List<String> values) {
            addCriterion("product_detail not in", values, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailBetween(String value1, String value2) {
            addCriterion("product_detail between", value1, value2, "productDetail");
            return (Criteria) this;
        }

        public Criteria andProductDetailNotBetween(String value1, String value2) {
            addCriterion("product_detail not between", value1, value2, "productDetail");
            return (Criteria) this;
        }

        public Criteria andDebitDescIsNull() {
            addCriterion("debit_desc is null");
            return (Criteria) this;
        }

        public Criteria andDebitDescIsNotNull() {
            addCriterion("debit_desc is not null");
            return (Criteria) this;
        }

        public Criteria andDebitDescEqualTo(String value) {
            addCriterion("debit_desc =", value, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescNotEqualTo(String value) {
            addCriterion("debit_desc <>", value, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescGreaterThan(String value) {
            addCriterion("debit_desc >", value, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescGreaterThanOrEqualTo(String value) {
            addCriterion("debit_desc >=", value, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescLessThan(String value) {
            addCriterion("debit_desc <", value, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescLessThanOrEqualTo(String value) {
            addCriterion("debit_desc <=", value, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescLike(String value) {
            addCriterion("debit_desc like", value, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescNotLike(String value) {
            addCriterion("debit_desc not like", value, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescIn(List<String> values) {
            addCriterion("debit_desc in", values, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescNotIn(List<String> values) {
            addCriterion("debit_desc not in", values, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescBetween(String value1, String value2) {
            addCriterion("debit_desc between", value1, value2, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andDebitDescNotBetween(String value1, String value2) {
            addCriterion("debit_desc not between", value1, value2, "debitDesc");
            return (Criteria) this;
        }

        public Criteria andNeedDataIsNull() {
            addCriterion("need_data is null");
            return (Criteria) this;
        }

        public Criteria andNeedDataIsNotNull() {
            addCriterion("need_data is not null");
            return (Criteria) this;
        }

        public Criteria andNeedDataEqualTo(String value) {
            addCriterion("need_data =", value, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataNotEqualTo(String value) {
            addCriterion("need_data <>", value, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataGreaterThan(String value) {
            addCriterion("need_data >", value, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataGreaterThanOrEqualTo(String value) {
            addCriterion("need_data >=", value, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataLessThan(String value) {
            addCriterion("need_data <", value, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataLessThanOrEqualTo(String value) {
            addCriterion("need_data <=", value, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataLike(String value) {
            addCriterion("need_data like", value, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataNotLike(String value) {
            addCriterion("need_data not like", value, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataIn(List<String> values) {
            addCriterion("need_data in", values, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataNotIn(List<String> values) {
            addCriterion("need_data not in", values, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataBetween(String value1, String value2) {
            addCriterion("need_data between", value1, value2, "needData");
            return (Criteria) this;
        }

        public Criteria andNeedDataNotBetween(String value1, String value2) {
            addCriterion("need_data not between", value1, value2, "needData");
            return (Criteria) this;
        }

        public Criteria andQualificationIsNull() {
            addCriterion("qualification is null");
            return (Criteria) this;
        }

        public Criteria andQualificationIsNotNull() {
            addCriterion("qualification is not null");
            return (Criteria) this;
        }

        public Criteria andQualificationEqualTo(String value) {
            addCriterion("qualification =", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationNotEqualTo(String value) {
            addCriterion("qualification <>", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationGreaterThan(String value) {
            addCriterion("qualification >", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationGreaterThanOrEqualTo(String value) {
            addCriterion("qualification >=", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationLessThan(String value) {
            addCriterion("qualification <", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationLessThanOrEqualTo(String value) {
            addCriterion("qualification <=", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationLike(String value) {
            addCriterion("qualification like", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationNotLike(String value) {
            addCriterion("qualification not like", value, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationIn(List<String> values) {
            addCriterion("qualification in", values, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationNotIn(List<String> values) {
            addCriterion("qualification not in", values, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationBetween(String value1, String value2) {
            addCriterion("qualification between", value1, value2, "qualification");
            return (Criteria) this;
        }

        public Criteria andQualificationNotBetween(String value1, String value2) {
            addCriterion("qualification not between", value1, value2, "qualification");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberIsNull() {
            addCriterion("people_number is null");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberIsNotNull() {
            addCriterion("people_number is not null");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberEqualTo(Integer value) {
            addCriterion("people_number =", value, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberNotEqualTo(Integer value) {
            addCriterion("people_number <>", value, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberGreaterThan(Integer value) {
            addCriterion("people_number >", value, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("people_number >=", value, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberLessThan(Integer value) {
            addCriterion("people_number <", value, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberLessThanOrEqualTo(Integer value) {
            addCriterion("people_number <=", value, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberLike(Integer value) {
            addCriterion("people_number like", value, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberNotLike(Integer value) {
            addCriterion("people_number not like", value, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberIn(List<Integer> values) {
            addCriterion("people_number in", values, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberNotIn(List<Integer> values) {
            addCriterion("people_number not in", values, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberBetween(Integer value1, Integer value2) {
            addCriterion("people_number between", value1, value2, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andPeopleNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("people_number not between", value1, value2, "peopleNumber");
            return (Criteria) this;
        }

        public Criteria andFastTimeIsNull() {
            addCriterion("fast_time is null");
            return (Criteria) this;
        }

        public Criteria andFastTimeIsNotNull() {
            addCriterion("fast_time is not null");
            return (Criteria) this;
        }

        public Criteria andFastTimeEqualTo(String value) {
            addCriterion("fast_time =", value, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeNotEqualTo(String value) {
            addCriterion("fast_time <>", value, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeGreaterThan(String value) {
            addCriterion("fast_time >", value, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeGreaterThanOrEqualTo(String value) {
            addCriterion("fast_time >=", value, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeLessThan(String value) {
            addCriterion("fast_time <", value, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeLessThanOrEqualTo(String value) {
            addCriterion("fast_time <=", value, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeLike(String value) {
            addCriterion("fast_time like", value, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeNotLike(String value) {
            addCriterion("fast_time not like", value, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeIn(List<String> values) {
            addCriterion("fast_time in", values, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeNotIn(List<String> values) {
            addCriterion("fast_time not in", values, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeBetween(String value1, String value2) {
            addCriterion("fast_time between", value1, value2, "fastTime");
            return (Criteria) this;
        }

        public Criteria andFastTimeNotBetween(String value1, String value2) {
            addCriterion("fast_time not between", value1, value2, "fastTime");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyIsNull() {
            addCriterion("debit_money is null");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyIsNotNull() {
            addCriterion("debit_money is not null");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyEqualTo(String value) {
            addCriterion("debit_money =", value, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyNotEqualTo(String value) {
            addCriterion("debit_money <>", value, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyGreaterThan(String value) {
            addCriterion("debit_money >", value, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("debit_money >=", value, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyLessThan(String value) {
            addCriterion("debit_money <", value, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyLessThanOrEqualTo(String value) {
            addCriterion("debit_money <=", value, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyLike(String value) {
            addCriterion("debit_money like", value, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyNotLike(String value) {
            addCriterion("debit_money not like", value, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyIn(List<String> values) {
            addCriterion("debit_money in", values, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyNotIn(List<String> values) {
            addCriterion("debit_money not in", values, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyBetween(String value1, String value2) {
            addCriterion("debit_money between", value1, value2, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitMoneyNotBetween(String value1, String value2) {
            addCriterion("debit_money not between", value1, value2, "debitMoney");
            return (Criteria) this;
        }

        public Criteria andDebitTimeIsNull() {
            addCriterion("debit_time is null");
            return (Criteria) this;
        }

        public Criteria andDebitTimeIsNotNull() {
            addCriterion("debit_time is not null");
            return (Criteria) this;
        }

        public Criteria andDebitTimeEqualTo(String value) {
            addCriterion("debit_time =", value, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeNotEqualTo(String value) {
            addCriterion("debit_time <>", value, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeGreaterThan(String value) {
            addCriterion("debit_time >", value, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeGreaterThanOrEqualTo(String value) {
            addCriterion("debit_time >=", value, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeLessThan(String value) {
            addCriterion("debit_time <", value, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeLessThanOrEqualTo(String value) {
            addCriterion("debit_time <=", value, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeLike(String value) {
            addCriterion("debit_time like", value, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeNotLike(String value) {
            addCriterion("debit_time not like", value, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeIn(List<String> values) {
            addCriterion("debit_time in", values, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeNotIn(List<String> values) {
            addCriterion("debit_time not in", values, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeBetween(String value1, String value2) {
            addCriterion("debit_time between", value1, value2, "debitTime");
            return (Criteria) this;
        }

        public Criteria andDebitTimeNotBetween(String value1, String value2) {
            addCriterion("debit_time not between", value1, value2, "debitTime");
            return (Criteria) this;
        }

        public Criteria andMonthyRateIsNull() {
            addCriterion("monthy_rate is null");
            return (Criteria) this;
        }

        public Criteria andMonthyRateIsNotNull() {
            addCriterion("monthy_rate is not null");
            return (Criteria) this;
        }

        public Criteria andMonthyRateEqualTo(java.math.BigDecimal value) {
            addCriterion("monthy_rate =", value, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateNotEqualTo(java.math.BigDecimal value) {
            addCriterion("monthy_rate <>", value, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateGreaterThan(java.math.BigDecimal value) {
            addCriterion("monthy_rate >", value, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateGreaterThanOrEqualTo(java.math.BigDecimal value) {
            addCriterion("monthy_rate >=", value, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateLessThan(java.math.BigDecimal value) {
            addCriterion("monthy_rate <", value, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateLessThanOrEqualTo(java.math.BigDecimal value) {
            addCriterion("monthy_rate <=", value, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateLike(java.math.BigDecimal value) {
            addCriterion("monthy_rate like", value, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateNotLike(java.math.BigDecimal value) {
            addCriterion("monthy_rate not like", value, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateIn(List<java.math.BigDecimal> values) {
            addCriterion("monthy_rate in", values, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateNotIn(List<java.math.BigDecimal> values) {
            addCriterion("monthy_rate not in", values, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
            addCriterion("monthy_rate between", value1, value2, "monthyRate");
            return (Criteria) this;
        }

        public Criteria andMonthyRateNotBetween(java.math.BigDecimal value1, java.math.BigDecimal value2) {
            addCriterion("monthy_rate not between", value1, value2, "monthyRate");
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

    }

    /**
     * This class corresponds to the database table debit_borrow
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