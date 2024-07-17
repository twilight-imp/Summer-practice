package com.example.CarRental.repositories;

import com.example.CarRental.domain.Request;
import com.example.CarRental.domain.RequestStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository {
    List<Request> findByClientAndStatusAndDate(String lastName, RequestStatus requestStatus, LocalDateTime dateEnd);

    Request create(Request request);
    Optional<Request> findById(Class<Request> requestClass, int id);

    Request findByPayment(int paymentId);

}
