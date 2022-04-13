package com.freetoursegovia.freetoursegovia.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;
/**
 * Entity for JPA library implementation
 * This entity binds with table "tour" from the DB
 */
@Entity
@Table(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id")
    private Integer tourId;

    @Column(name = "name")
    private String name;

    @Column(name = "language")
    private String language;

    @Column(name = "duration")
    private Float duration;

    @Column(name = "price")
    private Float price;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "startPoint")
    private String startPoint;

    @Column(name = "endPoint")
    private String endPoint;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "tour")
    Set<Client> clientList;

    @OneToMany(mappedBy = "tour")
    @JsonManagedReference
    Set<Availability> availability;


    public Tour() {
    }

    public Tour(Integer tourId, String name, String language, Float duration, Float price, String startTime, String startPoint, String endPoint, String description, Set<Client> clientList, Set<Availability> availability) {
        this.tourId = tourId;
        this.name = name;
        this.language = language;
        this.duration = duration;
        this.price = price;
        this.startTime = startTime;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.description = description;
        this.clientList = clientList;
        this.availability = availability;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Client> getClientList() {
        return clientList;
    }

    public void setClientList(Set<Client> clientList) {
        this.clientList = clientList;
    }

    public Set<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(Set<Availability> availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "tourId=" + tourId +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                ", startTime='" + startTime + '\'' +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", description='" + description + '\'' +
                ", clientList=" + clientList +
                ", availability=" + availability +
                '}';
    }
}
