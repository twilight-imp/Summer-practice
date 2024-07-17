package com.example.CarRental.services.impl;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.RentalConditions;
import com.example.CarRental.dtos.CarDto;
import com.example.CarRental.dtos.RentalConditionsDto;
import com.example.CarRental.repositories.RentalConditionsRepository;
import com.example.CarRental.services.RentalConditionsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalConditionsServiceImpl implements RentalConditionsService {
    @Autowired
    private RentalConditionsRepository rentalConditionsRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void addRentalCondition(RentalConditionsDto rentalConditionsDto) {
        RentalConditions rentalConditions = modelMapper.map(rentalConditionsDto,RentalConditions.class);
        modelMapper.map(rentalConditionsRepository.create(rentalConditions), RentalConditions.class);
    }

}
