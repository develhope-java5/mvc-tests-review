package com.develhope.java5.mvctestsreview.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Plant {
    @GeneratedValue
    @Id
    private Long id; 
    private String name;
    private int quantity;

    public Plant() { }

    public Plant(Long id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
}
