package com.example.CarRental.services.impl;

import com.example.CarRental.domain.Payment;
import com.example.CarRental.domain.PaymentStatus;
import com.example.CarRental.repositories.PaymentRepository;
import com.example.CarRental.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public void addPayment(Payment payment) {
        paymentRepository.create(payment);
    }

    @Override
    public List<Payment> findByClientId(int idClient) {
        return paymentRepository.findByClientId(idClient);
    }
}
