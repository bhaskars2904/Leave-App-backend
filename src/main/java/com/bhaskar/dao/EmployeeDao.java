package com.bhaskar.dao;

import com.bhaskar.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

}
