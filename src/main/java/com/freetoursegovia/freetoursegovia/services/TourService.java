package com.freetoursegovia.freetoursegovia.services;

import com.freetoursegovia.freetoursegovia.model.Tour;
import com.freetoursegovia.freetoursegovia.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to work with JPA Library and to be able to inject the dependency in other classes
 *
 */
@Service
public class TourService {

    @Autowired
    TourRepository tourRepository;

    public List<Tour> findAll(){
        return tourRepository.findAll();
    }

    public Optional<Tour> findById(Integer id){
        return tourRepository.findById(id);
    }

    public Tour saveTour(Tour tour){return tourRepository.save(tour);}

    public void deleteTour(Integer id){ tourRepository.deleteById(id);}

    public Tour editTour(Tour newTour){

        Integer id = newTour.getTourId();

        if(tourRepository.findById(id).isPresent()) {

            Tour tour = new Tour();
            tour.setTourId(newTour.getTourId());
            tour.setName(newTour.getName());
            tour.setLanguage(newTour.getLanguage());
            tour.setDuration(newTour.getDuration());
            tour.setPrice(newTour.getPrice());
            tour.setStartTime(newTour.getStartTime());
            tour.setStartPoint(newTour.getStartPoint());
            tour.setEndPoint(newTour.getEndPoint());
            tour.setDescription(newTour.getDescription());
            tourRepository.save(tour);
            return tour;
        }

        return newTour;

    }
}
