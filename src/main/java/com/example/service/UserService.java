package com.example.service;

import org.springframework.data.repository.CrudRepository;

import com.example.model.User;

public interface UserService extends CrudRepository<User, Integer> {

}
