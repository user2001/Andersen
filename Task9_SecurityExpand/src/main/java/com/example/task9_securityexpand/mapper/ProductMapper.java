package com.example.task9_securityexpand.mapper;

import com.example.task9_securityexpand.dto.ProductRequest;
import com.example.task9_securityexpand.dto.ProductResponse;
import com.example.task9_securityexpand.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequest productRequest);

    Product toEntity(ProductResponse productResponse);

    ProductResponse toDto(Product product);

}
