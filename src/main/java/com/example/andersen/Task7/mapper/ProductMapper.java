package com.example.andersen.Task7.mapper;

import com.example.andersen.Task7.dto.ProductDto;
import com.example.andersen.Task7.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);

}
