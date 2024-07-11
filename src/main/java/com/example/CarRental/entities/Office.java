package com.example.CarRental.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "offices")
public class Office extends BaseEntity {
    private String name;
    private String city;
    private String district;
    private String street;
    private int numberBuilding;
    private int numberHome;
    private String phone;
    private String email;

    private Set<Car> car;

    public Office(String name, String city, String district, String street, int numberBuilding, int numberHome, String phone, String email) {
        this.name = name;
        this.city = city;
        this.district = district;
        this.street = street;
        this.numberBuilding = numberBuilding;
        this.numberHome = numberHome;
        this.phone = phone;
        this.email = email;
    }

    protected Office() {
    }

    @Column(length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(length = 50, nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @Column(length = 50, nullable = false)
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
    @Column(length = 50, nullable = false)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(nullable = false)
    public int getNumberBuilding() {
        return numberBuilding;
    }

    public void setNumberBuilding(int numberBuilding) {
        this.numberBuilding = numberBuilding;
    }
    @Column(nullable = false)
    public int getNumberHome() {
        return numberHome;
    }

    public void setNumberHome(int numberHome) {
        this.numberHome = numberHome;
    }
    @Column(length = 15, nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(length = 50, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "car", targetEntity = Request.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Car> getCar() {
        return car;
    }

    public void setCar(Set<Car> car) {
        this.car = car;
    }
}
