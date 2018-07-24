package com.bhaskar.Service;

import com.bhaskar.dao.LeaveDao;
import com.bhaskar.model.entities.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeLeaveService {
    @Autowired
    private LeaveDao leaveDao;

    public List<Leave> getEmployeeLeaves(String uname) {
        return leaveDao.getEmployeeLeaves(uname);
    }
}
