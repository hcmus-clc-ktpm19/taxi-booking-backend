package com.hcmus.customerservice.controller;

import com.hcmus.customerservice.model.exception.AccountNotFoundException;
import com.hcmus.customerservice.model.exception.ExistedAccountException;
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
public class ExceptionAdviceController {

  private final Logger logger = LoggerFactory.getLogger(ExceptionAdviceController.class);

  @ExceptionHandler(ExistedAccountException.class)
  public ResponseEntity<Map<String, String>> handleExistedAccountException(
      ExistedAccountException e) {
    logger.error("Account with phone {} already exists", e.getPhone());

    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(Map.of("Error-Message", e.getMessage() + ": " + e.getPhone()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {

    logger.error("Validation error: {}", ex.getMessage());

    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    return ResponseEntity
        .badRequest()
        .body(errors);
  }

  @ExceptionHandler(AccountNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleAccountNotFoundException(
      AccountNotFoundException e) {
    logger.error("Account with phone {} not found", e.getPhone());

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(Map.of("Error-Message", e.getMessage() + ": " + e.getPhone()));
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<Map<String, String>> handleTokenExpiredException(Throwable e) {
    String message = e.getMessage();
    logger.error(message);

    return ResponseEntity
        .badRequest()
        .body(Map.of("Error-Message", message));
  }
}