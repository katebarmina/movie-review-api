package com.barmina.movieapp.exceptions;

public class EmailNotFoundException extends RuntimeException {

  public EmailNotFoundException(String message) {
    super(message);
  }
}
