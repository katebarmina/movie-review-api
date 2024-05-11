package com.barmina.movieapp.security.authentication;

import org.springframework.security.core.Authentication;

public interface AuthenticationWrapper {

    Authentication getAuthentication();

}
