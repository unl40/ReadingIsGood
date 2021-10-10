package com.example.readingisgood.repository;

import com.example.readingisgood.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {

    CustomerEntity findCustomerByEmail(String email);

}
