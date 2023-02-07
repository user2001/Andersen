package com.example.task9_securityexpand.controller;

import com.example.task9_securityexpand.dto.ProductRequest;
import com.example.task9_securityexpand.dto.ProductResponse;
import com.example.task9_securityexpand.mapper.ProductMapper;
import com.example.task9_securityexpand.model.Product;
import com.example.task9_securityexpand.service.OrderService;
import com.example.task9_securityexpand.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/products")
public class ProductController {
    private final ProductService productService;
    private final OrderService orderService;
    private final ProductMapper productMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.create(productRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse update(@Validated @RequestBody ProductResponse productResponse,
                                  @PathVariable int id) {

        productResponse.setId(id);
        Product product = productMapper.toEntity(productResponse);
        productService.update(product);
        return productResponse;

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> deleteProduct(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            productService.delete(id);
            response.put("status", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            response.put("status", HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }
}
