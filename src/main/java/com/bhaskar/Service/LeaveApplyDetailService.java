package com.bhaskar.Service;

import com.bhaskar.dao.EmpManagerDao;
import com.bhaskar.dao.EmployeeDao;
import com.bhaskar.dao.LeaveBalanceDao;
import com.bhaskar.model.EmployeeDetail;
import com.bhaskar.model.LeaveApplyDetail;
import com.bhaskar.model.entities.EmpManager;
import com.bhaskar.model.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveApplyDetailService {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    EmpManagerDao empManagerDao;
    @Autowired
    LeaveBalanceDao leaveBalanceDao;
    public LeaveApplyDetail getLeaveApplyDetails(String uname){
        LeaveApplyDetail leaveApplyDetail = new LeaveApplyDetail();
//        leaveApplyDetail.setLeaveLeft(leaveBalanceDao.findLeaveLeftById(employeeDao.findByUname(uname).getEmpId()));
        leaveApplyDetail.setLeaveLeft(leaveBalanceDao.findByEmpId(employeeDao.findByUname(uname).getEmpId()).getLeaveLeft());
        List<EmployeeDetail> approverDetails= new ArrayList<EmployeeDetail>();
        List<Employee> approvers = new ArrayList<Employee>();
        empManagerDao.findByEmpId(employeeDao.findByUname(uname).getEmpId()).forEach(empManager -> {
           EmployeeDetail employeeDetail = new EmployeeDetail();
           int approverId = empManager.getManId();
           Employee approver = employeeDao.findByEmpId(approverId);
           employeeDetail.setFname(approver.getFname());
           employeeDetail.setLname(approver.getLname());
           employeeDetail.setMail(approver.getMail());
           approverDetails.add(employeeDetail);
        });
        leaveApplyDetail.setApproverList(approverDetails);
        return leaveApplyDetail;
    }
}
