package com.example.springaotdemo.controllers;

import com.example.springaotdemo.entities.User;
import com.example.springaotdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HelloController {
    @Autowired
    UserService service;
    @GetMapping("/hello")
   public List<User> hello(){
        return service.findAll();
    }
    @PostMapping("/insert")
   public User insert(@RequestBody User user){
       return service.insert(user);
    }
}
