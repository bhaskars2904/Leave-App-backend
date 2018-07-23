package com.bhaskar.dao;

import com.bhaskar.model.LeaveStatus;
import org.springframework.data.repository.CrudRepository;

public interface LeaveStatusDao extends CrudRepository<LeaveStatus, Integer> {
}
