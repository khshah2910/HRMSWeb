package com.hrms.controller;

import com.hrms.entity.Employee;
import com.hrms.entity.Manager;
import com.hrms.entity.Team;
import com.hrms.exceptions.ResourceNotFoundException;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.TeamRepository;
import com.hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TeamRepository teamRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @GetMapping()
    public Iterable<Employee> getEmployees(){
        Iterable<Employee> employees = employeeRepository.findAll();
        if(((List<Employee>) employees).isEmpty()){
            throw new ResourceNotFoundException("No Records Found!");
        }
        return employees;
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id){
        Employee employee = employeeRepository.findEmployeeById(id);
        if(employee == null){
            throw  new ResourceNotFoundException("\n\n No record with id " + id + " found");
        }
        return employee;
    }
    @GetMapping("/findByName/{name}")
    public List<Employee> findByName(@PathVariable String name){
        List<Employee> employee = employeeRepository.findByFirstName(name);
        if(employee == null){
            throw  new ResourceNotFoundException("\n\n No record with name " + name + " found");
        }
        return employee;
    }

    @GetMapping("/findByTeam/{teamName}")
    public List<Employee> findByTeamName(@PathVariable String teamName){

        Team teams = teamRepository.findByTeamName(teamName);

        List<Employee> employee = employeeRepository.findByTeam(teams);
        System.out.println(employee);
        if(employee == null){
            throw  new ResourceNotFoundException("\n\n No record with name " + teamName + " found");
        }
        return employee;
    }

    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee, Manager manager){
        return employeeService.createEmployee(employee, manager);
    }

    @DeleteMapping("/remove/{id}")
    public void removeEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.findEmployeeById(id);
        if(employee == null){
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
        employeeRepository.delete(employee);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@RequestBody Employee employee,@PathVariable Long id){
        Employee emp = employeeRepository.findEmployeeById(id);
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        Employee persist = employeeRepository.save(employee);
        return persist;
    }





}
