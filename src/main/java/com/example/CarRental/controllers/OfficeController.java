package com.example.CarRental.controllers;

import com.example.CarRental.dtos.OfficeDto;
import com.example.CarRental.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @GetMapping("/offices")
    Iterable<OfficeDto> getAll() {
        return officeService.findAll();
    }

    @GetMapping("/offices/{id}")
    OfficeDto getOne(@PathVariable Integer id){
        return officeService.getOfficeById(id);
    }

    @PostMapping("/offices")
    public OfficeDto create(@RequestBody OfficeDto officeDto) {
        return officeService.addOffice(officeDto);
    }


}
