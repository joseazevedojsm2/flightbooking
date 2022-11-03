package com.solera.flightbooking.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class GroupsAge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String groupAge;

    @OneToMany(mappedBy = "groupsAge",cascade = CascadeType.ALL)
    private List<Price> price;

    public GroupsAge() {
    }

    public GroupsAge(int id, String group) {
        this.id = id;
        this.groupAge = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return groupAge;
    }

    public void setGroup(String group) {
        this.groupAge = group;
    }

    @Override
    public String toString() {
        return "GroupsAge{" +
                "id=" + id +
                ", group='" + groupAge + '\'' +
                '}';
    }
}
