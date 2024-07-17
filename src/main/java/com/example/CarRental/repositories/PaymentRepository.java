package com.example.CarRental.repositories;

import com.example.CarRental.domain.Payment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository{
    Payment findByRequest(int idRequest);

    List<Payment> findByClientId(int clientId);
    Payment create(Payment payment);
    Optional<Payment> findById(Class<Payment> paymentClass, int id);
}
