package com.example.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Cart;

public interface CartRepo extends CrudRepository<Cart, Integer> {

}
