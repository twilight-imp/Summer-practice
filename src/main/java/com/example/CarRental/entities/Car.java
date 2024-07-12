package com.example.CarRental.entities;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    private String brand;
    private String model;
    private CarCategory carCategory;
    private int numberSeats;
    private String color;
    private int yearRelease;
    private int costPerDay;

    private CarStatus carStatus;
    private Office office;
    private RentalConditions rentalConditions;

    private Set<Request> request;

    public Car(String brand, String model, CarCategory carCategory, int numberSeats, String color, int yearRelease, int costPerDay, CarStatus carStatus, Office office, RentalConditions rentalConditions) {
        this.brand = brand;
        this.model = model;
        this.carCategory = carCategory;
        this.numberSeats = numberSeats;
        this.color = color;
        this.yearRelease = yearRelease;
        this.costPerDay = costPerDay;
        this.carStatus = carStatus;
        this.office = office;
        this.rentalConditions = rentalConditions;
    }

    protected Car() {
    }
    @Column(length = 20, nullable = false)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    @Column(length = 20, nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    public CarCategory getCarCategory() {
        return carCategory;
    }
    public void setCarCategory(CarCategory carCategory) {
        this.carCategory = carCategory;
    }
    @Column(name = "number_of_seats", nullable = false)
    public int getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }

    @Column(length = 20, nullable = false)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    @Column(name = "year_of_release", nullable = false)
    public int getYearRelease() {
        return yearRelease;
    }

    public void setYearRelease(int yearRelease) {
        this.yearRelease = yearRelease;
    }


    @Column(name = "cost_per_day", nullable = false)
    public int getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(int costPerDay) {
        this.costPerDay = costPerDay;
    }
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public CarStatus getCarStatus() {
        return carStatus;
    }
    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "office_id", referencedColumnName = "id")
    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
    @OneToOne(mappedBy = "car", targetEntity = RentalConditions.class)
    public RentalConditions getRentalConditions() {
        return rentalConditions;
    }

    public void setRentalConditions(RentalConditions rentalConditions) {
        this.rentalConditions = rentalConditions;
    }

    @OneToMany(mappedBy = "car", targetEntity = Request.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Request> getRequest() {
        return request;
    }

    public void setRequest(Set<Request> request) {
        this.request = request;
    }
}
