package com.example.readingisgood.service;

import com.example.readingisgood.dto.OrderDto;
import com.example.readingisgood.entity.OrderEntity;
import com.example.readingisgood.exception.ApiException;
import com.example.readingisgood.mapper.OrderMapper;
import com.example.readingisgood.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveOrder(OrderDto order) {
        final OrderEntity entity = orderMapper.mapToEntity(order);
        entity.setOrderDateTime(LocalDateTime.now());
        orderRepository.save(entity);
    }

    public OrderDto findOrderById(String id) {
        final Optional<OrderEntity> foundOrder = orderRepository.findById(id);
        return orderMapper.mapToDto(foundOrder.orElse(null));
    }

    public List<OrderDto> findOrdersBetweenStartAndEnd(LocalDateTime start, LocalDateTime end) {
        Query query = new Query();
        if (start != null && end != null) {
            if (end.isBefore(start)) {
                throw new ApiException("Invalid date interval", "Enddate cannot be before than startdate", HttpStatus.BAD_REQUEST.value());
            }
            query.addCriteria(Criteria.where("orderDateTime").gte(start).andOperator(Criteria.where("orderDateTime").lte(end)));
        }

        List<OrderEntity> result = mongoTemplate.find(query, OrderEntity.class);
        return orderMapper.mapToDto(result);
    }

    //Todo:Pagination
    public List<OrderDto> findOrdersByCustomerId(String customerId) {
        final List<OrderEntity> entities = orderRepository.findAllByCustomer(customerId);
        return orderMapper.mapToDto(entities);
    }
}
