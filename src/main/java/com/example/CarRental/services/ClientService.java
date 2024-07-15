package com.example.CarRental.services;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.Client;

public interface ClientService {
    void addClient(Client client);
    Client getClientById(int id);
}
