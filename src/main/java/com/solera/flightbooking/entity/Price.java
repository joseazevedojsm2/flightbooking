package com.solera.flightbooking.entity;

import jakarta.persistence.*;

@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_flight",nullable = false)
    private Flight flight;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_groupsAge",nullable = false)
    private GroupsAge groupsAge;

    private Double price;
    private Double additionalCost;

    public Price() {
    }

    public Price(int id, Double price, Double additionalCost) {
        this.id = id;
        this.price = price;
        this.additionalCost = additionalCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(Double additionalCost) {
        this.additionalCost = additionalCost;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public GroupsAge getGroupsAge() {
        return groupsAge;
    }

    public void setGroupsAge(GroupsAge groupsAge) {
        this.groupsAge = groupsAge;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", price=" + price +
                ", additionalCost=" + additionalCost +
                '}';
    }
}
