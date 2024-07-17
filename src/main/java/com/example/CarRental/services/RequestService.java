package com.example.CarRental.services;

import com.example.CarRental.dtos.RequestDto;


public interface RequestService {

    boolean addRequest(RequestDto requestDto);
    RequestDto getRequestById(int id);

    boolean returnRequest(int idRequest);
    boolean checkRequest(int idRequest);
}

