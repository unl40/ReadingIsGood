package com.example.readingisgood.mapper;

import com.example.readingisgood.dto.BookDto;
import com.example.readingisgood.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookEntity mapToEntity(BookDto dto);

    BookDto mapToDto(BookEntity entity);
}
