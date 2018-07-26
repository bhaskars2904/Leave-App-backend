package com.bhaskar.Service;

import com.bhaskar.dao.EmployeeSecretDao;
import com.bhaskar.model.entities.EmployeeSecret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    private EmployeeSecretDao employeeSecretDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public void signUpEmployee(EmployeeSecret employeeSecret){
        employeeSecret.setPassword(bCryptPasswordEncoder.encode(employeeSecret.getPassword()));
        employeeSecretDao.save(employeeSecret);
    }
}
