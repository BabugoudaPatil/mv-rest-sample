package com.example.mv.rest.sample.configs;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class ResponseLoggingFilter extends OncePerRequestFilter {

  private static final Logger logger = LoggerFactory.getLogger(ResponseLoggingFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    long startTime = System.currentTimeMillis();

    // Generate a unique correlation ID for the request if not already present
    String correlationId = request.getHeader("Correlation-ID");

    if (StringUtils.isEmpty(correlationId)) {
      correlationId = UUID.randomUUID().toString();
      response.setHeader("Correlation-ID", correlationId);
    }

    try {
      filterChain.doFilter(request, response);
    } finally {
      long endTime = System.currentTimeMillis();
      long responseTime = endTime - startTime;
      int responseCode = response.getStatus();
      String responseStatus = HttpStatus.valueOf(responseCode).toString();

      logger.info("Response Time: {}ms | Correlation-ID: {} | Response Code: {} | Response Status: {}",
          responseTime, correlationId, responseCode, responseStatus);
    }
  }
}
