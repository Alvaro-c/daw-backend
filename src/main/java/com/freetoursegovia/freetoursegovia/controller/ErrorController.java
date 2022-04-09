package com.freetoursegovia.freetoursegovia.controller;

import com.freetoursegovia.freetoursegovia.services.AvailabilityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ErrorController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    AvailabilityService availabilityService;

//    @GetMapping("/error")
//    public String errorRequest(Availability availability){
//
//
//
//        return "error";
//    }


}
