package com.bhaskar.Service;

import com.bhaskar.dao.EmployeeDao;
import com.bhaskar.model.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailService {

    @Autowired
    private EmployeeDao employeeDao;


    public Employee getEmployeeDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String uname = (String)auth.getPrincipal();
        return employeeDao.findByUname(uname);
    }
}
