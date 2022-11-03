package com.solera.flightbooking.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Boolean allowLuggage;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Flight> flight;

    public Company() {
    }

    public Company(String company, Boolean allowLuggage) {
        this.name = company;

        this.allowLuggage = allowLuggage;
    }

    public Company(int id, String company, Boolean allowLuggage) {
        this.id = id;
        this.name = company;

        this.allowLuggage = allowLuggage;
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

    public Boolean getAllowLuggage() {
        return allowLuggage;
    }

    public void setAllowLuggage(Boolean allowLuggage) {
        this.allowLuggage = allowLuggage;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", company='" + name + '\'' +
                ", allowLuggage=" + allowLuggage +
                '}';
    }
}
