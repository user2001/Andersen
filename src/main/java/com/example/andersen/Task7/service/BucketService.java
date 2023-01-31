package com.example.andersen.Task7.service;

import com.example.andersen.Task7.mapper.ProductMapper;
import com.example.andersen.Task7.model.Bucket;
import com.example.andersen.Task7.repository.BucketRepository;
import com.example.andersen.Task7.repository.ProductRepository;
import com.example.andersen.Task7.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BucketService {
    private final ProductRepository productRepository;
    private final BucketRepository bucketRepository;
    private final UserRepository userRepository;

    private final ProductMapper productMapper;

    public Bucket create(Bucket bucket) {
        return bucketRepository.save(bucket);
    }

    public Bucket getBucketById(int bucketId) {
        return bucketRepository.findById(bucketId).get();
    }

    public List<Bucket> getAllBuckets() {
        return bucketRepository.findAll();
    }

    public List<Bucket> getByUserId(int userId) {
        List<Bucket> buckets = bucketRepository.getByUserId(userId);
        return buckets.isEmpty() ? new ArrayList<>() : buckets;
    }

    public Bucket readById(int id) {
        Optional<Bucket> optional = bucketRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("Order with id " + id + " not found");
    }

    public void delete(int id) {
        Bucket bucket = readById(id);

        if (bucket == null) {
            throw new EntityNotFoundException("Bucket with id " + id + " not found");
        }
        bucketRepository.delete(bucket);
    }

    public Bucket update(Bucket bucket) {

        if (bucket == null) {
            throw new EntityNotFoundException("ToDo can`t be null");
        }
        Bucket oldBucket;
        try {
            oldBucket = readById(bucket.getId());
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Bucket with id " + bucket.getId() + " not found");
        }

        if (oldBucket == null) {
            throw new EntityNotFoundException("Bucket can`t be null");
        }
        return bucketRepository.save(bucket);
    }


}
