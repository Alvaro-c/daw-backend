package com.freetoursegovia.freetoursegovia.controller;


import com.freetoursegovia.freetoursegovia.Utils.Mail;
import com.freetoursegovia.freetoursegovia.model.Availability;
import com.freetoursegovia.freetoursegovia.model.Client;
import com.freetoursegovia.freetoursegovia.model.Tour;
import com.freetoursegovia.freetoursegovia.services.AvailabilityService;
import com.freetoursegovia.freetoursegovia.services.ClientService;
import com.freetoursegovia.freetoursegovia.services.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller class to map incoming request from browser
 */
@Controller
public class BookingController {

    @Autowired
    AvailabilityService availabilityService;
    @Autowired
    TourService tourService;
    @Autowired
    ClientService clientService;


    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    /**
     * Map incoming request directly to /bookingform without information attached to them
     * @return
     */
    @GetMapping("/bookingForm")
    public String bookingFormControllerGet(Availability availability, Client client) {
        log.info("----# Booking form Get request");

        return "bookingFormGet";
    }


    /**
     * Map incoming request from landing page. This information has attached
     * the visitDate attribute in the object Availability
     * The method returns the booking form with the info regarding Availability filled
     * @param availability
     * @param model
     * @return
     */
    @PostMapping("/bookingForm")
    public String bookingFormController(Availability availability, Client client, Model model) {


        log.info("----# Availability check request");


        model.addAttribute("localDate", LocalDate.now());

        Availability a = availabilityService.findByVisitDate(availability.getVisitDate());

        List<Availability> allAvail = availabilityService.findAll();

        //Find the correct Availability object
        for (int i = 0; i < allAvail.size(); i ++) {

            if (allAvail.get(i).getVisitDate() == a.getVisitDate()) {

                availability.setAvailabilityId(a.getAvailabilityId());
                availability.setTour(a.getTour());
                availability.setVisitDate(a.getVisitDate());
                availability.setAvailability(a.getAvailability());

            }
        }
        // Check for availability to show either the booking form or an advise of lack of availability
        if (availability.getAvailability() >= 1) {

            return "bookingForm";
        } else {

            return "no-hay-plazas-libres";
        }

    }

    /**
     *  This method receives the information from the booking form already filled
     *  It will cehck if the booking is possible and if so, send a confirmation email
     *  and redirect to the confirmation page.
     *  Otherwise it will send the client to the error page.
     * @param availability
     * @param client
     * @return
     */
    @PostMapping("/bookingRequest")
    public String bookingRequest(Availability availability, Client client){

        log.info("----------Booking request received");

        // Get the availability
        Availability a = availabilityService.findByVisitDate(availability.getVisitDate());

        List<Availability> allAvail = availabilityService.findAll();

        for (int i = 0; i < allAvail.size(); i ++) {

            if (allAvail.get(i).getVisitDate() == a.getVisitDate()) {

                availability.setAvailabilityId(a.getAvailabilityId());
                availability.setTour(a.getTour());
                availability.setVisitDate(a.getVisitDate());
                availability.setAvailability(a.getAvailability());

            }
        }

            if (availability.getAvailability() - client.getAdults() - client.getChildren() >= 0){

                Tour tour = tourService.findById(1).get();
                client.setTour(tour);
                System.out.println("FLAG ---- " + tour);
                System.out.println("FLAG ---- " + client);

                // Second layer of security to check if somehow the booking is made for no people.
                // First layer is on the HTML
                if (client.getAdults() + client.getChildren() == 0) {
                    return "error-en-la-reserva";
                }

                availability.setAvailability(availability.getAvailability() - client.getAdults() - client.getChildren());
                availabilityService.editAvailability(availability);
                client.setVisitDate(availability.getVisitDate());

                clientService.saveClient(client);
                System.out.println(availability);

                Mail mail = new Mail();
                if (mail.sendEmail(client)) {
                    // If mail is sent, confirmation is sent to the client as well

                    mail.sendBookingToMe(client);
                    return "confirmacion";
                } else {
                    // In case of error within the booking or the email confirmation, the client is redirected to the error page
                    return "error-en-la-reserva";
                }



            } else {

            // In case of error within the booking or the email confirmation, the client is redirected to the error page
            return "error-en-la-reserva";
        }


    }

}
