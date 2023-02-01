package com.example.andersen.Task7.controller;

import com.example.andersen.Task7.model.Order;
import com.example.andersen.Task7.model.Product;
import com.example.andersen.Task7.model.User;
import com.example.andersen.Task7.service.OrderService;
import com.example.andersen.Task7.service.ProductService;
import com.example.andersen.Task7.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/all/users/{user_id}")
    public String getAll(@PathVariable("user_id") int userId, Model model) {
        List<Order> orders = orderService.getByUserId(userId);
        model.addAttribute("orders", orders);
        model.addAttribute("user", userService.readById(userId));
        return "order-user";
    }

    @GetMapping("/create/users/{owner_id}")
    public String create(@PathVariable("owner_id") int ownerId, Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("ownerId", ownerId);
        return "order-create";
    }

    @PostMapping("/create/users/{owner_id}")
    public String create(@PathVariable("owner_id") int ownerId, @ModelAttribute("bucket") Order order) {
        order.setOwner(userService.readById(ownerId));
        orderService.create(order);
        return "redirect:/orders/all/users/" + ownerId;
    }

    @GetMapping("/{id}/products")
    public String read(@PathVariable int id, Model model) {
        Order order = orderService.readById(id);
        List<Product> products = productService.getAllProductInBucket(id);
        List<Product> allProducts = productService.getAllProducts();
        List<User> users = userService.getAll().stream()
                .filter(user -> user.getId() != order.getOwner().getId()).collect(Collectors.toList());
        model.addAttribute("order", order);
        model.addAttribute("products", products);
        model.addAttribute("allProducts", allProducts);
        model.addAttribute("users", users);
        return "order-products";
    }

    @GetMapping("/{id}/add")
    public String addProduct(@PathVariable int id, @RequestParam int productId) {
        Order order = orderService.readById(id);
        List<Product> orderedProducts = order.getOrderedProducts();
        orderedProducts.add(productService.getProductById(productId));
        order.setOrderedProducts(orderedProducts);
        orderService.update(order);
        return "redirect:/orders/" + id + "/products";
    }

    @GetMapping("/{id}/remove")
    public String removeCollaborator(@PathVariable int id, @RequestParam int productId) {
        Order order = orderService.readById(id);
        List<Product> orderedProducts = order.getOrderedProducts();
        orderedProducts.remove(productService.getProductById(productId));
        order.setOrderedProducts(orderedProducts);
        orderService.update(order);
        return "redirect:/orders/" + id + "/products";
    }


}
