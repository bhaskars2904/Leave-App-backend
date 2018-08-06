package com.bhaskar.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LeaveBalance {
    @Id
    private Integer empId;
    private Integer leaveLeft;
//    OneToOne
//    private Employee employee;

//    public LeaveBalance(Integer empId, Integer leaveLeft, Employee employee) {
//        this.empId = empId;
//        this.leaveLeft = leaveLeft;
//        this.employee = employee;
//    }

//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getLeaveLeft(){return leaveLeft;}
    public void setLeaveLeft(Integer leaveLeft){this.leaveLeft = leaveLeft;}
}
