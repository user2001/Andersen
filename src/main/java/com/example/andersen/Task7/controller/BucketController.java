package com.example.andersen.Task7.controller;

import com.example.andersen.Task7.dto.ProductDto;
import com.example.andersen.Task7.model.Product;
import com.example.andersen.Task7.service.BucketService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/bucket")
public class BucketController {
    private final BucketService bucketService;

    @GetMapping("/all")
    public String showAllProduct(Model theModel) {
        List<ProductDto> products = bucketService.getAllProducts();
        theModel.addAttribute("products", products);
        return "home";
    }
}
