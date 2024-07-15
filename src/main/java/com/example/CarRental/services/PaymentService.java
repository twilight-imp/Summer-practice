package com.example.CarRental.services;

import com.example.CarRental.domain.Payment;

import java.util.List;

public interface PaymentService {
    void addPayment(Payment payment);
    List<Payment> findByClientId(int idClient);
}
