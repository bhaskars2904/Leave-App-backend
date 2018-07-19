package com.bhaskar.dao;

import com.bhaskar.model.LeaveBalance;
import org.springframework.data.repository.CrudRepository;

public interface LeaveBalanceDao extends CrudRepository<LeaveBalance, Long> {
}
