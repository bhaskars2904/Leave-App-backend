package com.bhaskar.Service;

import com.bhaskar.dao.EmployeeSecretDao;
import com.bhaskar.model.entities.EmployeeSecret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private EmployeeSecretDao employeeSecretDao;

    public UserDetailsServiceImpl(EmployeeSecretDao employeeSecretDao) {
        this.employeeSecretDao = employeeSecretDao;
    }

    @Override
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
        EmployeeSecret employeeSecret = employeeSecretDao.findByUname(uname);
        if(employeeSecret == null){
            throw new UsernameNotFoundException(uname);
        }
        return new User(employeeSecret.getUname(), employeeSecret.getPassword(), emptyList());
    }
}
