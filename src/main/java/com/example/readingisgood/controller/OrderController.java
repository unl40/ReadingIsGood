package com.example.readingisgood.controller;

import com.example.readingisgood.dto.OrderDto;
import com.example.readingisgood.mapper.OrderMapper;
import com.example.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity saveOrder(@Valid @RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OrderDto> findOrderById(@PathVariable String id) {
        final OrderDto result = orderService.findOrderById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> findOrdersBetweenStartAndEnd(@PathVariable LocalDateTime start, @PathVariable LocalDateTime end) {
        final List<OrderDto> result = orderService.findOrdersBetweenStartAndEnd(start, end);
        return ResponseEntity.ok(result);
    }

}
