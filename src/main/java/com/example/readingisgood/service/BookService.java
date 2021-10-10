package com.example.readingisgood.service;

import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.entity.BookEntity;
import com.example.readingisgood.mapper.BookMapper;
import com.example.readingisgood.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    public void saveBook(BookDto book) {
        final BookEntity entity = bookMapper.mapToEntity(book);
        bookRepository.save(entity);
    }

    public void updateBook(BookDto book, String id) {
        final Optional<BookEntity> foundBook = bookRepository.findById(id);
        if (foundBook.isPresent()) {
            final BookEntity entity = bookMapper.mapToEntity(book);
            bookRepository.save(entity);
        }
    }
}
