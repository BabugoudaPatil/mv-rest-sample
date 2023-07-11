package com.example.mv.rest.sample.api.exceptions;

import lombok.Data;

@Data
public class ErrorMessage {

  private String message;

  private String errorCode;

  private String errorMessage;

  public ErrorMessage(String message, String errorCode, String errorMessage) {
    this.message = message;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
