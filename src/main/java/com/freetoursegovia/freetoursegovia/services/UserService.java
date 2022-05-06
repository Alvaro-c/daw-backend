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

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User findUserById(int id) { return userRepository.findById(id).orElseThrow(); }

    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findUsersByEmailAndAndPassword(email, password);
    }

    public User findUserByEmail(String email) {return userRepository.findUsersByEmail(email);}

    public void deleteUser(int id) { userRepository.deleteById(id);}


}
