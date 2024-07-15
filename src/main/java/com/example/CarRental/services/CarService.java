package com.example.CarRental.services;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.CarStatus;

import java.time.LocalDateTime;

public interface CarService {
    void addCar(Car car);
    Car getCarById(int id);
    Car findCarByCharacteristicsAndLocationAndDate(String district, String brand, String model, String color, int numberSeats, int budget, LocalDateTime startDate, int numDays);


}

