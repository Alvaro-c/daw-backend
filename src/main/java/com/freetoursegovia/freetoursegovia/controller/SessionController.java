package com.freetoursegovia.freetoursegovia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.freetoursegovia.freetoursegovia.model.User;
import com.freetoursegovia.freetoursegovia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@SessionAttributes({"user_id"})
@RequestMapping(path = "/api/login")
public class SessionController {

    @Autowired
    UserService userService;

    /**
        Login control
     */
    @PostMapping("/access")
    public User login(@RequestBody User user, HttpSession session) {

        try{
            User controlUser = userService.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
            // Create session with user id
            session.setAttribute("user_id",controlUser.getId());
            controlUser.setRol(session.getId());
            return controlUser;
        } catch (Exception e){
            System.out.println(e);
            User notFound = new User();
            notFound.setName("User not found");
            notFound.setRol("0");
            return notFound;
        }

    }


    // Function to check if user has session initiated
    @GetMapping("/session")
    public User checkSession(HttpSession session){

        User user = userService.findUserById(6);

        return user;
    }


    @GetMapping("/test")                     // it will handle all request for /welcome
    public java.util.Map<String,Integer> start(HttpServletRequest request) {

        Integer integer =(Integer) request.getSession().getAttribute("hitCounter");  //it will read data from database tables

        if(integer==null){
            integer= Integer.valueOf(0);
            integer++;
            request.getSession().setAttribute("hitCounter",integer);  // it will write data to tables
        }else{
            integer++;
            request.getSession().setAttribute("hitCounter",integer);  // it will write data to tables
        }
        java.util.Map<String,Integer> hitCounter=new HashMap<>();
        hitCounter.put("Hit Counter",integer);
        return hitCounter;
    }

}