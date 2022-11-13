package com.barmina.movieapp.repository;

import com.barmina.movieapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  User findByEmail(@Email String email);
}
