package com.bhaskar.dao;

import com.bhaskar.model.entities.EmpManager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmpManagerDao extends CrudRepository<EmpManager, Integer> {
    List<EmpManager> findByEmpId(int id);
}
