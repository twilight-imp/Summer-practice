package com.example.CarRental.repositories;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.CarStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarRepository {

    List<Car> findByCharacteristicsAndRegionAndCost(String city, String district, String brand, String model, String color, int numberSeats, int costPerDay,  CarStatus carStatus);

    Car findByDateRequest(int idCar, LocalDateTime dateStart, LocalDateTime dateEnd);



    void create(Car car);
    Car findById(Class<Car> carClass, int id);
    Car update(Car car);
    void updateStatus(int id, CarStatus carStatus);


}
