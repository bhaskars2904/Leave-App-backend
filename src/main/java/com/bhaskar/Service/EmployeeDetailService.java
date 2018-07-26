package com.bhaskar.Service;

import com.bhaskar.dao.EmployeeDao;
import com.bhaskar.model.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailService {

    @Autowired
    private EmployeeDao employeeDao;

    public Employee getEmployeeDetails(String uname){
        return employeeDao.findByUname(uname);
    }
}
