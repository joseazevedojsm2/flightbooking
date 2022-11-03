package com.solera.flightbooking.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_origin",nullable = false)
    private Place origin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_destination",nullable = false)
    private Place destination;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Flight> flight;

    public Route() {
    }

    public Route(Place origin, Place destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Route(int id, Place origin, Place destination) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;
    }

    public Place getOrigin() {
        return origin;
    }

    public void setOrigin(Place origin) {
        this.origin = origin;
    }

    public int getId() {
        return id;
    }

    public Boolean isRoute(String originName,String destinationName){
        return getOrigin().getName().equalsIgnoreCase(originName) && getDestination().getName().equalsIgnoreCase(destinationName);
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                '}';
    }
}
