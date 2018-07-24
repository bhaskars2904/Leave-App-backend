package com.bhaskar.dao;

import com.bhaskar.model.entities.Leave;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveDao extends CrudRepository<Leave, Integer> {
    @Query("Select l FROM com.bhaskar.model.entities.Leave l where l.empId in (select e.empId from Employee e where e.uname=?1)")
    List<Leave> getEmployeeLeaves(String uname);
}
