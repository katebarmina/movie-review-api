package com.barmina.movieapp.service.impl;

import com.barmina.movieapp.exceptions.UserNotFoundException;
import com.barmina.movieapp.model.User;
import com.barmina.movieapp.repository.UserRepository;
import com.barmina.movieapp.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.management.InstanceAlreadyExistsException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserServiceImpl(repository);
    }

    @Test
    void willThrowExceptionWhenAlreadyExists() {
        User user = new User();
        user.setName("Jane");
        user.setEmail("jane@gmail.com");
        user.setPassword("password");
        given(repository.findByEmail(user.getEmail())).willReturn(user);
        assertThatThrownBy(() -> service.create(user))
                .isInstanceOf(InstanceAlreadyExistsException.class)
                .hasMessageContaining("User with email "
                        + user.getEmail()
                        + " already exists.");
    }

    @Test
    void willThrowExceptionWhenNotFound() {
        given(repository.findById(1)).willReturn(Optional.empty());
        assertThatThrownBy(() -> service.getById(1))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User with id " + 1 + " doesn't exist. ");
    }

    @Test
    void create() throws InstanceAlreadyExistsException {
        User user = new User();
        user.setName("Jane");
        user.setEmail("jane@gmail.com");
        user.setPassword("password");
        service.create(user);
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(repository).save(argumentCaptor.capture());
        Assertions.assertEquals(user, argumentCaptor.getValue());
    }

    @Test
    void getAll() {
        service.getAll();
        verify(repository).findAll();
    }

    @Test
    void getById() {
        given(repository.findById(anyInt())).willReturn(Optional.of(new User()));
        service.getById(anyInt());
        verify(repository).findById(anyInt());
    }

    @Test
    void update() {
        User user = new User();
        user.setName("Jane");
        user.setEmail("jane@gmail.com");
        user.setPassword("password");
        service.update(user, anyInt());
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(repository).save(argumentCaptor.capture());
        Assertions.assertEquals(user, argumentCaptor.getValue());
    }

    @Test
    void findByEmail() {
        service.findByEmail(anyString());
        verify(repository).findByEmail(anyString());
    }

    @Test
    void deleteById() {
        service.deleteById(anyInt());
        verify(repository).deleteById(anyInt());
    }
}