package com.solera.flightbooking.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_route",nullable = false)
    private Route route;

    private LocalDate arrivalDate,departureDate;
    private LocalTime arrivalTime,departureTime;
    private Boolean allowLuggage;


    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_company", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL)
    private List<Price> prices;

    public Flight() {
    }

    public Flight(int id, LocalDate arrivalDate, LocalDate departureDate, LocalTime arrivalTime, LocalTime departureTime) {
        this.id = id;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }



    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean getAllowLuggage() {
        return allowLuggage;
    }

    public void setAllowLuggage(Boolean allowLuggage) {
        this.allowLuggage = allowLuggage;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
