package com.bhaskar.controller;

import com.bhaskar.Service.*;
//import com.bhaskar.dao.EmployeeDao;
//import com.bhaskar.dao.LeaveDao;
//import com.bhaskar.dao.LeaveStatusDao;
//import com.bhaskar.dao.MixedDao;
import com.bhaskar.model.ApproverLeaveDetail;
import com.bhaskar.model.EmployeeDetail;
import com.bhaskar.model.EmployeeLeaveDetail;
import com.bhaskar.model.LeaveApplyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MainController {
//    @Autowired
//    private EmployeeDao employeeDao;
//    @Autowired
//    private LeaveDao leaveDao;
//    @Autowired
//    private MixedDao mixedDao;
//    @Autowired
//    private LeaveStatusDao leaveStatusDao;
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

    @RequestMapping(value = "/employee/{uname}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<EmployeeDetail> getEmployeeDetails(@PathVariable String uname){
       return employeeDetailService.getEmployeeDetails(uname);
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

}

