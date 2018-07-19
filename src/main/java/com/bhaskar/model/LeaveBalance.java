package com.bhaskar.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LeaveBalance {
    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public Integer getLeave_left() {
        return leave_left;
    }

    public void setLeave_left(Integer leave_left) {
        this.leave_left = leave_left;
    }

    @Id

    private Integer emp_id;
    private Integer leave_left;
}
