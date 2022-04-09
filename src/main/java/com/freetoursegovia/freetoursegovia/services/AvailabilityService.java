package com.freetoursegovia.freetoursegovia.services;

import com.freetoursegovia.freetoursegovia.model.Availability;
import com.freetoursegovia.freetoursegovia.repository.AvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service to work with JPA Library and to be able to inject the dependency in other classes
 *
 */
@Service
public class AvailabilityService {

    @Autowired
    AvailabilityRepository availabilityRepository;

    public List<Availability> findAll() {
        return availabilityRepository.findAll();
    }

    public Optional<Availability> findById(Integer id) {
        return availabilityRepository.findById(id);
    }

    public Availability saveAvailability (Availability availability) {
        return availabilityRepository.save(availability);
    }

    public Availability editAvailability (Availability newAva) {

        Integer id = newAva.getAvailabilityId();
        if(availabilityRepository.findById(id).isPresent()){

            Availability a = new Availability();
            a.setAvailabilityId(newAva.getAvailabilityId());
            a.setTour(newAva.getTour());
            a.setVisitDate(newAva.getVisitDate());
            a.setAvailability(newAva.getAvailability());
            availabilityRepository.save(a);
            return a;
        }
        return newAva;
    }

    public Availability findByVisitDate(LocalDate visitDate) {

        Availability a = availabilityRepository.findByVisitDate(visitDate);
        List<Availability> allAvail = availabilityRepository.findAll();


        for (int i = 0; i < allAvail.size(); i ++) {

            if (allAvail.get(i).getVisitDate() == visitDate) {

                a.setAvailabilityId(allAvail.get(i).getAvailabilityId());
                a.setTour(allAvail.get(i).getTour());
                a.setVisitDate(allAvail.get(i).getVisitDate());
                a.setAvailability(allAvail.get(i).getAvailability());

                return a;

            }
        }


        return availabilityRepository.findByVisitDate(visitDate);
    }
}
