package com.example.CarRental.repositories;

import com.example.CarRental.entities.RentalConditions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalConditionsRepository extends JpaRepository <RentalConditions, Integer> {
}
