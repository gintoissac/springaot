package com.example.springaotdemo.service;

import com.example.springaotdemo.entities.User;
import com.example.springaotdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;
    public User insert(User user){
       return repository.save(user);

    }
    public List<User> findAll(){
        return repository.findAll();

    }
}
