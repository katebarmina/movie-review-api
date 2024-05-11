package com.barmina.movieapp.service.impl;

import com.barmina.movieapp.exceptions.UserNotFoundException;
import com.barmina.movieapp.model.Role;
import com.barmina.movieapp.model.User;
import com.barmina.movieapp.repository.UserRepository;
import com.barmina.movieapp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User create(User user) throws InstanceAlreadyExistsException {
        PasswordEncoder encoder = new BCryptPasswordEncoder(10);
        if (Objects.nonNull(userRepository.findByEmail(user.getEmail()))) {
            log.debug("Couldn't create user.");
            throw new InstanceAlreadyExistsException(
                    "User with email " + user.getEmail() + " already exists.");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        user.setRoles(roles);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);
        log.debug("Saved user with email " + user.getEmail());
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " doesn't exist. "));
    }

    @Override
    public User update(User newUser, Integer id) {
        return userRepository
                .findById(id)
                .map(
                        user -> {
                            user.setName(newUser.getName());
                            user.setEmail(newUser.getEmail());
                            user.setRoles(newUser.getRoles());
                            user.setEnabled(newUser.getEnabled());
                            return userRepository.save(user);
                        })
                .orElseGet(
                        () -> {
                            newUser.setId(id);
                            return userRepository.save(newUser);
                        });
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

}
