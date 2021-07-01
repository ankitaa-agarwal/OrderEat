package com.ordereat.OrderEat.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ordereat.OrderEat.Entity.ResponseEntityClass;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ResponseEntityClass> handleAllExceptions(Exception ex, WebRequest request) {
    ResponseEntityClass exceptionResponse = new ResponseEntityClass("failure", null, ex.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NotFoundException.class)
  public final ResponseEntity<ResponseEntityClass> handleNotFoundException(NotFoundException ex, WebRequest request) {
    ResponseEntityClass exceptionResponse = new ResponseEntityClass("failure", null, ex.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public final ResponseEntity<ResponseEntityClass> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
    ResponseEntityClass exceptionResponse = new ResponseEntityClass("failure", null, "Username:"+ex.getMessage()+" not found");
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<ResponseEntityClass> handleBadRequestException(BadRequestException ex, WebRequest request) {
    ResponseEntityClass exceptionResponse = new ResponseEntityClass("failure", null, ex.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }
  
  @ExceptionHandler(AlreadyExistsException.class)
  public final ResponseEntity<ResponseEntityClass> handleAlreadyExistsException(AlreadyExistsException ex, WebRequest request) {
    ResponseEntityClass exceptionResponse = new ResponseEntityClass("failure", null, ex.getMessage());
    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
  }
}