package com.example.andersen.Task7.controller;

import com.example.andersen.Task7.model.User;
import com.example.andersen.Task7.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public String getAll(Model theModel) {
        List<User> users = userService.getAll();
        theModel.addAttribute("users", users);
        return "users_list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "create-user";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") User user, BindingResult result) {

        if (result.hasErrors()) {
            throw new IllegalArgumentException("User cannot be null");
        }
        User newUser = userService.create(user);
        return "redirect:/buckets/all/users/" + newUser.getId();
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users/all";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable int id, Model model) {
        User user = userService.readById(id);
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable int id, @Validated @ModelAttribute("user") User user, BindingResult result, @RequestParam("roleId") long roleId, Model model) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException("User with id: " + id + " cannot have null parameters");
        }
        userService.update(user);
        return "redirect:/users/" + id + "/read";
    }

    @GetMapping("/{id}/read")
    public String read(@PathVariable int id, Model model) {
        User user = userService.readById(id);
        model.addAttribute("user", user);
        return "user-info";
    }
}
