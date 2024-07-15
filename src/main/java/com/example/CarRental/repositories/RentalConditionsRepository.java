package com.example.CarRental.repositories;

import com.example.CarRental.domain.RentalConditions;
import org.springframework.stereotype.Repository;


@Repository
public interface RentalConditionsRepository{
    RentalConditions findByCarId(Integer id);

    void create(RentalConditions rentalConditions);

    RentalConditions findById(Class<RentalConditions> rentalConditionsClass, int id);
}
