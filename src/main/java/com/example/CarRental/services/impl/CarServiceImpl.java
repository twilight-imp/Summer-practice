package com.example.CarRental.services.impl;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.CarStatus;
import com.example.CarRental.repositories.CarRepository;
import com.example.CarRental.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void addCar(Car car) {
        carRepository.create(car);
    }

    @Override
    public Car getCarById(int id) {
        return carRepository.findById(Car.class, id);
    }

    @Override
    public Car findCarByCharacteristicsAndLocationAndDate(String district, String brand, String model, String color, int numberSeats, int budget, LocalDateTime startDate, int numDays) {
        int costPerDay = budget / numDays;
        CarStatus available = CarStatus.AVAILABLE;
        List<Car> carList = carRepository.findByCharacteristicsAndRegionAndCost(district,brand,model,color,numberSeats,costPerDay,available);
        LocalDateTime endDate = startDate.plusDays(numDays);
        for (Car car: carList) {
            if(carRepository.findByRequest(car.getId(), startDate, endDate) == null) return car;
        }
        return null;
    }

}
