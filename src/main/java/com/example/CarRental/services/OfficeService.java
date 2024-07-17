package com.example.CarRental.services;

import com.example.CarRental.dtos.OfficeDto;

import java.util.List;

public interface OfficeService {
    OfficeDto addOffice(OfficeDto officeDto);
    OfficeDto getOfficeById(int id);

    List<OfficeDto> findAll();
}
