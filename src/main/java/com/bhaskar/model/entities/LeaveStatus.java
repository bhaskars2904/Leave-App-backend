package com.bhaskar.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class LeaveStatus {
    @Id
    private Integer leaveStatusId;
    private Integer leaveId;
    private Integer empId;
    private Integer approverId;
    private String status;
    @ManyToOne
    @JoinColumn(name="leaveId",referencedColumnName="leaveId", insertable = false, updatable = false)
    @JsonBackReference
    private Leave leave;

    public LeaveStatus() {
    }

    public LeaveStatus(Integer leaveStatusId, Integer leaveId, Integer empId, Integer approverId, String status, Leave leave) {

        this.leaveStatusId = leaveStatusId;
        this.leaveId = leaveId;
        this.empId = empId;
        this.approverId = approverId;
        this.status = status;
        this.leave = leave;
    }

    public Integer getLeaveStatusId() {
        return leaveStatusId;
    }

    public void setLeaveStatusId(Integer leaveStatusId) {
        this.leaveStatusId = leaveStatusId;
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getApproverId() {
        return approverId;
    }

    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Leave getLeave() {

        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }
}
