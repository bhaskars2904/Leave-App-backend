package com.bhaskar.Service;

import com.bhaskar.dao.*;
import com.bhaskar.model.ApproverLeaveDetail;
import com.bhaskar.model.entities.Employee;
import com.bhaskar.model.entities.Leave;
import com.bhaskar.model.entities.LeaveStatus;
import com.bhaskar.util.DaysFromDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ApproverLeaveService {
    @Autowired
    private LeaveStatusDao leaveStatusDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private LeaveBalanceDao leaveBalanceDao;
    @Autowired
    private LeaveDao leaveDao;
    public List<ApproverLeaveDetail> getApproverLeaveDetails(String uname){
        List<LeaveStatus> leaveStatusList =  leaveStatusDao.findByApproverId(employeeDao.findByUname(uname).getEmpId());
        List<ApproverLeaveDetail> approverLeaveDetailList = new ArrayList<>();

        leaveStatusList.forEach(leaveStatus -> {
            ApproverLeaveDetail approverLeaveDetail = new ApproverLeaveDetail();

            String fname = employeeDao.findByEmpId(leaveStatus.getEmpId()).getFname();
            int leaveId = leaveStatus.getLeaveId();
            int leaveLeft = leaveBalanceDao.findByEmpId(leaveStatus.getEmpId()).getLeaveLeft();
            Leave leave = leaveDao.findByLeaveId(leaveId);
            approverLeaveDetail.setFname(fname);
            approverLeaveDetail.setLeaveId(leaveId);
            approverLeaveDetail.setStartDate(leave.getStartDate());
            approverLeaveDetail.setEndDate(leave.getEndDate());
            approverLeaveDetail.setDescr(leave.getDescr());
            approverLeaveDetail.setEmpId(leave.getEmpId());
            approverLeaveDetail.setNumLeaveDays(DaysFromDate.getDaysFromDate(leave.getStartDate(),leave.getEndDate()));
            approverLeaveDetail.setLeaveLeft(leaveLeft);
            approverLeaveDetail.setStatus(leaveStatus.getStatus());
            approverLeaveDetailList.add(approverLeaveDetail);
        });

        return approverLeaveDetailList;
    }
}
