package com.hcmus.customerservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviceController {

  private final Logger logger = LoggerFactory.getLogger(ExceptionAdviceController.class);

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<String> handleTokenExpiredException(Throwable e) {
    String message = e.getMessage();
    logger.warn(message);
    logger.info("keke");

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
  }
}