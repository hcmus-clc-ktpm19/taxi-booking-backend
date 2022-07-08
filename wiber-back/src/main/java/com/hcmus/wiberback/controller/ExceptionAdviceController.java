package com.hcmus.wiberback.controller;

import java.util.Map;
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
  public ResponseEntity<Map<String, String>> handleTokenExpiredException(Throwable e) {
    String message = e.getMessage();
    logger.error(message);

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(Map.of("Error-Message", message));
  }
}