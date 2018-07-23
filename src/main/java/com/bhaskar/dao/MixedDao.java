package com.bhaskar.dao;

import java.util.List;

public interface MixedDao {
    List<Object> getEmployeeDetails(String uname);
    List<Object> getLeaves(String uname);
    List<Object> getRequests(String uname);
    List<Object> getLeaveInfo(String uname);
    int getEmpId(String uname);
    int countLeaveTable();
    int countLeaveStatusTable();
    void changeStatus(int approverId, int leaveId);
}
