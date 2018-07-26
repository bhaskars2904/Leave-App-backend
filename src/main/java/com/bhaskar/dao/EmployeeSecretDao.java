package com.bhaskar.dao;

import com.bhaskar.model.entities.EmployeeSecret;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSecretDao extends JpaRepository<EmployeeSecret, Integer> {
    EmployeeSecret findByUname(String uname);
}
