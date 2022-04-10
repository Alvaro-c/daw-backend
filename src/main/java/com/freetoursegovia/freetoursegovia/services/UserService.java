package com.freetoursegovia.freetoursegovia.services;

import com.freetoursegovia.freetoursegovia.model.User;
import com.freetoursegovia.freetoursegovia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(User test){
        return userRepository.save(test);
    }

    public User findUserById(int id) { return userRepository.findById(id).orElseThrow(); }

    public void deleteUser(int id) { userRepository.deleteById(id);}


}
