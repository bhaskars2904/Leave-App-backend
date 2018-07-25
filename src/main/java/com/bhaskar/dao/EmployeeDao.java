package com.bhaskar.dao;

import com.bhaskar.model.entities.Employee;
import com.bhaskar.model.EmployeeDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    @Query("SELECT new com.bhaskar.model.EmployeeDetail(e.fname, e.lname, e.mail) " +
            "FROM Employee e where e.uname = ?1 ")
    List<EmployeeDetail> findEmployeeDetailsByUserName(String uname);

    @Query("select e.fname from com.bhaskar.model.entities.Employee e where e.empId = ?1")
    String findFnameById(int id);

    Employee findByUname(String uname);
    Employee findByEmpId(int id);
}
