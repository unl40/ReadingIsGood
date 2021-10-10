package com.example.readingisgood.mapper;

import com.example.readingisgood.dto.CustomerDto;
import com.example.readingisgood.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity mapToEntity(CustomerDto dto);

    CustomerDto mapToDto(CustomerEntity entity);
}
