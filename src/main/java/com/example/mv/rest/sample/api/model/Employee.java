package com.example.mv.rest.sample.api.model;

import com.example.mv.rest.sample.api.util.ContactNumberConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Employee {

  @Id
  private Long id;

  @NotNull
  private String name;

  private String place;

  private Integer salary;

  private boolean active;

  @ContactNumberConstraint
  private String phone;
}
