package com.example.CarRental.repositories;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.CarStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository {

    List<Car> findByCharacteristicsAndRegionAndCost(String city, String district, int numberSeats, int costPerDay,  CarStatus carStatus);

    Car findByDateRequest(int idCar, LocalDateTime dateStart, LocalDateTime dateEnd);

    Car create(Car car);
    Optional<Car> findById(Class<Car> carClass, int id);

    List<Car> getAll(Class<Car> carClass);


}
