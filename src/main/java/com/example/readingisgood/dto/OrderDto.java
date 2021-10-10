package com.example.readingisgood.dto;

import com.example.readingisgood.entity.CustomerEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private String id;

    @NotEmpty(message = "books cannot be empty")
    private List<BookDto> books = new ArrayList<>();

    @NotNull(message = "customer cannot be null")
    private CustomerEntity customer;

    private LocalDateTime orderDateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public BigDecimal getTotalOrderAmount() {
        return books.stream().map(s -> s.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
