package com.example.readingisgood.repository;

import com.example.readingisgood.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookEntity, String> {

}
