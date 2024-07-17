package com.example.CarRental.repositories;

import com.example.CarRental.domain.Office;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfficeRepository{
    Office create(Office office);
    Optional<Office> findById(Class<Office> officeClass, int id);
    List<Office> getAll(Class<Office> officeClass);

}
