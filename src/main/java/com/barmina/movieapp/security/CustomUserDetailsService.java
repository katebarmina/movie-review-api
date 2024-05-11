package com.barmina.movieapp.security;

import com.barmina.movieapp.exceptions.EmailNotFoundException;
import com.barmina.movieapp.model.User;
import com.barmina.movieapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(repository.findByEmail(email));
        return new UserDetailsImpl(
                optionalUser.orElseThrow(
                        () -> new EmailNotFoundException("User with email " + email + " doesn't exist.")));
    }

}
