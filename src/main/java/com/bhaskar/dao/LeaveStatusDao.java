package com.bhaskar.dao;

import com.bhaskar.model.entities.LeaveStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveStatusDao extends CrudRepository<LeaveStatus, Integer> {
//    @Query("select ls from com.bhaskar.model.entities.LeaveStatus ls where ls.approverId in (select e.empId from Employee e where e.uname= ?1)")
//    List<LeaveStatus> findLeaveByApproverId(String uname);

    List<LeaveStatus> findByLeaveId(int leaveId);
    List<LeaveStatus> findByApproverId(int approverId);
}
