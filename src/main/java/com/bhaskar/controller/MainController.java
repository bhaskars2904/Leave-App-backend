package com.bhaskar.controller;

import com.bhaskar.dao.EmployeeDao;
import com.bhaskar.dao.LeaveDao;
import com.bhaskar.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private LeaveDao leaveDao;

}
