package com.freetoursegovia.freetoursegovia.repository;

import com.freetoursegovia.freetoursegovia.model.Booking;
import com.freetoursegovia.freetoursegovia.model.Product;
import com.freetoursegovia.freetoursegovia.model.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    public List<Booking> findAll();

    public List<Booking> findBookingByUser(User user);

    public List<Booking> findBookingByProduct(Product product);

    public List<Booking> findBookingByDate(LocalDate date);

    public List<Booking> findBookingByDateAndProduct(LocalDate date, Product product);

}
