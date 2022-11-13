package com.barmina.movieapp.security.authentication.impl;

import com.barmina.movieapp.security.authentication.AuthenticationWrapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationWrapperImpl implements AuthenticationWrapper {

  @Override
  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}
