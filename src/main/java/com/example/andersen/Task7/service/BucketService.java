package com.example.andersen.Task7.service;

import com.example.andersen.Task7.dto.ProductDto;
import com.example.andersen.Task7.mapper.ProductMapper;
import com.example.andersen.Task7.model.Bucket;
import com.example.andersen.Task7.model.Product;
import com.example.andersen.Task7.model.User;
import com.example.andersen.Task7.repository.BucketRepository;
import com.example.andersen.Task7.repository.ProductRepository;
import com.example.andersen.Task7.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BucketService {
    private final ProductRepository productRepository;
    private final BucketRepository bucketRepository;
    private final UserRepository userRepository;

    private final ProductMapper productMapper;

    public void addProductToTheBucket(Product product, int userId) {
        User owner = getUserById(userId);
        Bucket bucket = new Bucket();
        bucket.setOrderedProducts(List.of(product));
        bucket.setOwner(owner);
        bucketRepository.save(bucket);
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).get();
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtoList=new ArrayList<>();
        for (Product product:products) {
            productDtoList.add(productMapper.toDto(product));
        }
        return products.isEmpty() ? new ArrayList<>() : productDtoList;
    }


}
