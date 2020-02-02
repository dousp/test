package com.dou.test.dao;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

/**
 * @author dsp
 * @date 2019-08-09
 */
public class CustomerCollectSampleDto {
    /*
     * Medical_Examination_Info
     */

    @Id
    @Field("_id")
    private ObjectId id;
    @Field("institute_id")
    private String instituteId;
    @Field("work_no")
    private String workNo;
    @Field("exam_date")
    private Date examDate;
    @Field("xyz")
    private String xyz;
    @Field("customer")
    private Customer customer;

    /*
     * Sample
     */
    @Field("samples")
    private List<Sample> samples;

    public static class Sample{
        @Field("collected_time")
        private Date collectedTime;
        @Field("exam_item_id")
        private Long examItemId;
        @Field("exam_item_code")
        private String examItemCode;
        @Field("exam_item_name")
        private String examItemName;
        @Field("sampling_dept_id")
        private Integer samplingDeptId;
        @Field("status")
        private Integer status;
        @Field("pis_status")
        private Integer pisStatus;

        public Date getCollectedTime() {
            return collectedTime;
        }

        public Sample setCollectedTime(Date collectedTime) {
            this.collectedTime = collectedTime;
            return this;
        }

        public Long getExamItemId() {
            return examItemId;
        }

        public Sample setExamItemId(Long examItemId) {
            this.examItemId = examItemId;
            return this;
        }

        public String getExamItemCode() {
            return examItemCode;
        }

        public Sample setExamItemCode(String examItemCode) {
            this.examItemCode = examItemCode;
            return this;
        }

        public String getExamItemName() {
            return examItemName;
        }

        public Sample setExamItemName(String examItemName) {
            this.examItemName = examItemName;
            return this;
        }

        public Integer getSamplingDeptId() {
            return samplingDeptId;
        }

        public Sample setSamplingDeptId(Integer samplingDeptId) {
            this.samplingDeptId = samplingDeptId;
            return this;
        }

        public Integer getStatus() {
            return status;
        }

        public Sample setStatus(Integer status) {
            this.status = status;
            return this;
        }

        public Integer getPisStatus() {
            return pisStatus;
        }

        public Sample setPisStatus(Integer pisStatus) {
            this.pisStatus = pisStatus;
            return this;
        }
    }
    // @Field("collected_time")
    // private Date collectedTime;
    // @Field("exam_item_id")
    // private Long examItemId;
    // @Field("exam_item_code")
    // private String examItemCode;
    // @Field("exam_item_name")
    // private String examItemName;
    // @Field("sampling_dept_id")
    // private Integer samplingDeptId;
    // @Field("containers")
    // private List<CollectingSample.Container> containers;
    // @Field("outside_company_id")
    // private Integer outsideCompanyId;
    // @Field("outside_company_code")
    // private String outsideCompanyCode;
    // @Field("status")
    // private Integer status;

    /*
     * getter/setter
     */

    public ObjectId getId() {
        return id;
    }

    public CustomerCollectSampleDto setId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getInstituteId() {
        return instituteId;
    }

    public CustomerCollectSampleDto setInstituteId(String instituteId) {
        this.instituteId = instituteId;
        return this;
    }

    public String getWorkNo() {
        return workNo;
    }

    public CustomerCollectSampleDto setWorkNo(String workNo) {
        this.workNo = workNo;
        return this;
    }

    public Date getExamDate() {
        return examDate;
    }

    public CustomerCollectSampleDto setExamDate(Date examDate) {
        this.examDate = examDate;
        return this;
    }

    public String getXyz() {
        return xyz;
    }

    public CustomerCollectSampleDto setXyz(String xyz) {
        this.xyz = xyz;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerCollectSampleDto setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public CustomerCollectSampleDto setSamples(List<Sample> samples) {
        this.samples = samples;
        return this;
    }
}
