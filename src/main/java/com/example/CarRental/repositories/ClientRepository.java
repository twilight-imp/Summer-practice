package com.example.CarRental.repositories;

import com.example.CarRental.domain.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository{
    void create(Client client);
    Client findById(Class<Client> clientClass, int id);
    Client update(Client client);

    Client findByPhone(String phone);
}
