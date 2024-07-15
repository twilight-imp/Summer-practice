package com.example.CarRental.services.impl;

import com.example.CarRental.domain.RentalConditions;
import com.example.CarRental.repositories.RentalConditionsRepository;
import com.example.CarRental.services.RentalConditionsService;
import org.springframework.beans.factory.annotation.Autowired;

public class RentalConditionsServiceImpl implements RentalConditionsService {
    @Autowired
    private RentalConditionsRepository rentalConditionsRepository;
    @Override
    public void addRentalCondition(RentalConditions rentalConditions) {
        rentalConditionsRepository.create(rentalConditions);
    }

    @Override
    public RentalConditions getRentalConditionById(int id) {
        return rentalConditionsRepository.findById(RentalConditions.class, id);
    }
}
