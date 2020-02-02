package com.dou.test.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Repository
public class MongoDao {

    @Resource
    MongoTemplate mongotemplate;

    public List<CustomerCollectSampleDto> findAlreadyCollectSample2(ApplySearch applySearch) {
        // MedicalExaminationInfo

        //<editor-fold desc="Lookup 表关联">
        Field from = Fields.field(Constants.MEDICAL_EXAM_INFO);
        Field localField = Fields.field(Constants.WORK_NO);
        Field foreignField = Fields.field(Constants.WORK_NO);
        Field as = Fields.field(Constants.MEDICAL_EXAM_INFO_ALIAS);
        AggregationOperation lookUp = new LookupOperation(from, localField, foreignField, as);
        //</editor-fold>

        //<editor-fold desc="match：采样单状态、pis发送状态、分院编码、体检单、采样开始、结束时间">
        // 采样单状态
        Criteria criteria = where(Constants.STATUS).is(Constants.ONE)
                .orOperator(where(Constants.PIS_STATUS).exists(false),where(Constants.PIS_STATUS).is(Constants.ZERO));
        // 外送单位ID过滤
        if (!StringUtils.isEmpty(applySearch.getOutsideCompanyIds())) {
            criteria.and(Constants.OUTSIDE_COMPANY_ID).in(applySearch.getOutsideCompanyIds());
        }
        // 分院编码
        if ( !StringUtils.isEmpty(applySearch.getInstituteIds()) ) {
            criteria.and(Constants.INSTITUTE_ID).in(applySearch.getInstituteIds());
        }
        // 体检单
        if ( !StringUtils.isEmpty(applySearch.getWorkNos()) ) {
            criteria.and(Constants.WORK_NO).in(applySearch.getWorkNos());
        }
        AggregationOperation matchFirst = new MatchOperation(criteria);

        // 采样开始、结束时间
        if ( !StringUtils.isEmpty(applySearch.getStartTime()) || !StringUtils.isEmpty(applySearch.getEndTime()) ) {
            Criteria criteriaTime = where(Constants.MEDICAL_EXAM_INFO_ALIA_EXAM_DATE)
                    .gte(DateTimeUtils.parseDate(applySearch.getStartTime()))
                    .lt(DateTimeUtils.parseDate(applySearch.getEndTime()));
        }
        // AggregationOperation matchSecond = new MatchOperation(criteriaTime);
        //</editor-fold>

        AggregationOperation unwindInfo = new UnwindOperation(Fields.field("$"+Constants.MEDICAL_EXAM_INFO_ALIAS));

        //<editor-fold desc="group">
        // Field idGroup = Fields.field(Constants.ID, Constants.OBJECT_ID);
        Field instituteIdGroup = Fields.field(Constants.INSTITUTE_ID, Constants.INSTITUTE_ID);
        Field  workNoGroup = Fields.field(Constants.WORK_NO, Constants.WORK_NO);
        Field  collectTime = Fields.field(Constants.COLLECTED_TIME, Constants.COLLECTED_TIME);
        Field  examItemId = Fields.field(Constants.EXAM_ITEM_ID, Constants.EXAM_ITEM_ID);
        GroupOperation groupOperation =
                new GroupOperation(Fields.from(instituteIdGroup, workNoGroup))
                        .first("$institute_id").as("instituteId")
                        .first("$work_no").as("workNo")
                        .first("$mei.exam_date").as("examDate")
                        .first("$mei.customer").as("customer")
                        .first("$mei.cxyz").as("xyz")
        .addToSet("").as("samples");
        // .first("$sample._id").as("sampleId")
        // .first("$sample.collected_time").as("collectedTime")
        // .first("$sample.exam_item_id").as("examItemId")
        // .first("$sample.exam_item_code").as("examItemCode")
        // .first("$sample.exam_item_name").as("examItemName")
        // .first("$sample.sampling_dept_id").as("samplingDeptId")
        // .first("$sample.outside_company_id").as("outsideCompanyId")
        // .first("$sample.outside_company_code").as("outsideCompanyCode")
        // .first("$sample.status").as("status")
        // .first("$sample.pis_status").as("pisStatus")
        // .addToSet("$containers").as("containers");
        //</editor-fold>



        //<editor-fold desc="aggregate">
        Aggregation last = Aggregation.newAggregation( lookUp, matchFirst, unwindInfo, groupOperation);
        return mongotemplate.aggregate(last,Constants.COLLECTING_SAMPLE,CustomerCollectSampleDto.class).getMappedResults();
        //</editor-fold>
    }
}
