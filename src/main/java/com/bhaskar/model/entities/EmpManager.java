package com.bhaskar.model.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmpManager {
    @Id
    private Integer empManId;
    private Integer empId;
    private Integer manId;

    public Integer getEmpManId() {
        return empManId;
    }

    public void setEmpManId(Integer empManId) {
        this.empManId = empManId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getManId() {
        return manId;
    }

    public void setManId(Integer manId) {
        this.manId = manId;
    }
}
