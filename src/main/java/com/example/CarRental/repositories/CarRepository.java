package com.example.CarRental.repositories;

import com.example.CarRental.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query(value = "select c from Car c " +
            "join c.office o where o.district = :district")
    List<Car> findByDistrict(@Param(value = "district") String district);

    @Query(value = "select c from Car c where c.costPerDay < :cost")
    List<Car> findByCost(@Param(value = "cost") Integer cost);
}
