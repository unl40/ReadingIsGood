package com.example.readingisgood.controller;

import com.example.readingisgood.dto.CustomerDto;
import com.example.readingisgood.dto.OrderDto;
import com.example.readingisgood.service.CustomerService;
import com.example.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity saveCustomer(@Valid @RequestBody CustomerDto customer) {
        customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getCustomers(@RequestParam String customerId) {
        final List<OrderDto> result = orderService.findOrdersByCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
