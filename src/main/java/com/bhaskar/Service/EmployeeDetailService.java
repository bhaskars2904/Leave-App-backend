package com.bhaskar.Service;

import com.bhaskar.dao.EmployeeDao;
import com.bhaskar.model.EmployeeDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDetailService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<EmployeeDetail> getEmployeeDetails(String uname){
        return (List<EmployeeDetail>) employeeDao.findEmployeeDetailsByUserName(uname);
    }
}
