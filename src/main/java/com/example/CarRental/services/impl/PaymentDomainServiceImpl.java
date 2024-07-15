package com.example.CarRental.services.impl;

import com.example.CarRental.domain.PaymentStatus;
import com.example.CarRental.domain.Request;
import com.example.CarRental.repositories.PaymentRepository;
import com.example.CarRental.repositories.RequestRepository;
import com.example.CarRental.services.PaymentDomainService;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentDomainServiceImpl implements PaymentDomainService {

    @Autowired
    private PaymentRepository paymentRepository;
    private RequestRepository requestRepository;


    @Override
    public void makePayment(int idPayment) {
        if (checkPayment(idPayment)) paymentRepository.updateStatus(idPayment, PaymentStatus.COMPLETED);
    }
    @Override
    public boolean checkPayment(int idPayment) {
        Request request = requestRepository.findByPayment(idPayment);
        if ((request.getDateStart().minusDays(request.getNumDays())).getHour() < 24) {
            paymentRepository.updateStatus(idPayment, PaymentStatus.OVERDUE);
            return false;
        } else return true;
    }




}
