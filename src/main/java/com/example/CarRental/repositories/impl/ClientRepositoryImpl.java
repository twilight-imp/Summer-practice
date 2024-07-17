package com.example.CarRental.repositories.impl;

import com.example.CarRental.domain.Client;
import com.example.CarRental.repositories.AbstractRepository;
import com.example.CarRental.repositories.ClientRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepositoryImpl extends AbstractRepository<Client> implements ClientRepository {

}
