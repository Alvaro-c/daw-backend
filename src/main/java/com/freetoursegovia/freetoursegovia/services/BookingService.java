package com.freetoursegovia.freetoursegovia.services;

import com.freetoursegovia.freetoursegovia.model.Booking;
import com.freetoursegovia.freetoursegovia.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {


    @Autowired
    BookingRepository bookingRepository;

    public List<Booking> findAllBookings() { return bookingRepository.findAll(); }

    public Booking saveBooking(Booking booking) { return bookingRepository.save(booking); }

    public Booking findBookingById(int id) { return bookingRepository.findById(id).orElseThrow(); }

    public void deleteBooking(int id) { bookingRepository.deleteById(id); }

}
