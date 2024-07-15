package com.example.CarRental.services;

import com.example.CarRental.domain.Payment;

import java.util.List;

public interface PaymentDomainService {
    void makePayment(int idPayment);

    boolean checkPayment(int idPayment);

}
