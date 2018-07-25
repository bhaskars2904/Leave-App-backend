package com.bhaskar.Service;

import com.bhaskar.dao.EmployeeDao;
import com.bhaskar.dao.LeaveBalanceDao;
import com.bhaskar.dao.LeaveDao;
import com.bhaskar.dao.LeaveStatusDao;
import com.bhaskar.model.entities.LeaveBalance;
import com.bhaskar.model.entities.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChangeLeaveStatusService {
    @Autowired
    private LeaveStatusDao leaveStatusDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private LeaveBalanceDao leaveBalanceDao;
    @Autowired
    private LeaveDao leaveDao;


    public void approveOrReject(Map<String, Object> payload){
       String status = (String) payload.get("status");
       int leaveId = (int)payload.get("leaveId");
       String approverUname = (String)payload.get("uname");
       int approverId = employeeDao.findByUname(approverUname).getEmpId();
       //1=accepted by all approvers, 0=not accepted by all approvers
        int flag = 1;

       if(status.equalsIgnoreCase("accept")){
           List<LeaveStatus> leaveStatusList = leaveStatusDao.findByLeaveId(leaveId);
           for(LeaveStatus leaveStatus:leaveStatusList) {
               if(leaveStatus.getApproverId()==approverId){
                   leaveStatus.setStatus("Accepted");
                   leaveStatusDao.save(leaveStatus);
               }
               if(leaveStatus.getStatus().equalsIgnoreCase("pending")||leaveStatus.getStatus().equalsIgnoreCase("rejected")){
                   flag = flag*0;
               }
           };
           if(flag==1){
                deductLeave(leaveId);
           }

       }else if(status.equalsIgnoreCase("reject")){
           List<LeaveStatus> leaveStatusList = leaveStatusDao.findByLeaveId(leaveId);
           leaveStatusList.forEach(leaveStatus -> {
               if(leaveStatus.getApproverId()==approverId){
                   leaveStatus.setStatus("Rejected");
                   leaveStatusDao.save(leaveStatus);
               }
           });
       }
    }

    private void deductLeave(int leaveId){
        int empId =  leaveDao.findByLeaveId(leaveId).getEmpId();
        int numLeaveDays = leaveDao.findByLeaveId(leaveId).getNumLeaveDays();
        LeaveBalance leaveBalance = leaveBalanceDao.findByEmpId(empId);
        int daysLeft = leaveBalance.getLeaveLeft();
        if(numLeaveDays<=daysLeft){
            daysLeft = daysLeft - numLeaveDays;
        }
        leaveBalance.setLeaveLeft(daysLeft);
        leaveBalanceDao.save(leaveBalance);
    }
}
