package com.solera.flightbooking.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "origin",cascade = CascadeType.ALL)
    private List<Route> routeAsOrigin;

    @OneToMany(mappedBy = "destination",cascade = CascadeType.ALL)
    private List<Route> routeAsDestination;

    public Place() {
    }

    public Place(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
