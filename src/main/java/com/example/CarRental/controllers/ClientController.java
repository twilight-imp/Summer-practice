package com.example.CarRental.controllers;

import com.example.CarRental.dtos.ClientDto;
import com.example.CarRental.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    Iterable<ClientDto> getAll() {
        return clientService.findAll();
    }

    @GetMapping("/clients/{id}")
    ClientDto getOne(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    @PostMapping("/clients")
    public ClientDto create(@RequestBody ClientDto clientDto) {
        return clientService.addClient(clientDto);
    }

}
