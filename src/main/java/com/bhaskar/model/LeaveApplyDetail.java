package com.bhaskar.model;

import java.util.List;

public class LeaveApplyDetail {
    private int leaveLeft;
    private List<EmployeeDetail> approverList;

    public int getLeaveLeft() {
        return leaveLeft;
    }

    public void setLeaveLeft(int leaveLeft) {
        this.leaveLeft = leaveLeft;
    }

    public List<EmployeeDetail> getApproverList() {
        return approverList;
    }

    public void setApproverList(List<EmployeeDetail> approverList) {
        this.approverList = approverList;
    }
}
