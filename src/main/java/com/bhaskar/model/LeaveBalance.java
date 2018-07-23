package com.bhaskar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LeaveBalance {
    @Id
    private Integer empId;
    private Integer leaveLeft;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getLeaveLeft(){return leaveLeft;}
    public void setLeaveLeft(Integer leaveLeft){this.leaveLeft = leaveLeft;}
}
