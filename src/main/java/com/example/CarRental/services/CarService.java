package com.example.CarRental.services;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.CarStatus;
import com.example.CarRental.dtos.CarDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CarService {
    CarDto addCar(CarDto car);
    CarDto findCarById(int id);
    List<CarDto> findCarByCharacteristicsAndLocationAndDate(String city, String district, int numberSeats, int budget, LocalDateTime startDate, int numDays);

    List<CarDto> findAll();


}

