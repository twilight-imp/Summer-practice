package com.example.CarRental.services;

import com.example.CarRental.dtos.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto addClient(ClientDto clientDto);
    ClientDto getClientById(int id);
    List<ClientDto> findAll();
}
