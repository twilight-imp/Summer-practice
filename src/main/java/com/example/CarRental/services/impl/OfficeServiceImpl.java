package com.example.CarRental.services.impl;

import com.example.CarRental.domain.Client;
import com.example.CarRental.domain.Office;
import com.example.CarRental.repositories.OfficeRepository;
import com.example.CarRental.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;

public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public void addOffice(Office office) {
        officeRepository.create(office);
    }

    @Override
    public Office getOfficeById(int id) {
        return officeRepository.findById(Office.class, id);
    }
}
