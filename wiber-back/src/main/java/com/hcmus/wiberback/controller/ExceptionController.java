package com.hcmus.wiberback.controller;

import com.hcmus.wiberback.entity.exception.AccountExistedException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

  @ExceptionHandler(AccountExistedException.class)
  public ResponseEntity<Map<String, String>> handleAccountExistedException(
      AccountExistedException e) {
    LOGGER.error(e.getMessage(), e);
    Map<String, String> response = new HashMap<>();
    response.put("Error-Message", e.getMessage() + ": " + e.getPhone());

    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return errors;
  }


  @ExceptionHandler(Throwable.class)
  public ResponseEntity<Map<String, String>> handleTokenExpiredException(Throwable e) {
    String message = e.getMessage();
    LOGGER.error(message);

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(Map.of("Error-Message", message));
  }
}