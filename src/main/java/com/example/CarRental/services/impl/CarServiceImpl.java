package com.example.CarRental.services.impl;

import com.example.CarRental.exceptions.CarNotFoundException;
import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.CarStatus;
import com.example.CarRental.dtos.CarDto;
import com.example.CarRental.repositories.CarRepository;
import com.example.CarRental.services.CarService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService  {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public CarDto addCar(CarDto carDto){
        Car car = modelMapper.map(carDto,Car.class);
        return modelMapper.map(carRepository.create(car), CarDto.class);
    }

    @Override
    @Transactional
    public CarDto findCarById(int id) {
        return modelMapper.map(carRepository.findById(Car.class, id).
                orElseThrow(() -> new IllegalArgumentException("Автомобиль не найден")),CarDto.class);
    }

    //поиск автомобиля на нужную дату с заданными параметрами в нужном районе
    @Override
    @Transactional
    public List<CarDto> findCarByCharacteristicsAndLocationAndDate(String city, String district, int numberSeats, int budget, LocalDateTime startDate, int numDays) {
        int costPerDay = budget / numDays;
        CarStatus available = CarStatus.AVAILABLE;
        List<Car> carList = carRepository.findByCharacteristicsAndRegionAndCost(city, district,numberSeats,costPerDay,available);
        List<Car> carFindList = new ArrayList<>();
        LocalDateTime endDate = startDate.plusDays(numDays);
        System.out.println(endDate);
        for (Car car1: carList) {
            if(carRepository.findByDateRequest(car1.getId(), startDate, endDate) == null) carFindList.add(car1);
        }
        if(carFindList.isEmpty()){
            throw new CarNotFoundException("Нужного автомобиля не найдено");
        }
        return  carFindList.stream().map(car -> modelMapper.map(car, CarDto.class)).toList();
    }

    @Override
    @Transactional
    public List<CarDto> findAll() {
        return carRepository.getAll(Car.class).stream().map(car -> modelMapper.map(car, CarDto.class)).toList();
    }



}
