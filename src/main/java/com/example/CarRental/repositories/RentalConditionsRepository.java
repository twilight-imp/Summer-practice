package com.example.CarRental.repositories;

import com.example.CarRental.entities.Car;
import com.example.CarRental.entities.RentalConditions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalConditionsRepository extends JpaRepository <RentalConditions, Integer> {
    @Query(value = "select rc from RentalConditions rc " +
            "join rc.car c where c.id = :id")
    List<Car> findByCar(@Param(value = "id") Integer id);
}
