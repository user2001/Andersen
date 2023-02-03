package com.example.andersen.Task7.controller;

import com.example.andersen.Task7.model.Product;
import com.example.andersen.Task7.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public String getAllProducts(Model theModel) {
        List<Product> products = productService.getAllProducts();
        theModel.addAttribute("products", products);
        return "products_list";
    }
}
