package com.example.CarRental.repositories;

import com.example.CarRental.domain.Client;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository{
    Client create(Client client);
    Optional<Client> findById(Class<Client> clientClass, int id);

    List<Client> getAll(Class<Client> clientClass);

}
