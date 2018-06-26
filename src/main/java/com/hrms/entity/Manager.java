package com.hrms.entity;

import javax.persistence.*;

@Entity
@Table(name = "manager")
public class Manager {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "manager_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;

    @OneToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private Employee employee;

    public Manager() {
    }

    public Manager(Team team, Employee employee) {
        this.team = team;
        this.employee = employee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", team=" + team +
                ", employee=" + employee +
                '}';
    }
}
