package com.hrms.service;

import com.hrms.entity.Employee;
import com.hrms.entity.Manager;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Employee createEmployee(Employee employee ,Manager manager){
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setStatus("Active");

        if(employee.getRole().equals("MANAGER")){
            manager.setTeam(employee.getTeam());
            manager.setEmployee(employee);
           managerRepository.save(manager);
        }

        return employeeRepository.save(employee);
    }

}
