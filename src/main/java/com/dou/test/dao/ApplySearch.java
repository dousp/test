package com.dou.test.dao;

import java.util.ArrayList;
import java.util.List;

public class ApplySearch {

    private List<String> instituteIds = new ArrayList<>();
    private List<Integer> deptIds = new ArrayList<>();
    private List<Integer> outsideCompanyIds = new ArrayList<>();
    private List<String> workNos = new ArrayList<>();
    private String startTime;
    private String endTime;

    public ApplySearch() {
    }

    public ApplySearch(String instituteId, Integer deptId) {
        this.instituteIds.add(instituteId);
        this.deptIds.add(deptId);
    }

    public ApplySearch(String instituteId, Integer deptId, Integer outsideCompanyId) {
        this.instituteIds.add(instituteId);
        this.deptIds.add(deptId);
        this.outsideCompanyIds.add(outsideCompanyId);
    }

    public ApplySearch(String instituteId, Integer deptId, Integer outsideCompanyId,String startTime, String endTime) {

        if(null != instituteId){
            this.instituteIds.add(instituteId);
        }
        if(null != deptId){
            this.deptIds.add(deptId);
        }
        if(null != outsideCompanyId){
            this.outsideCompanyIds.add(outsideCompanyId);
        }
        this.startTime= startTime;
        this.endTime= endTime;
    }

    public ApplySearch(String instituteId, Integer deptId, Integer outsideCompanyId, String workNo) {
        this.instituteIds.add(instituteId);
        this.deptIds.add(deptId);
        this.outsideCompanyIds.add(outsideCompanyId);
        this.workNos.add(workNo);
    }

    public ApplySearch(String instituteId, Integer deptId, Integer outsideCompanyId, String workNo, String startTime, String endTime) {
        this.instituteIds.add(instituteId);
        this.deptIds.add(deptId);
        this.outsideCompanyIds.add(outsideCompanyId);
        this.workNos.add(workNo);
        this.startTime= startTime;
        this.endTime= endTime;
    }

    public ApplySearch(List<String> instituteIds, List<Integer> deptIds, List<Integer> outsideCompanyIds, List<String> workNos, String startTime, String endTime) {
        this.instituteIds = instituteIds;
        this.deptIds = deptIds;
        this.outsideCompanyIds = outsideCompanyIds;
        this.workNos = workNos;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public List<String> getInstituteIds() {
        return instituteIds;
    }

    public ApplySearch setInstituteIds(List<String> instituteIds) {
        this.instituteIds = instituteIds;
        return this;
    }

    public ApplySearch setInstituteId(String instituteId) {
        this.instituteIds.add(instituteId);
        return this;
    }

    public List<Integer> getDeptIds() {
        return deptIds;
    }

    public ApplySearch setDeptIds(List<Integer> deptIds) {
        this.deptIds = deptIds;
        return this;
    }
    public ApplySearch setDeptId(Integer deptId) {
        this.deptIds.add(deptId);
        return this;
    }

    public List<Integer> getOutsideCompanyIds() {
        return outsideCompanyIds;
    }

    public ApplySearch setOutsideCompanyIds(List<Integer> outsideCompanyIds) {
        this.outsideCompanyIds = outsideCompanyIds;
        return this;
    }

    public ApplySearch setOutsideCompanyId(Integer outsideCompanyId) {
        this.outsideCompanyIds.add(outsideCompanyId);
        return this;
    }

    public List<String> getWorkNos() {
        return workNos;
    }

    public ApplySearch setWorkNos(List<String> workNos) {
        this.workNos = workNos;
        return this;
    }

    public ApplySearch setWorkNo(String workNo) {
        this.workNos.add(workNo);
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public ApplySearch setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public ApplySearch setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

}
