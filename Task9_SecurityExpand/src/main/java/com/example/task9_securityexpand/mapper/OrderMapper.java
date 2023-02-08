package com.example.task9_securityexpand.mapper;

import com.example.task9_securityexpand.dto.OrderRequest;
import com.example.task9_securityexpand.dto.OrderResponse;
import com.example.task9_securityexpand.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderRequest orderRequest);

    Order toEntity(OrderResponse orderResponse);

    OrderResponse toDto(Order order);

}
