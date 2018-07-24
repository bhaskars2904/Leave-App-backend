package com.bhaskar.model;

import javax.persistence.Id;
import java.util.Date;

public class ApproverLeaveDetail {
    private int leaveId;
    private Date startDate;
    private Date endDate;
    private String descr;
    private int empId;
    private String fname;
    private String status;
    private int numLeaveDays;
    private int leaveLeft;

    public ApproverLeaveDetail() {
    }

    public ApproverLeaveDetail(int leaveId, Date startDate, Date endDate, String descr, int empId, String fname, String status, int numLeaveDays, int leaveLeft) {
        this.leaveId = leaveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descr = descr;
        this.empId = empId;
        this.fname = fname;
        this.status = status;
        this.numLeaveDays = numLeaveDays;
        this.leaveLeft = leaveLeft;
    }

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

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumLeaveDays() {
        return numLeaveDays;
    }

    public void setNumLeaveDays(int numLeaveDays) {
        this.numLeaveDays = numLeaveDays;
    }

    public int getLeaveLeft() {
        return leaveLeft;
    }

    public void setLeaveLeft(int leaveLeft) {
        this.leaveLeft = leaveLeft;
    }
}
