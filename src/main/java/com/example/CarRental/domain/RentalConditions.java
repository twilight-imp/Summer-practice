package com.example.CarRental.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "rent_conditions")
public class RentalConditions extends BaseEntity {
    private int minAge;

    private int minDrivingExperience;

    private int minRentDays;

    private int includeMileage;

    private Car car;

    public RentalConditions(int minAge, int minDrivingExperience, int minRentDays, int includeMileage, Car car) {
        this.minAge = minAge;
        this.minDrivingExperience = minDrivingExperience;
        this.minRentDays = minRentDays;
        this.includeMileage = includeMileage;
        this.car = car;
    }

    protected RentalConditions() {
    }

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

}
