package com.hrms.repository;

import com.hrms.entity.Employee;
import com.hrms.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeById(Long id);
    List<Employee> findByFirstName(String name);
    List<Employee> findByTeam(Team name);

}
