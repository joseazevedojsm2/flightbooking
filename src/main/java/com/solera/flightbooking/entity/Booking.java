package com.solera.flightbooking.entity;

import jakarta.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_passanger", nullable = false)
    private Passanger passanger;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_price", nullable = false)
    private Price price;

    public Booking() {
    }

    public Booking( Passanger passanger, Price price) {
        this.passanger = passanger;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passanger getPassanger() {
        return passanger;
    }

    public void setPassanger(Passanger passanger) {
        this.passanger = passanger;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", passanger=" + passanger +
                ", price=" + price +
                '}';
    }
}
