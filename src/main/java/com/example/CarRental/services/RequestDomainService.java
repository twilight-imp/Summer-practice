package com.example.CarRental.services;

import com.example.CarRental.domain.Request;

import java.time.LocalDateTime;

public interface RequestDomainService {
    void addRequest(Request request, int clientId, int carId);
    boolean checkClient(int clientId, int carId, int numDays);

    boolean checkCar(int id, LocalDateTime dateStart, int numDays);

    void returnRequest(int idRequest);
    boolean checkRequest(int idRequest);


}
