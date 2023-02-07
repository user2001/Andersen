package com.example.task9_securityexpand.mapper;

import com.example.task9_securityexpand.dto.ProductDto;
import com.example.task9_securityexpand.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);

}
