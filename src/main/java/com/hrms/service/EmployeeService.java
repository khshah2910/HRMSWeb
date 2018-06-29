package com.hrms.service;

import com.hrms.entity.Employee;
import com.hrms.entity.Manager;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Employee createEmployee(Employee employee , Manager manager){

      List<Employee> allEmployees = employeeRepository.findAll();

        allEmployees.forEach(getEmployee -> {
            if(getEmployee.getEmail().equalsIgnoreCase(employee.getEmail())){
                System.out.println("Employee already exists! ");
            };
        });

        LocalDateTime dateTime = LocalDateTime.now();

        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setStatus("Active");
        employee.setCreatedOn(dateTime);

        Employee e = employeeRepository.save(employee);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>  "+employee.getTeam());

        if(employee.getRole().equalsIgnoreCase("Manager")){
            manager.setTeam(employee.getTeam());
            manager.setEmployee(employee);
           managerRepository.save(manager);
        }


        return e;
    }

}
