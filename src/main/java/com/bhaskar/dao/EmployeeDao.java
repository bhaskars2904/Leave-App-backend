package com.bhaskar.dao;

import com.bhaskar.model.entities.Employee;
import com.bhaskar.model.EmployeeDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {
    Employee findByUname(String uname);
    Employee findByEmpId(int id);
}
