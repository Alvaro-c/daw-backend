package com.freetoursegovia.freetoursegovia.controller;

import com.freetoursegovia.freetoursegovia.Utils.Mail;
import com.freetoursegovia.freetoursegovia.Utils.Utils;
import com.freetoursegovia.freetoursegovia.model.*;
import com.freetoursegovia.freetoursegovia.services.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    UserService userService;
    @Autowired
    MessageService messageService;
    @Autowired
    ProductService productService;
    @Autowired
    BookingService bookingService;

    // ############################# //
    // #      User management     ## //
    // ############################# //

    @GetMapping("/user")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User newUser) {
        return userService.saveUser(newUser);
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.findUserById(id);
    }

    @PutMapping("/user/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable int id) {

        User t = userService.findUserById(id);
        t.setName(newUser.getName());
        t.setSurname(newUser.getSurname());
        t.setPhone(newUser.getPhone());
        t.setEmail(newUser.getEmail());
        t.setPassword(newUser.getPassword());
        t.setRol(newUser.getRol());
        t.setPhoto(newUser.getPhoto());
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

        System.out.println(newProduct.toString());
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
        p.setDescription(newProduct.getDescription());
        p.setPrice(newProduct.getPrice());
        p.setCapacity(newProduct.getCapacity());
        p.setImage(newProduct.getImage());
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
        newBooking.setUser(userService.findUserById(newBooking.getUser().getId()));
        Mail m = new Mail();
        m.sendEmailToUser(newBooking);
        return bookingService.saveBooking(newBooking);
    }


    @PutMapping("/booking/{id}")
    public Booking replaceBooking(@RequestBody Booking newBooking, @PathVariable int id) {

        Booking b = bookingService.findBookingById(id);
        b.setAdults(newBooking.getAdults());
        b.setChildren(newBooking.getChildren());
        b.setComments(newBooking.getComments());
        b.setDate(newBooking.getDate());
        b.setUser(userService.findUserById(newBooking.getUser().getId()));
        b.setProduct(productService.findProductById(newBooking.getProduct().getId()));// Search user in userService
        bookingService.saveBooking(b);
        return b;
    }

    @DeleteMapping("/booking/{id}")
    public Booking deleteBooking(@PathVariable int id) {
        Booking b = bookingService.findBookingById(id);
        bookingService.deleteBooking(id);
        return b;
    }

    @PostMapping("/availability")
    public int findAvailabilityByDate(@RequestBody Booking newBooking) {

        List<Booking> bookings = bookingService.findBookingByDateAndProduct(newBooking.getDate(), newBooking.getProduct());
        int people = 0;

        for (Booking booking : bookings) {
            System.out.println("PEOPLE:" + people);
            people = people + booking.getAdults() + booking.getChildren();
        }
        newBooking.setProduct(productService.findProductById(newBooking.getProduct().getId()));

        Integer capacity = newBooking.getProduct().getCapacity();

        return capacity - people;
    }

    @PostMapping("/contact-form")
    public Form contactForm(@RequestBody Form form) {

        Mail m = new Mail();
        boolean result = m.sendBookingToMe(form);

        if (result) {
            return form;
        } else {
            form.setName("Ha ocurrido un error");
            return form;
        }

    }

}
