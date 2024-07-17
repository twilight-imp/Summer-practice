package com.example.CarRental.dtos;

import com.example.CarRental.domain.Car;

public class RentalConditionsDto {
    private int id;
    private int minAge;

    private int minDrivingExperience;

    private int minRentDays;

    private int includeMileage;

    private int carId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMinDrivingExperience() {
        return minDrivingExperience;
    }

    public void setMinDrivingExperience(int minDrivingExperience) {
        this.minDrivingExperience = minDrivingExperience;
    }

    public int getMinRentDays() {
        return minRentDays;
    }

    public void setMinRentDays(int minRentDays) {
        this.minRentDays = minRentDays;
    }

    public int getIncludeMileage() {
        return includeMileage;
    }

    public void setIncludeMileage(int includeMileage) {
        this.includeMileage = includeMileage;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
