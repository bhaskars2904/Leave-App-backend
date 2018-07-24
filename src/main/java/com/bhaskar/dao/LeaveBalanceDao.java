package com.bhaskar.dao;

import com.bhaskar.model.entities.LeaveBalance;
import org.springframework.data.repository.CrudRepository;

public interface LeaveBalanceDao extends CrudRepository<LeaveBalance, Integer> {
}
