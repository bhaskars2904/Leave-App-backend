package com.bhaskar.controller;

import com.bhaskar.Service.*;
import com.bhaskar.model.ApproverLeaveDetail;
import com.bhaskar.model.EmployeeLeaveDetail;
import com.bhaskar.model.LeaveApplyDetail;
import com.bhaskar.model.entities.Employee;
import com.bhaskar.model.entities.EmployeeSecret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    private EmployeeDetailService employeeDetailService;
    @Autowired
    private EmployeeLeaveService employeeLeaveService;
    @Autowired
    private ApproverLeaveService approverLeaveService;
    @Autowired
    private LeaveApplyDetailService leaveApplyDetailService;
    @Autowired
    private SubmitLeaveService submitLeaveService;
    @Autowired
    private ChangeLeaveStatusService changeLeaveStatusService;
    @Autowired
    private SignUpService signUpService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Employee getEmployeeDetails(){
        return employeeDetailService.getEmployeeDetails();
    }

    @RequestMapping(value = "/leaves", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<EmployeeLeaveDetail> getEmployeeLeaves(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = (String)auth.getPrincipal();
        return employeeLeaveService.getEmployeeLeaves(name);
    }

    @RequestMapping(value = "/requests", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<ApproverLeaveDetail> getApproverLeaveDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = (String)auth.getPrincipal();
        return approverLeaveService.getApproverLeaveDetails(name);
    }

    @RequestMapping(value = "/leaveinfo", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public LeaveApplyDetail getLeaveApplyDetails(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = (String)auth.getPrincipal();
        return leaveApplyDetailService.getLeaveApplyDetails(name);
    }

    @RequestMapping(value="/leavesubmit", method = RequestMethod.POST)
    @ResponseBody
    public List<EmployeeLeaveDetail> submitLeave(@RequestBody Map<String, Object> payload)throws Exception{

        submitLeaveService.submitLeave(payload);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = (String)auth.getPrincipal();
        return employeeLeaveService.getEmployeeLeaves(name);
    }

    @RequestMapping(value="/statuschange", method = RequestMethod.POST)
    @ResponseBody
    public List<ApproverLeaveDetail> changeLeaveStatus(@RequestBody Map<String, Object> payload)throws Exception{
        changeLeaveStatusService.approveOrReject(payload);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = (String)auth.getPrincipal();
        return approverLeaveService.getApproverLeaveDetails(name);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    @ResponseBody
    public void signUp(@RequestBody EmployeeSecret employeeSecret) {
        signUpService.signUpEmployee(employeeSecret);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public void login(HttpServletRequest request, HttpServletResponse response){
    }

    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    @ResponseBody
    public String hello(@RequestBody Map<String, Object> payload){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = (String) authentication.getPrincipal();
        System.out.println(payload);
        return name;
    }

}

