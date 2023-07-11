package com.example.mv.rest.sample.api.service;

import com.example.mv.rest.sample.api.model.Employee;
import com.example.mv.rest.sample.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public Optional<Employee> save(Employee covid) {
    if (!employeeRepository.existsById(covid.getId())) {
      return Optional.ofNullable(employeeRepository.save(covid));
    } else {
      throw new RuntimeException("Bad request");
    }
  }

  public Employee findById(Long id) {
    Optional<Employee> covid = employeeRepository.findById(id);
    if (covid.isPresent()) {
      return covid.get();
    } else {
      throw new RuntimeException("Not found");
    }
  }

  public List<Employee> findAllBy(String by) {
    return employeeRepository.findAll(sortAscendingOrderBy(by));
  }

  private Sort sortAscendingOrderBy(String by) {
    return Sort.by(by).ascending();
  }

}
