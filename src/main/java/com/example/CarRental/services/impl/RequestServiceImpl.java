package com.example.CarRental.services.impl;

import com.example.CarRental.domain.Request;
import com.example.CarRental.repositories.RequestRepository;
import com.example.CarRental.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request getRequestById(int id) {
        return requestRepository.findById(Request.class, id);
    }
}
