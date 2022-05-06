package com.freetoursegovia.freetoursegovia.controller;

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
            //controlUser.setRol(session.getId());
            return controlUser;
        } catch (Exception e){
            System.out.println(e);
            User notFound = new User();
            notFound.setName("User not found");
            notFound.setRol("0");
            return notFound;
        }

    }

}