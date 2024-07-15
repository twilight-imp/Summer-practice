package com.example.CarRental.services;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.Client;
import com.example.CarRental.domain.RentalConditions;

public interface RentalConditionsService {
        void addRentalCondition(RentalConditions rentalConditions);
        RentalConditions getRenatalConditionById(int id);

}
