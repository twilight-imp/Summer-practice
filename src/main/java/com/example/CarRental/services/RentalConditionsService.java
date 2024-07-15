package com.example.CarRental.services;


import com.example.CarRental.domain.RentalConditions;

public interface RentalConditionsService {
        void addRentalCondition(RentalConditions rentalConditions);
        RentalConditions getRentalConditionById(int id);

}
