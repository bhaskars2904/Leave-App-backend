package com.bhaskar.controller;

import com.bhaskar.Service.ApproverLeaveService;
import com.bhaskar.Service.EmployeeDetailService;
import com.bhaskar.Service.EmployeeLeaveService;
import com.bhaskar.dao.EmployeeDao;
import com.bhaskar.dao.LeaveDao;
import com.bhaskar.dao.LeaveStatusDao;
import com.bhaskar.dao.MixedDao;
import com.bhaskar.model.ApproverLeaveDetail;
import com.bhaskar.model.EmployeeDetail;
import com.bhaskar.model.entities.Employee;
import com.bhaskar.model.entities.Leave;
import com.bhaskar.model.entities.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/")
public class MainController {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private MixedDao mixedDao;
    @Autowired
    private LeaveStatusDao leaveStatusDao;
    @Autowired
    private EmployeeDetailService employeeDetailService;
    @Autowired
    private EmployeeLeaveService employeeLeaveService;
    @Autowired
    private ApproverLeaveService approverLeaveService;

//    @RequestMapping(value = "/employee/{uname}", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public List<Object> getEmployeeDetails(@PathVariable String uname){
//        return mixedDao.getEmployeeDetails(uname);
//    }
    @RequestMapping(value = "/employee/{uname}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<EmployeeDetail> getEmployeeDetails(@PathVariable String uname){
       return employeeDetailService.getEmployeeDetails(uname);
    }

//    @RequestMapping(value = "/leaves/{uname}", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public List<Object> getEmployeeLeaves(@PathVariable String uname){
//        return mixedDao.getLeaves(uname);
//    }

    @RequestMapping(value = "/leaves/{uname}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Leave> getEmployeeLeaves(@PathVariable String uname){
        return employeeLeaveService.getEmployeeLeaves(uname);
    }

//    @RequestMapping(value = "/requests/{uname}", method = RequestMethod.GET, produces="application/json")
//    @ResponseBody
//    public List<Object> getRequests(@PathVariable String uname){
//        return mixedDao.getRequests(uname);
//    }

    @RequestMapping(value = "/requests/{uname}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<LeaveStatus> getApproverLeaveDetails(@PathVariable String uname){
        return approverLeaveService.getApproverLeaveDetails(uname);
    }

    @RequestMapping(value = "/leaveinfo/{uname}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public List<Object> getLeaveInfo(@PathVariable String uname){
        return mixedDao.getLeaveInfo(uname);
    }

    @RequestMapping(value="/leavesubmit/{uname}", method = RequestMethod.POST)
    @ResponseBody
    public void submitLeave(@RequestBody Map<String, Object> payload) throws Exception{
        if(payload!=null){
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String uname = (String) payload.get("uname");
            int emp_id = mixedDao.getEmpId(uname);
            Leave leave = new Leave();
            leave.setEmpId(emp_id);
            leave.setDesc((String) payload.get("descr"));
            Date sd = simpleDateFormat.parse((String)payload.get("startDate"));
            Date ed = simpleDateFormat.parse((String)payload.get("endDate"));
            long numLeaveDays = ed.getTime()-sd.getTime();
            numLeaveDays += 24*3600*1000;
            leave.setStartDate(sd);
            leave.setEndDate(ed);
            int leaveDays = (int)TimeUnit.DAYS.convert(numLeaveDays, TimeUnit.MILLISECONDS);
            leave.setNumLeaveDays(leaveDays);
//            leaveDao.deleteAll();
//            leaveStatusDao.deleteAll();
            int leaveIndex = mixedDao.countLeaveTable();
            leaveIndex += 1;
            leave.setLeaveId(leaveIndex);

            leaveDao.save(leave);
            int leaveStatusIndex = mixedDao.countLeaveStatusTable();
            ArrayList<Integer> approvers = (ArrayList<Integer>) payload.get("approvers");
            for (Integer approver: approvers) {
                LeaveStatus leaveStatus = new LeaveStatus();
                leaveStatusIndex+=1;
                leaveStatus.setLeaveStatusId(leaveStatusIndex);
//                leaveStatus.setLeaveId(leaveIndex);
                leaveStatus.setEmpId(emp_id);
                leaveStatus.setApproverId(approver);
                leaveStatus.setStatus("Pending");
                leaveStatusDao.save(leaveStatus);
            }
        }
    }

    @RequestMapping(value = "/approval/{uname}", method = RequestMethod.POST)
    @ResponseBody
    public void approveLeave(@RequestBody Map<String, Object> payload)throws Exception{
        //only uname i.e. of approver and leave_id subject to acceptance will be sent
        if(payload!=null){
           int leaveId = (int) payload.get("leave_id");
           String uname = (String)payload.get("uname");
           int approverId = mixedDao.getEmpId(uname);
           mixedDao.changeStatus(approverId,leaveId);
        }
    }

}
