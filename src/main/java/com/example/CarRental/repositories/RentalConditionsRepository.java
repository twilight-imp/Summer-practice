package com.example.CarRental.repositories;

import com.example.CarRental.domain.RentalConditions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalConditionsRepository{
    RentalConditions findByCarId(Integer id);
}
