package com.dou.test.dao;

/**
 * 公共常量
 * @author dsp
 * @date 2019-07-11
 */
public interface Constants {

    Integer ZERO =0;
    Integer ONE =1;
    Integer TWO =2;

    /**\
     * 文档
     */
    String MEDICAL_EXAM_INFO = "medical_examination_info";
    String ITEM_EXAM = "item_examination";
    String EXAM_ITEM_CHECK_LOG = "exam_item_check_log";
    String COLLECTING_SAMPLE = "collecting_sample";
    String THR_CUSTOMER_EXAM_ITEM = "thr_customer_exam_item";
    String EXAMINATION_CHECK_INFO = "examination_check_info";

    /**
     * 别名
     */
    String EXAMINATION_CHECK_INFO_ALIAS = "exam_check_info";
    String MEDICAL_EXAM_INFO_ALIAS = "mei";
    String COLLECTING_SAMPLE_ALIAS = "sample";
    String MEDICAL_EXAM_INFO_ALIA_EXAM_DATE = "mei.exam_date";
    String EXAMINATION_CHECK_INFO_STATUS = "exam_check_info.status";
    String EXAMINATION_CHECK_INFO_PIS_STATUS = "exam_check_info.pis_status";
    String COLLECTING_SAMPLE_ALIAS_PIS_STATUS = "sample.pis_status";
    String COLLECTING_SAMPLE_ALIAS_STATUS = "sample.status";
    String COLLECTING_SAMPLE_ALIAS_OUTSIDE_COMPANY_ID = "sample.outside_company_id";


    /**
     * 属性
     */
    String ID = "id";
    String OBJECT_ID = "_id";
    String INSTITUTE_ID = "institute_id";
    String INSTITUTEID = "instituteId";
    String WORK_NO = "work_no";
    String WORKNO = "workNo";
    String DEPARTMENT_ID = "department_id";
    String STATUS = "status";
    String OUTSIDE_COMPANY_ID = "outside_company_id";
    String EXAM_DATE = "exam_date";
    String PIS_STATUS = "pis_status";
    String CW_STATUS = "cw_status";
    String THIRDASSOCIANAME = "ThirdAssociaName";
    String EXAMINATION_INDEX = "examination_index";
    String COLLECTED_TIME = "collected_time";
    String EXAM_ITEM_ID = "exam_item_id";

    /**
     * 组合属性、聚合
     */
    String ITEM_EXAM_ITEM_INDEXES_CODE = "item_indexes.code";
    String ITEM_EXAM_ITEM_INDEXES_$_VALUE = "item_indexes.$.value";
    String ITEM_EXAM_ITEM_INDEXES_$_RESULT_TYPE = "item_indexes.$.result_type";
    String ITEM_EXAM_ITEM_INDEXES_$_RESULT_UNIT = "item_indexes.$.result_unit";
    String ITEM_EXAM_ITEM_INDEXES_$_POSITIVE = "item_indexes.$.positive";
    String ITEM_EXAM_ITEM_INDEXES_$_HIGH_NORMAL_VALUE = "item_indexes.$.high_normal_value";
    String ITEM_EXAM_ITEM_INDEXES_$_LOW_NORMAL_VALUE = "item_indexes.$.low_normal_value";

    String UNWIND_EXAM_CHECK_INFO = "$exam_check_info";
    String UNWIND_EXAMINATION_INDEX = "$examination_index";
    String UNWIND_EXAMINATION_INDEX_EXAM_ITEMS = "$examination_index.exam_items";


    String MEDICAL_EXAM_I_DEPARTMENT_ID = "i.department_id";
    String MEDICAL_EXAM_J_STATUS = "j.status";
    String MEDICAL_EXAM_J_ID = "j.id";
    String MEDICAL_EXAM_INDEX_ITEM_EXAMINER_ID = "examination_index.exam_items.examiner.id";
    String MEDICAL_EXAM_INDEX_ITEM_EXAMINER_NAME = "examination_index.exam_items.examiner.name";
    String MEDICAL_EXAM_INDEX_ITEM_CHECKER_ID = "examination_index.exam_items.checker.id";
    String MEDICAL_EXAM_INDEX_ITEM_CHECKER_NAME = "examination_index.exam_items.checker.name";
    String MEDICAL_EXAM_INDEX_DEPARTMENT_CODE = "examination_index.department_code";
    String MEDICAL_EXAM_INDEX_ITEM_OUTSIDE_COMPANY_ID = "examination_index.exam_items.outside_company_id";
    String MEDICAL_EXAM_INDEX_DEPARTMENT_ID = "examination_index.department_id";
    String MEDICAL_EXAM_INDEX_EXAM_ITEMS = "examination_index.exam_items";

    
    String THR_CUSTOMER_CHECKITEMS_ITEMMISCODE = "CheckItems.ItemMisCode";

}
