package com.hrms.entity;

import javax.persistence.*;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private long id;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    /*@OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "manager_id")
    private Manager manager;*/
    public Team(){

    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
