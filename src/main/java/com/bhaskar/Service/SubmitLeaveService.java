package com.bhaskar.Service;

import com.bhaskar.dao.EmployeeDao;
import com.bhaskar.dao.LeaveBalanceDao;
import com.bhaskar.dao.LeaveDao;
import com.bhaskar.dao.LeaveStatusDao;
import com.bhaskar.model.entities.Leave;
import com.bhaskar.model.entities.LeaveBalance;
import com.bhaskar.model.entities.LeaveStatus;
import com.bhaskar.util.DaysFromDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Service
public class SubmitLeaveService {
    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private LeaveStatusDao leaveStatusDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private LeaveBalanceDao leaveBalanceDao;
    public void submitLeave(Map<String, Object> payload) throws ParseException {
        if(payload!=null){
            Leave leave = new Leave();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String uname = (String)auth.getPrincipal();
            int leaveId = (int)leaveDao.count();
            leaveId += 1;
            String descr = (String) payload.get("descr");
            String startDate = (String)payload.get("startDate");
            String endDate = (String)payload.get("endDate");
            leave.setEmpId(employeeDao.findByUname(uname).getEmpId());
            leave.setLeaveId(leaveId);
            leave.setDesc(descr);
            leave.setStartDate(getDateFromString(startDate));
            leave.setEndDate(getDateFromString(endDate));
            leave.setNumLeaveDays(DaysFromDate.getDaysFromString(startDate,endDate,"yyyy-MM-dd"));
            leaveDao.save(leave);
            deductLeave(leaveId);


            int leaveStatusId = (int)leaveStatusDao.count();

            ArrayList<Integer> approvers = (ArrayList<Integer>) payload.get("approvers");
            for (Integer approver: approvers) {
                LeaveStatus leaveStatus = new LeaveStatus();
                leaveStatusId+=1;
                leaveStatus.setLeaveStatusId(leaveStatusId);
                leaveStatus.setLeaveId(leaveId);
                leaveStatus.setEmpId(employeeDao.findByUname(uname).getEmpId());
                leaveStatus.setApproverId(approver);
                leaveStatus.setStatus("Pending");
                leaveStatusDao.save(leaveStatus);
            }

        }
    }
    public Date getDateFromString( String dateString) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(dateString);
        return date;
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
