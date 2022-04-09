package com.freetoursegovia.freetoursegovia.repository;

import com.freetoursegovia.freetoursegovia.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Interface to extend Jpa Library
 */
@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {


    Availability findByVisitDate(LocalDate visitDate);
}
