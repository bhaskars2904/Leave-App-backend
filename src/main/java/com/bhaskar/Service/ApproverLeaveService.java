package com.bhaskar.Service;

import com.bhaskar.dao.ApproverLeaveDao;
import com.bhaskar.dao.LeaveStatusDao;
import com.bhaskar.model.ApproverLeaveDetail;
import com.bhaskar.model.entities.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApproverLeaveService {
    @Autowired
    private LeaveStatusDao leaveStatusDao;
    public List<LeaveStatus> getApproverLeaveDetails(String uname){
         List<LeaveStatus> ls =  leaveStatusDao.findLeaveByApproverId(uname);
        System.out.println(ls.get(0).getLeave().getStartDate());
         return ls;
    }
}
