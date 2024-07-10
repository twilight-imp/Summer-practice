package com.example.CarRental.entities;

import jakarta.persistence.*;

@MappedSuperclass
//@Inheritance(strategy= InheritanceType.JOINED)
public abstract class EntityId{
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
}
