package com.bhaskar.model;

import java.util.Date;
import java.util.List;

public class EmployeeLeaveDetail {
    private int leaveId;
    private Date startDate;
    private Date endDate;
    private String descr;
    private List<ApproverDetail> approverDetailList;

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<ApproverDetail> getApproverDetailList() {
        return approverDetailList;
    }

    public void setApproverDetailList(List<ApproverDetail> approverDetailList) {
        this.approverDetailList = approverDetailList;
    }
}
