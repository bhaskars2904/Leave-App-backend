package com.bhaskar.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Leave {
    @Id
    private Integer leaveId;
    private Date startDate;
    private Date endDate;
    private String descr;
    private Integer numLeaveDays;
    private Integer empId;

//    ManyToOne
//    JoinColumn(name = "emp_id", nullable=false)
//    private Employee employee;

//    OneToMany(mappedBy = "leave")
//    JsonManagedReference
//    private List<LeaveStatus> leaves;

    public Leave() {
    }
    public Leave(Integer leaveId, Date startDate, Date endDate, String descr, Integer numLeaveDays) {
        this.leaveId = leaveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descr = descr;
        this.numLeaveDays = numLeaveDays;
    }

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//    public Leave(Integer leaveId, Date startDate, Date endDate, String descr, Integer numLeaveDays, List<LeaveStatus> leaves, Integer empId) {
//        this.leaveId = leaveId;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.descr = descr;
//        this.leaves = leaves;
//        this.numLeaveDays = numLeaveDays;
//        this.leaves = leaves;
//        this.empId = empId;
//    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getDescr() {
        return descr;
    }

    public void setDesc(String descr) {
        this.descr = descr;
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
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

    public Integer getNumLeaveDays() {
        return numLeaveDays;
    }

    public void setNumLeaveDays(Integer numLeaveDays) {
        this.numLeaveDays = numLeaveDays;
    }

//    public List<LeaveStatus> getLeaves() {
//        return leaves;
//    }
//
//    public void setLeaves(List<LeaveStatus> leaves) {
//        this.leaves = leaves;
//    }
}
