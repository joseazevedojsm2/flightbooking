package com.solera.flightbooking.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Passanger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fName;
    private String lName;
    private String nacionality;
    private String identification;
    private int groupAge;

    @OneToMany(mappedBy = "passanger",cascade = CascadeType.ALL)
    private List<Booking> booking;

    public Passanger() {
    }

    public Passanger(String fName, String lName) {
        this.fName = fName;
        this.lName = lName;
    }

    public Passanger(int id, String fName, String lName, String nacionality, String identification, int groupAge) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.nacionality = nacionality;
        this.identification = identification;
        this.groupAge = groupAge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getGroupAge() {
        return groupAge;
    }

    public void setGroupAge(int groupAge) {
        this.groupAge = groupAge;
    }

    @Override
    public String toString() {
        return "Passanger{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", nacionality='" + nacionality + '\'' +
                ", identification='" + identification + '\'' +
                ", groupAge=" + groupAge +
                '}';
    }
}
