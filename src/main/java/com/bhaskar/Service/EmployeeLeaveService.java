package com.bhaskar.Service;

import com.bhaskar.dao.EmployeeDao;
import com.bhaskar.dao.LeaveDao;
import com.bhaskar.dao.LeaveStatusDao;
import com.bhaskar.model.ApproverDetail;
import com.bhaskar.model.EmployeeDetail;
import com.bhaskar.model.EmployeeLeaveDetail;
import com.bhaskar.model.entities.Leave;
import com.bhaskar.model.entities.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeLeaveService {
    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private LeaveStatusDao leaveStatusDao;

    public List<EmployeeLeaveDetail> getEmployeeLeaves(String uname) {
        List<EmployeeLeaveDetail> employeeLeaveDetailList = new ArrayList<>();
        int empId = employeeDao.findByUname(uname).getEmpId();
        List<Leave> leaves = leaveDao.findByEmpId(empId);
        leaves.forEach(leave -> {
            EmployeeLeaveDetail employeeLeaveDetail = new EmployeeLeaveDetail();
            employeeLeaveDetail.setLeaveId(leave.getLeaveId());
            employeeLeaveDetail.setDescr(leave.getDescr());
            employeeLeaveDetail.setStartDate(leave.getStartDate());
            employeeLeaveDetail.setEndDate(leave.getEndDate());
            List<ApproverDetail> approverDetailList = new ArrayList<>();
            List<LeaveStatus> leaveStatusList = leaveStatusDao.findByLeaveId(leave.getLeaveId());
            leaveStatusList.forEach(leaveStatus -> {
                ApproverDetail approverDetail = new ApproverDetail();
                EmployeeDetail employeeDetail = new EmployeeDetail();
                int approverId = leaveStatus.getApproverId();
//                employeeDetail.setFname(employeeDao.findById(approverId).getFname());
//                employeeDetail.setLname(employeeDao.findById(approverId).getLname());
//                employeeDetail.setMail(employeeDao.findById(approverId).getMail());
                employeeDetail.setFname(employeeDao.findByEmpId(approverId).getFname());
                employeeDetail.setLname(employeeDao.findByEmpId(approverId).getLname());
                employeeDetail.setMail(employeeDao.findByEmpId(approverId).getMail());
                approverDetail.setApproverId(approverId);
                approverDetail.setStatus(leaveStatus.getStatus());
                approverDetail.setEmployeeDetail(employeeDetail);
                approverDetailList.add(approverDetail);
            });
            employeeLeaveDetail.setApproverDetailList(approverDetailList);
            employeeLeaveDetailList.add(employeeLeaveDetail);
        });
        return employeeLeaveDetailList;
    }
}
