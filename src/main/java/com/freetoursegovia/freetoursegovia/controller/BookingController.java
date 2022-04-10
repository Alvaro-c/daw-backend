package com.freetoursegovia.freetoursegovia.controller;


import com.freetoursegovia.freetoursegovia.Utils.Mail;
import com.freetoursegovia.freetoursegovia.model.Availability;
import com.freetoursegovia.freetoursegovia.model.Client;
import com.freetoursegovia.freetoursegovia.model.Tour;
import com.freetoursegovia.freetoursegovia.model.User;
import com.freetoursegovia.freetoursegovia.services.AvailabilityService;
import com.freetoursegovia.freetoursegovia.services.ClientService;
import com.freetoursegovia.freetoursegovia.services.UserService;
import com.freetoursegovia.freetoursegovia.services.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


/**
 * API controller to communicate with Database and send information to client
 */

@RestController
@RequestMapping(path = "/api")
public class BookingController {

    @Autowired
    AvailabilityService availabilityService;
    @Autowired
    TourService tourService;
    @Autowired
    ClientService clientService;

    @Autowired
    UserService userService;

    private final Logger log = LoggerFactory.getLogger(HomeController.class);


    // ############################# //
    // ##### User management ###### //
    // ############################# //

    @GetMapping("/test")
    List<User> findAllTests() {
        return userService.findAllUsers();
    }

    @PostMapping("/test")
    User saveTest(@RequestBody User newTest) {

        return userService.saveUser(newTest);
    }

    @GetMapping("/test/{id}")
    User one(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PutMapping("/test/{id}")
    User replaceTest(@RequestBody User newTest, @PathVariable int id) {

        User t = userService.findUserById(id);
        t.setName(newTest.getName());
        userService.saveUser(t);
        return t;

    }

    @DeleteMapping("/test/{id}")
    void deleteTest(@PathVariable int id) {
        userService.deleteUser(id);
    }


    // ####################### //
    // Client related requests //
    // ####################### //

    // Get all clients
    @GetMapping("/client")
    public List<Client> getAllClients() {

        return clientService.findAll();
    }

    // Get client by id
    @GetMapping("/client/{id}")
    public Client getClient(@PathVariable Integer id) {
        return clientService
                .findById(id)
                .orElseThrow();
    }

    // Add new client
    @PostMapping("/client/new")
    public ResponseEntity<Client> saveClient(Client client) {
        Client c = clientService.saveClient(client);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @PostMapping("/client/new-tutorial")
    public Client newClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    // Edit existing client
    @PostMapping("/client/edit")
    public ResponseEntity<Client> editClient(Client client) {
        System.out.println("#############################################FLAGGGGGG");
        Client c = clientService.saveClient(client);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    // Delete client by id
    @GetMapping("/client/delete/{id}")
    public Client deleteClient(@PathVariable Integer id) {
        Client c = clientService.findById(id).orElseThrow();
        clientService.deleteClient(id);
        return c;
    }


    // ###################### //
    // Tour related requests //
    // ###################### //
    @GetMapping("/tour")
    public List<Tour> getAllTours() {
        return tourService.findAll();
    }

    @GetMapping("/tour/{id}")
    public Tour getTour(@PathVariable Integer id) {
        return tourService
                .findById(id)
                .orElseThrow();
    }

    @PostMapping("/tour")
    public ResponseEntity<Tour> editTour(Tour tour) {
        Tour t = tourService.saveTour(tour);
        return new ResponseEntity<>(t, HttpStatus.CREATED);
    }


    /**
     * Map incoming request directly to /bookingform without information attached to them
     *
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
     *
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
        for (int i = 0; i < allAvail.size(); i++) {

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
     * This method receives the information from the booking form already filled
     * It will cehck if the booking is possible and if so, send a confirmation email
     * and redirect to the confirmation page.
     * Otherwise it will send the client to the error page.
     *
     * @param availability
     * @param client
     * @return
     */
    @PostMapping("/bookingRequest")
    public String bookingRequest(Availability availability, Client client) {

        log.info("----------Booking request received");

        // Get the availability
        Availability a = availabilityService.findByVisitDate(availability.getVisitDate());

        List<Availability> allAvail = availabilityService.findAll();

        for (int i = 0; i < allAvail.size(); i++) {

            if (allAvail.get(i).getVisitDate() == a.getVisitDate()) {

                availability.setAvailabilityId(a.getAvailabilityId());
                availability.setTour(a.getTour());
                availability.setVisitDate(a.getVisitDate());
                availability.setAvailability(a.getAvailability());

            }
        }

        if (availability.getAvailability() - client.getAdults() - client.getChildren() >= 0) {

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
