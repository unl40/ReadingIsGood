package com.example.readingisgood.repository;

import com.example.readingisgood.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findAllByCustomer(String customerId);

}
