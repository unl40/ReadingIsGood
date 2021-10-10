package com.example.readingisgood.service;

import com.example.readingisgood.dto.CustomerDto;
import com.example.readingisgood.entity.CustomerEntity;
import com.example.readingisgood.exception.ApiException;
import com.example.readingisgood.mapper.CustomerMapper;
import com.example.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    public void saveCustomer(CustomerDto customer) {
        if (customerRepository.findCustomerByEmail(customer.getEmail()) != null) {
            throw new ApiException("Email is not unique", "This email is already taken. Please use a different email adress",
                    HttpStatus.BAD_REQUEST.value());
        }
        final CustomerEntity entity = customerMapper.mapToEntity(customer);
        customerRepository.save(entity);
    }
}
