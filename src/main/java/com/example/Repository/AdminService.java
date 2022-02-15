package com.example.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Admin;

public interface AdminService extends CrudRepository<Admin, Integer> {

}
