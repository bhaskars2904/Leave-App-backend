package com.bhaskar.controller;

import com.bhaskar.Service.*;
import com.bhaskar.model.*;
import com.bhaskar.model.entities.Employee;
import com.bhaskar.model.entities.EmployeeSecret;
import com.bhaskar.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sun.text.normalizer.ICUBinary;

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

    @RequestMapping(value = "/employee/{uname}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public Employee getEmployeeDetails(@PathVariable String uname){
//        return employeeDetailService.getEmployeeDetails(uname);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = (String)auth.getPrincipal();
        return employeeDetailService.getEmployeeDetails(name);
    }

    @RequestMapping(value = "/leaves/{uname}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<EmployeeLeaveDetail> getEmployeeLeaves(@PathVariable String uname){
        return employeeLeaveService.getEmployeeLeaves(uname);
    }

    @RequestMapping(value = "/requests/{uname}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<ApproverLeaveDetail> getApproverLeaveDetails(@PathVariable String uname){
        return approverLeaveService.getApproverLeaveDetails(uname);
    }

    @RequestMapping(value = "/leaveinfo/{uname}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public LeaveApplyDetail getLeaveApplyDetails(@PathVariable String uname){
        return leaveApplyDetailService.getLeaveApplyDetails(uname);
    }

    @RequestMapping(value="/leavesubmit/{uname}", method = RequestMethod.POST)
    @ResponseBody
    public void submitLeave(@RequestBody Map<String, Object> payload)throws Exception{
        submitLeaveService.submitLeave(payload);
    }

    @RequestMapping(value="/statuschange/{uname}", method = RequestMethod.POST)
    @ResponseBody
    public void changeLeaveStatus(@RequestBody Map<String, Object> payload)throws Exception{
        changeLeaveStatusService.approveOrReject(payload);
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    @ResponseBody
    public void signUp(@RequestBody EmployeeSecret employeeSecret) {
        signUpService.signUpEmployee(employeeSecret);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public MyUser login(HttpServletRequest request, HttpServletResponse response){
        MyUser user = new MyUser();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("authentication object is "+auth);
        String name = (String) auth.getPrincipal();

        user.setUname(name);
        user.setRole(1);
        user.setToken("token is here");
        return user;
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = (String) authentication.getPrincipal();
        return name;
    }

}

