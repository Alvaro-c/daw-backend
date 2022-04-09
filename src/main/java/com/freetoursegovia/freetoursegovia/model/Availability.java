package com.freetoursegovia.freetoursegovia.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * Entity for JPA library implementation
 * This entity binds with table "availability" from the DB
 */
@Entity
@Table(name="availability")
public class Availability {

    @Id
    int availabilityId;

    @ManyToOne
    @JoinColumn(name="tour_id")
    Tour tour;

    @Column(name="visit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;

    @Column(name = "availability")
    private Integer availability;


    public Availability() {
    }

    public Availability(int availabilityId, Tour tour, LocalDate visitDate, Integer availability) {
        this.availabilityId = availabilityId;
        this.tour = tour;
        this.visitDate = visitDate;
        this.availability = availability;
    }

    public int getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(int availabilityId) {
        this.availabilityId = availabilityId;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "availabilityId=" + availabilityId +
                ", tourId=" + tour.getTourId() +
                ", visitDate=" + visitDate +
                ", availability=" + availability +
                '}';
    }
}
