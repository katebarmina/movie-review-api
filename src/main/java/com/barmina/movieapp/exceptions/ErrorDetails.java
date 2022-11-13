package com.barmina.movieapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails {
  private LocalDateTime timestamp;
  private String message;
  private String description;
}
