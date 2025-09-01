package com.quiz.arjun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.quiz.arjun.entites.User;
import com.quiz.arjun.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }
        return "login";
    }
    
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "signup";
        }
        
        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Username already exists");
            return "signup";
        }
        
        user.setRole(User.Role.USER);
        userService.saveUser(user);
        model.addAttribute("message", "Registration successful! Please login.");
        return "login";
    }
}
