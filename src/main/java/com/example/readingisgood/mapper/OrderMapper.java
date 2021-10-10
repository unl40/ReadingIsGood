package com.example.readingisgood.mapper;

import com.example.readingisgood.dto.OrderDto;
import com.example.readingisgood.entity.OrderEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderEntity mapToEntity(OrderDto dto);

    OrderDto mapToDto(OrderEntity entity);

    List<OrderEntity> mapToEntity(List<OrderDto> dtos);

    List<OrderDto> mapToDto(List<OrderEntity> entities);
}
