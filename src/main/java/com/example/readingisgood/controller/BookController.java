package com.example.readingisgood.controller;

import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity saveBook(@Valid @RequestBody BookDto book) {
        bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateBook(@Valid @RequestBody BookDto book, @PathVariable String id) {
        bookService.updateBook(book, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
