package com.freetoursegovia.freetoursegovia.controller;

import com.freetoursegovia.freetoursegovia.Utils.Mail;
import com.freetoursegovia.freetoursegovia.model.Availability;
import com.freetoursegovia.freetoursegovia.model.Client;
import com.freetoursegovia.freetoursegovia.repository.AvailabilityRepository;
import com.freetoursegovia.freetoursegovia.repository.TourRepository;
import com.freetoursegovia.freetoursegovia.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class map incoming request to public pages
 */
@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    AvailabilityRepository availabilityRepository;
    @Autowired
    AvailabilityService availabilityService;
    @Autowired
    TourRepository tourRepository;

    /**
     * Mapping to landing page
     * @param availability
     * @param client
     * @return
     */
    @GetMapping("")
    public String homeController(Availability availability, Client client) {

        log.info("----# Generic connection to home Mapped");

        return "index";
    }
    /**
     * Mapping to la_visita
     */
    @GetMapping("/la_visita")
    public String laVisitaController() {

        log.info("----# Connection to la_visita Mapped");

        return "la_visita";
    }

    /**
     * Mapping to cookies
     * @return
     */
    @GetMapping("/cookies")
    public String cookiesController() {

        log.info("----# Connection to cookies Mapped");

        return "cookies";
    }


}
