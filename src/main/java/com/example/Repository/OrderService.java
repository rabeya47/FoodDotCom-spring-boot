package com.example.Repository;

import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Order;

@Repository
public interface OrderService extends CrudRepository<Order, Integer> {



}
