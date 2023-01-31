package com.example.andersen.Task7.controller;

import com.example.andersen.Task7.model.Bucket;
import com.example.andersen.Task7.model.Product;
import com.example.andersen.Task7.model.User;
import com.example.andersen.Task7.service.BucketService;
import com.example.andersen.Task7.service.ProductService;
import com.example.andersen.Task7.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/buckets")
public class BucketController {
    private final BucketService bucketService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/all/users/{user_id}")
    public String getAll(@PathVariable("user_id") int userId, Model model) {
        List<Bucket> orders = bucketService.getByUserId(userId);
        model.addAttribute("orders", orders);
        model.addAttribute("user", userService.readById(userId));
        return "bucket-user";
    }

    @GetMapping("/create/users/{owner_id}")
    public String create(@PathVariable("owner_id") int ownerId, Model model) {
        model.addAttribute("bucket", new Bucket());
        model.addAttribute("ownerId", ownerId);
        return "bucket-create";
    }
    @PostMapping("/create/users/{owner_id}")
    public String create(@PathVariable("owner_id") int ownerId, @ModelAttribute("bucket") Bucket bucket) {
        bucket.setOwner(userService.readById(ownerId));
        bucketService.create(bucket);
        return "redirect:/buckets/all/users/" + ownerId;
    }
    @GetMapping("/{id}/products")
    public String read(@PathVariable int id, Model model) {
        Bucket bucket = bucketService.readById(id);
        List<Product> products = productService.getAllProductInBucket(id);
        List<Product> allProducts=productService.getAllProducts();
        List<User> users = userService.getAll().stream()
                .filter(user -> user.getId() != bucket.getOwner().getId()).collect(Collectors.toList());
        model.addAttribute("bucket", bucket);
        model.addAttribute("products", products);
        model.addAttribute("allProducts",allProducts);
        model.addAttribute("users", users);
        return "bucket-products";
    }

    @GetMapping("/{id}/add")
    public String addProduct(@PathVariable int id, @RequestParam int productId) {
        Bucket bucket = bucketService.readById(id);
        List<Product> orderedProducts = bucket.getOrderedProducts();
        orderedProducts.add(productService.getProductById(productId));
        bucket.setOrderedProducts(orderedProducts);
        bucketService.update(bucket);
        return "redirect:/buckets/" + id + "/products";
    }
    @GetMapping("/{id}/remove")
    public String removeCollaborator(@PathVariable int id, @RequestParam int productId) {
        Bucket bucket = bucketService.readById(id);
        List<Product> orderedProducts = bucket.getOrderedProducts();
        orderedProducts.remove(productService.getProductById(productId));
        bucket.setOrderedProducts(orderedProducts);
        bucketService.update(bucket);
        return "redirect:/buckets/" + id + "/products";
    }


}
