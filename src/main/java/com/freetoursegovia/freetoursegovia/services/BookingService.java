package com.freetoursegovia.freetoursegovia.services;

import com.freetoursegovia.freetoursegovia.model.Booking;
import com.freetoursegovia.freetoursegovia.model.Product;
import com.freetoursegovia.freetoursegovia.model.User;
import com.freetoursegovia.freetoursegovia.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {


    @Autowired
    BookingRepository bookingRepository;

    public List<Booking> findAllBookings() { return bookingRepository.findAll(); }

    public Booking saveBooking(Booking booking) { return bookingRepository.save(booking); }

    public Booking findBookingById(int id) { return bookingRepository.findById(id).orElseThrow(); }

    public List<Booking> findBookingByUser(User user) { return bookingRepository.findBookingByUser(user); }

    public List<Booking> findBookingByProduct(Product product) { return bookingRepository.findBookingByProduct(product); }

    public List<Booking> findBookingByDate(LocalDate date) { return bookingRepository.findBookingByDate(date); }

    public List<Booking> findBookingByDateAndProduct(LocalDate d, Product p) { return bookingRepository.findBookingByDateAndProduct(d, p);}

    public void deleteBooking(int id) { bookingRepository.deleteById(id); }

}
