package com.freetoursegovia.freetoursegovia.controller;

import com.freetoursegovia.freetoursegovia.Utils.Mail;
import com.freetoursegovia.freetoursegovia.Utils.Utils;
import com.freetoursegovia.freetoursegovia.model.*;
import com.freetoursegovia.freetoursegovia.services.*;
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
    @Autowired
    MessageService messageService;
    @Autowired
    ProductService productService;
    @Autowired
    BookingService bookingService;


    private final Logger log = LoggerFactory.getLogger(HomeController.class);


    // ############################# //
    // #      User management     ## //
    // ############################# //

    @GetMapping("/user")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User newTest) {
        return userService.saveUser(newTest);
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PutMapping("/user/{id}")
    public User replaceUser(@RequestBody User newTest, @PathVariable int id) {

        User t = userService.findUserById(id);
        t.setName(newTest.getName());
        userService.saveUser(t);
        return t;
    }

    @DeleteMapping("/user/{id}")
    public User deleteUser(@PathVariable int id) {

        User u = userService.findUserById(id);
        userService.deleteUser(id);
        return u;
    }


    // ####################### //
    //    Message management   //
    // ####################### //

    @GetMapping("/message")
    public List<Message> findAllMessages() {
        return messageService.findAllMessages();
    }

    @PostMapping("/message")
    public Message saveMessage(@RequestBody Message newMessage) {

        int id = newMessage.getUser().getId();
        newMessage.setUser(userService.findUserById(id));
        return messageService.saveMessage(newMessage);
    }

    @GetMapping("/message/{id}")
    public Message findMessageById(@PathVariable int id) {
        return messageService.findMessageById(id);
    }

    @GetMapping("/message/user_id/{id}")
    public List<Message> findMessageByUserId(@PathVariable int id) {
        return messageService.findMessageByUserId(id);
    }

    @PutMapping("/message/{id}")
    public Message replaceMessage(@RequestBody Message newMessage, @PathVariable int id) {

        Message m = messageService.findMessageById(id);
        m.setBody(newMessage.getBody());
        m.setDate(newMessage.getDate());
        m.setBody(newMessage.getBody());
        m.setUser(userService.findUserById(newMessage.getUser().getId())); // Search user in userService
        messageService.saveMessage(m);
        return m;
    }

    @DeleteMapping("/message/{id}")
    public Message deleteMessage(@PathVariable int id) {
        Message m = messageService.findMessageById(id);
        messageService.deleteMessage(id);
        return m;
    }


    // ###################### //
    //   Product management   //
    // ###################### //

    @GetMapping("/product")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping("/product")
    public Product saveProduct(@RequestBody Product newProduct) {
        return productService.saveProduct(newProduct);
    }

    @GetMapping("/product/{id}")
    public Product findProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @PutMapping("/product/{id}")
    public Product replaceProduct(@RequestBody Product newProduct, @PathVariable int id) {

        Product p = productService.findProductById(id);
        p.setName(newProduct.getName());
        p.setPrice(newProduct.getPrice());
        p.setCapacity(newProduct.getCapacity());
        productService.saveProduct(p);
        return p;
    }

    @DeleteMapping("/product/{id}")
    public Product deleteProduct(@PathVariable int id) {
        Product p = productService.findProductById(id);
        productService.deleteProduct(id);
        return p;
    }

    // ############################# //
    // #    Booking management    ## //
    // ############################# //

    @GetMapping("/booking")
    public List<Booking> findAllBookings() {
        return bookingService.findAllBookings();
    }

    @GetMapping("/booking/{id}")
    public Booking findBookingById(@PathVariable int id) {
        return bookingService.findBookingById(id);
    }

    @GetMapping("/booking/user/{id}")
    public List<Booking> findBookingByUser(@PathVariable int id) {

        User u = userService.findUserById(id);
        return bookingService.findBookingByUser(u);

    }

    @GetMapping("/booking/product/{id}")
    public List<Booking> findBookingByProduct(@PathVariable int id) {

        Product p = productService.findProductById(id);
        return bookingService.findBookingByProduct(p);

    }

    @GetMapping("/booking/date/{d}")
    public List<Booking> findBookingByDate(@PathVariable String d) {

        LocalDate date = Utils.stringToLocalDate(d);
        return bookingService.findBookingByDate(date);
    }

    @PostMapping("/booking")
    public Booking saveBooking(@RequestBody Booking newBooking) {
        return bookingService.saveBooking(newBooking);
    }


    @PutMapping("/booking/{id}")
    public Booking replaceBooking(@RequestBody Booking newBooking, @PathVariable int id) {

        Booking b = bookingService.findBookingById(id);
        b.setAdults(newBooking.getAdults());
        b.setChildren(newBooking.getChildren());
        b.setComments(newBooking.getComments());
        b.setUser(userService.findUserById(newBooking.getUser().getId())); // Search user in userService
        bookingService.saveBooking(b);
        return b;
    }

    @DeleteMapping("/booking/{id}")
    public Booking deleteBooking(@PathVariable int id) {
        Booking b = bookingService.findBookingById(id);
        bookingService.deleteBooking(id);
        return b;
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
