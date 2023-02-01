package com.example.andersen.Task7.service;

import com.example.andersen.Task7.currency.Currency;
import com.example.andersen.Task7.currency.CurrencyResolver;
import com.example.andersen.Task7.dto.ProductDto;
import com.example.andersen.Task7.model.Order;
import com.example.andersen.Task7.model.Product;
import com.example.andersen.Task7.repository.OrderRepository;
import com.example.andersen.Task7.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CurrencyResolver currencyResolver;
    private final double UA_SELL_PERCENT = 1.2;
    private final double NON_UA_SELL_PERCENT = 1.8;


    public BigDecimal sell_price(ProductDto productDto) {
        if (!productDto.getCurrency().equals(Currency.UAH)) {
            return productDto.getPrice().multiply(BigDecimal.valueOf(NON_UA_SELL_PERCENT));
        }
        return productDto.getPrice().multiply(BigDecimal.valueOf(UA_SELL_PERCENT));
    }

    public BigDecimal priceInUAH(ProductDto productDto) {
        BigDecimal sellPrice = sell_price(productDto);
        return currencyResolver.exchangeIntoUAH(Currency.valueOf(productDto.getCurrency()), sellPrice);
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).get();
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.isEmpty() ? new ArrayList<>() : products;
    }

    public List<Product> getAllProductInBucket(int bucketId) {
        Order bucket = orderRepository.findById(bucketId).get();
        return bucket.getOrderedProducts();
    }


}
