package com.hrms.controller;

import com.hrms.entity.Team;
import com.hrms.exceptions.ResourceNotFoundException;
import com.hrms.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("teams")
public class TeamController {
    @Autowired
    TeamRepository teamRepository;

    @GetMapping
    public Iterable<Team> getTeams(){
        Iterable<Team> teams = teamRepository.findAll();
        if(((List<Team>) teams).isEmpty()){
            throw  new ResourceNotFoundException("No Records Found");
        }
        return teams;
    }
    @PostMapping("/create")
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }
}
