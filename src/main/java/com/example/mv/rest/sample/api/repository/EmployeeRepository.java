package com.example.mv.rest.sample.api.repository;

import com.example.mv.rest.sample.api.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
