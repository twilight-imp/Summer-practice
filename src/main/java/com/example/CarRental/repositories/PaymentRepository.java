package com.example.CarRental.repositories;

import com.example.CarRental.domain.Payment;
import com.example.CarRental.domain.PaymentStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository{
    Payment findByRequest(int idRequest);

    List<Payment> findByClientId(int clientId);
    void create(Payment payment);
    Payment findById(Class<Payment> paymentClass, int id);
    void updateStatus(int Id, PaymentStatus paymentStatus);
}
