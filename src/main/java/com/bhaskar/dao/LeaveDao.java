package com.bhaskar.dao;

import com.bhaskar.model.Leave;
import org.springframework.data.repository.CrudRepository;

public interface LeaveDao extends CrudRepository<Leave, Integer> {

}
