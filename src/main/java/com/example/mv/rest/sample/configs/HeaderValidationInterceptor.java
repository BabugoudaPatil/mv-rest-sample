package com.example.mv.rest.sample.configs;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HeaderValidationInterceptor implements HandlerInterceptor {

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String headerValue = request.getHeader("Correlation-ID");
    if (headerValue == null || headerValue.isEmpty()) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing mandatory header: Correlation-ID");
      return false;
    }
    return true;
  }
}
