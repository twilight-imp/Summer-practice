package com.example.CarRental.controllers;

import com.example.CarRental.dtos.CarDto;
import com.example.CarRental.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    Iterable<CarDto> getAll() {
        return carService.findAll();
    }

    @GetMapping("/cars/{id}")
    CarDto getOne(@PathVariable Integer id){
        return carService.findCarById(id);
    }

    @PostMapping("/cars")
    public CarDto create(@RequestBody CarDto carDto) {
        return carService.addCar(carDto);
    }

    @PostMapping ("/cars/find")
    Iterable<CarDto> findByCharacteristics(@RequestParam String city, @RequestParam String district,
                                           @RequestParam Integer numberSeats,
                                           @RequestParam Integer budget, @RequestParam LocalDateTime startDate,
                                           @RequestParam Integer numDays){
        return carService.findCarByCharacteristicsAndLocationAndDate(city,district,numberSeats,budget, startDate, numDays);
    }

}
