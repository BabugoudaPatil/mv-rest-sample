package com.example.mv.rest.sample.api.controller;

import com.example.mv.rest.sample.api.model.Employee;
import com.example.mv.rest.sample.api.service.EmployeeService;
import com.example.mv.rest.sample.logging.LogExecutionTime;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @PostMapping(path = "/employee", consumes = APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> saveEmployee(@Valid Employee employee) {
    employeeService.save(employee);
    return ResponseEntity.status(CREATED).build();
  }

  @LogExecutionTime
  @GetMapping(path = "/employee/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Employee> getEmployee(@PathVariable Long id, @RequestParam(name="x", required = false) String x) {
    Employee employee = employeeService.findById(id);
    return ResponseEntity.status(OK).body(employee);
  }
}
