package com.bhaskar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Leave {
    @Id
    private Integer leaveId;
    private Date startDate;
    private Date endDate;
    private String descr;
    private Integer numLeaveDays;

    public Leave() {
    }

    public Leave(Integer leaveId, Date startDate, Date endDate, String descr, Integer numLeaveDays, Integer empId) {
        this.leaveId = leaveId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descr = descr;

        this.numLeaveDays = numLeaveDays;
        this.empId = empId;
    }


    private Integer empId;

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
}
