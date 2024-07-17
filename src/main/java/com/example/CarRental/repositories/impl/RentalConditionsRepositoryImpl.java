package com.example.CarRental.repositories.impl;

import com.example.CarRental.domain.RentalConditions;
import com.example.CarRental.repositories.AbstractRepository;
import com.example.CarRental.repositories.RentalConditionsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RentalConditionsRepositoryImpl extends AbstractRepository<RentalConditions> implements RentalConditionsRepository {
}

