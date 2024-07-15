package com.example.CarRental.repositories;

import com.example.CarRental.domain.Request;
import com.example.CarRental.domain.RequestStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RequestRepository {
    List<Request> findByClientAndStatusAndDate(String lastName, RequestStatus requestStatus, LocalDateTime dateEnd);

    void create(Request request);
    Request findById(Class<Request> requestClass, int id);
    void updateStatus(int id, RequestStatus requestStatus);

    Request findByPayment(int paymentId);
}
