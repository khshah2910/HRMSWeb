package com.hrms.controller;

import com.hrms.entity.Employee;
import com.hrms.entity.Manager;
import com.hrms.entity.Team;
import com.hrms.exceptions.ResourceNotFoundException;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.ManagerRepository;
import com.hrms.repository.TeamRepository;
import com.hrms.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class SystemController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    ManagerRepository managerRepository;

    @GetMapping("/employees")
    public Iterable<Employee> getEmployees(){
        Iterable<Employee> employees = employeeRepository.findAll();
        if(((List<Employee>) employees).isEmpty()){
            throw new ResourceNotFoundException("No Records Found!");
        }
        return employees;
    }

    @GetMapping("/employee/{id}")
    public Employee findById(@PathVariable Long id){
        Employee employee = employeeRepository.findEmployeeById(id);
        if(employee == null){
            throw  new ResourceNotFoundException("\n\n No record with id " + id + " found");
        }
        return employee;
    }

    @PostMapping("/employee/register")
    public Employee createEmployee(@RequestBody Employee employee, Manager manager){
        System.out.println("-------->>>>>>>>>"+employee);
        return employeeService.createEmployee(employee, manager);
    }

    @DeleteMapping("/employee/remove/{id}")
    public void removeEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.findEmployeeById(id);
        if(employee == null){
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        }
        employeeRepository.delete(employee);
    }

    @PutMapping("/employee/update")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @PostMapping("/register/team")
    public Team createTeam(@RequestBody Team team) {
        System.out.println(" kjdsfbsdl");
        return teamRepository.save(team);
    }

    @GetMapping("/teams")
    public Iterable<Team> getTeams(){
        Iterable<Team> teams = teamRepository.findAll();
        if(((List<Team>) teams).isEmpty()){
            throw  new ResourceNotFoundException("No Records Found");
        }
        return teams;
    }

    @GetMapping("/manager")
    public List<Manager> getAllManagers(){
        return managerRepository.findAll()  ;
    }

    @GetMapping("/manager/{managerId}")
    public Manager findByManagerId(@PathVariable Long managerId){
       Manager manager = managerRepository.findManagerById(managerId);

        if(manager == null) {
            throw new ResourceNotFoundException("Manager with id: " + managerId + "Not found ");
        }
        return manager;
    }
}