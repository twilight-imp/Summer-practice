package com.example.CarRental.services.impl;

import com.example.CarRental.domain.Client;
import com.example.CarRental.repositories.ClientRepository;
import com.example.CarRental.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public void addClient(Client client) {
        clientRepository.create(client);
    }

    @Override
    public Client getClientById(int id) {
        return  clientRepository.findById(Client.class, id);
    }
}
