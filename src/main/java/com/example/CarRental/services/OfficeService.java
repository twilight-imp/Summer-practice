package com.example.CarRental.services;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.Office;

public interface OfficeService {
    void addOffice(Office office);
    Office getOfficeById(int id);
}
