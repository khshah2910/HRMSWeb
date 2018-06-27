package com.hrms.controller;


import com.hrms.entity.Manager;
import com.hrms.exceptions.ResourceNotFoundException;
import com.hrms.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerRepository managerRepository;

    @GetMapping
    public List<Manager> getAllManagers(){
        return managerRepository.findAll()  ;
    }

    @GetMapping("/{managerId}")
    public Manager findByManagerId(@PathVariable Long managerId){
        Manager manager = managerRepository.findManagerById(managerId);

        if(manager == null) {
            throw new ResourceNotFoundException("Manager with id: " + managerId + "Not found ");
        }
        return manager;
    }
}
