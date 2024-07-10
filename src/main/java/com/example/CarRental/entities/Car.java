package com.example.CarRental.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car extends EntityId {
    private String brand;
    private String model;
    private String category;
    private int numberSeats;
    private String enginType;
    private String color;
    private int yearRelease;
    private String transmission;
    private int costPerDay;
    private String status;
    private Office office;
    private RentalConditions rentalConditions;

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
    @Column(length = 50, nullable = false)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @Column(name = "number_of_seats", nullable = false)
    public int getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }
    @Column(length = 20 ,name = "type_of_engin", nullable = false)
    public String getEnginType() {
        return enginType;
    }

    public void setEnginType(String enginType) {
        this.enginType = enginType;
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
    @Column(length = 20, nullable = false)
    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @Column(name = "cost_per_day", nullable = false)
    public int getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(int costPerDay) {
        this.costPerDay = costPerDay;
    }
    @Column(length = 20, nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
