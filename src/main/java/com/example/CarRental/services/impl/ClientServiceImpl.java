package com.example.CarRental.services.impl;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.Client;
import com.example.CarRental.domain.Office;
import com.example.CarRental.dtos.CarDto;
import com.example.CarRental.dtos.ClientDto;
import com.example.CarRental.dtos.OfficeDto;
import com.example.CarRental.repositories.ClientRepository;
import com.example.CarRental.services.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Transactional
    @Override
    public ClientDto addClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto,Client.class);
        return modelMapper.map(clientRepository.create(client), ClientDto.class);
    }

    @Override
    public ClientDto getClientById(int id) {
        return modelMapper.map(clientRepository.findById(Client.class, id), ClientDto.class);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.getAll(Client.class).stream().map(client -> modelMapper.map(client, ClientDto.class)).toList();
    }

}
