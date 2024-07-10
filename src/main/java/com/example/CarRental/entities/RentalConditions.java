package com.example.CarRental.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rent_conditions")
public class RentalConditions extends EntityId {
    private int minAge;

    private int minDrivingExperience;

    private int minRentDays;

    private int includeMileage;

    private Car car;

    @Column
    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }
    @Column
    public int getMinDrivingExperience() {
        return minDrivingExperience;
    }

    public void setMinDrivingExperience(int minDrivingExperience) {
        this.minDrivingExperience = minDrivingExperience;
    }

    @Column
    public int getMinRentDays() {
        return minRentDays;
    }

    public void setMinRentDays(int minRentDays) {
        this.minRentDays = minRentDays;
    }

    @Column
    public int getIncludeMileage() {
        return includeMileage;
    }

    public void setIncludeMileage(int includeMileage) {
        this.includeMileage = includeMileage;
    }

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public RentalConditions() {
    }
}
