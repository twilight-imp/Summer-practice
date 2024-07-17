package com.example.CarRental.services.impl;

import com.example.CarRental.domain.Office;
import com.example.CarRental.dtos.OfficeDto;
import com.example.CarRental.repositories.OfficeRepository;
import com.example.CarRental.services.OfficeService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OfficeDto addOffice(OfficeDto officeDto) {
        Office office = modelMapper.map(officeDto,Office.class);
        return modelMapper.map(officeRepository.create(office), OfficeDto.class);
    }

    @Override
    public OfficeDto getOfficeById(int id) {
        return modelMapper.map(officeRepository.findById(Office.class, id),OfficeDto.class);
    }

    @Override
    @Transactional
    public List<OfficeDto> findAll() {
        return officeRepository.getAll(Office.class).stream().map(office -> modelMapper.map(office, OfficeDto.class)).toList();
    }
}
