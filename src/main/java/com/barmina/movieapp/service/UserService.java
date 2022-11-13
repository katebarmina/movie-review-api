package com.barmina.movieapp.service;

import com.barmina.movieapp.model.User;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;

public interface UserService {
  User create(User user) throws InstanceAlreadyExistsException;

  List<User> getAll();

  User findByEmail(String email);

  User getById(Integer id);

  User update(User user, Integer id);

  void deleteById(Integer id);
}
