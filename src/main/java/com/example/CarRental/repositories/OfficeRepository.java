package com.example.CarRental.repositories;

import com.example.CarRental.domain.Office;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository{
    void create(Office office);
    Office findById(Class<Office> officeClass, int id);
    Office update(Office office);
}
