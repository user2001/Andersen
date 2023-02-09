package com.example.task9_securityexpand.service;

import com.example.task9_securityexpand.dto.ProductRequest;
import com.example.task9_securityexpand.dto.ProductResponse;
import com.example.task9_securityexpand.mapper.ProductMapper;
import com.example.task9_securityexpand.model.Product;
import com.example.task9_securityexpand.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse create(ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    public List<ProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toDto).toList();
    }

    public ProductResponse getProductById(int productId) {
        Product product = productRepository.findById(productId).get();
        return productMapper.toDto(product);
    }

    public void delete(int productId) {
        productRepository.deleteById(productId);
    }

    public List<ProductResponse> getByOrderId(int orderId) {
        return productRepository.getByOrderId(orderId).stream()
                .map(productMapper::toDto).toList();
    }

    public Product update(Product product) {
        if (product != null) {
            getProductById(product.getId());
            return productRepository.save(product);
        }
        throw new IllegalArgumentException("Product cannot be 'null'");
    }


}
