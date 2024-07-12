package com.example.CarRental.repositories;

import com.example.CarRental.entities.Car;
import com.example.CarRental.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query(value = "select p from Payment" +
            "join p.request r where r.dateStart < :dateStart "
    )
    List<Car> findByStartRequest(@Param(value = "data") LocalDateTime dateStart);
}
