package com.example.readingisgood.service;

import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.entity.BookEntity;
import com.example.readingisgood.mapper.BookMapper;
import com.example.readingisgood.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void saveBook() {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn("IsbnTest");
        bookDto.setName("NameTest");
        bookDto.setPrice(BigDecimal.ONE);

        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("IsbnTest");
        bookEntity.setName("NameTest");
        bookEntity.setPrice(BigDecimal.ONE);
        ArgumentCaptor<BookEntity> argumentCaptor = ArgumentCaptor.forClass(BookEntity.class);

        when(bookMapper.mapToEntity(bookDto)).thenReturn(bookEntity);

        bookService.saveBook(bookDto);

        verify(bookRepository).save(argumentCaptor.capture());
        assertEquals(bookEntity.getIsbn(), argumentCaptor.getValue().getIsbn());
        assertEquals(bookEntity.getName(), argumentCaptor.getValue().getName());
        assertEquals(bookEntity.getPrice(), argumentCaptor.getValue().getPrice());
    }

    @Test
    public void updateBook_currentBookIsFound_shouldUpdateBook() {
        ArgumentCaptor<BookEntity> argumentCaptor = ArgumentCaptor.forClass(BookEntity.class);

        BookDto bookDto = new BookDto();
        bookDto.setIsbn("IsbnTest");
        bookDto.setName("NameTest");
        bookDto.setPrice(BigDecimal.ONE);

        BookEntity bookEntity = new BookEntity();
        bookEntity.setIsbn("IsbnTest");
        bookEntity.setName("NameTest");
        bookEntity.setPrice(BigDecimal.ONE);

        BookEntity oldBookEntity = new BookEntity();
        oldBookEntity.setIsbn("IsbnTestOld");
        oldBookEntity.setName("NameTestOld");
        oldBookEntity.setPrice(BigDecimal.TEN);

        when(bookRepository.findById(eq("1"))).thenReturn(Optional.of(oldBookEntity));
        when(bookMapper.mapToEntity(bookDto)).thenReturn(bookEntity);

        bookService.updateBook(bookDto, "1");

        verify(bookRepository).save(argumentCaptor.capture());
        assertEquals(bookEntity.getIsbn(), argumentCaptor.getValue().getIsbn());
        assertEquals(bookEntity.getName(), argumentCaptor.getValue().getName());
        assertEquals(bookEntity.getPrice(), argumentCaptor.getValue().getPrice());
    }

    @Test
    public void updateBook_currentBookIsNotFound_shouldNotDoAnything() {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn("IsbnTest");
        bookDto.setName("NameTest");
        bookDto.setPrice(BigDecimal.ONE);

        when(bookRepository.findById(eq("1"))).thenReturn(Optional.empty());

        bookService.updateBook(bookDto, "1");

        verify(bookRepository, never()).save(any());
    }
}