package com.barmina.movieapp.controller;

import com.barmina.movieapp.exceptions.EmailNotFoundException;
import com.barmina.movieapp.exceptions.MovieNotFoundException;
import com.barmina.movieapp.exceptions.ReviewNotFoundException;
import com.barmina.movieapp.exceptions.UserNotFoundException;
import com.barmina.movieapp.exceptions.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.management.InstanceAlreadyExistsException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({
    UserNotFoundException.class,
    MovieNotFoundException.class,
    ReviewNotFoundException.class,
    EmailNotFoundException.class
  })
  public ResponseEntity<ErrorDetails> handleNotFoundExceptions(Exception ex, WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InstanceAlreadyExistsException.class)
  public ResponseEntity<ErrorDetails> handleAlreadyExistsException(Exception exception,WebRequest request){
    ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),exception.getMessage(),request.getDescription(false));
    return new ResponseEntity<>(errorDetails,HttpStatus.CONFLICT);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ErrorDetails errorDetails =
        new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
