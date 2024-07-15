package com.example.CarRental.services.impl;

import com.example.CarRental.domain.*;
import com.example.CarRental.repositories.CarRepository;
import com.example.CarRental.repositories.ClientRepository;
import com.example.CarRental.repositories.PaymentRepository;
import com.example.CarRental.repositories.RequestRepository;
import com.example.CarRental.services.PaymentDomainService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class PaymentDomainServiceImpl implements PaymentDomainService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarRepository carRepository;


    //произведение оплаты со сменой статуса платежа и заявки
    @Override
    public void makePayment(int idPayment) {
        Payment payment = paymentRepository.findById(Payment.class, idPayment);
        Request request = requestRepository.findByPayment(idPayment);
        if(payment.getPaymentStatus() == PaymentStatus.AWAITING) {
            if (checkPayment(idPayment)) {
                paymentRepository.updateStatus(idPayment, PaymentStatus.COMPLETED);
                requestRepository.updateStatus(request.getId(), RequestStatus.CONFIRMED);
            }
            else paymentRepository.updateStatus(idPayment, PaymentStatus.OVERDUE);
        }
    }

    //возврат платежа не менее чем за сутки до начала аренды
    @Override
    public void returnPayment(int idPayment) {
        Payment payment = paymentRepository.findById(Payment.class, idPayment);
        if(payment.getPaymentStatus() == PaymentStatus.COMPLETED)
        if (checkPayment((idPayment))) {
            paymentRepository.updateStatus(idPayment, PaymentStatus.RETURNED);
        }
    }

    //проверка просрочки платежа
    @Override
    public void overduePayment(int idPayment) {
        if(!checkPayment(idPayment)){
            paymentRepository.updateStatus(idPayment, PaymentStatus.OVERDUE);
            requestRepository.updateStatus(requestRepository.findByPayment(idPayment).getId(), RequestStatus.CANCELED);
        }
    }

    //получение персональной скидки
    @Override
    public int getDiscountByClient(int idClient) {
        Client client = clientRepository.findById(Client.class, idClient);
        if (LocalDate.now() == client.getBirthday()) return 25;
        else if (paymentRepository.findByClientId(idClient).size() > 30) return 15;
        else if (paymentRepository.findByClientId(idClient).size() > 15) return 10;
        else if (paymentRepository.findByClientId(idClient).size() > 5) return 5;
        else return 0;
    }


    @Override
    public boolean checkPayment(int idPayment) {
        Request request = requestRepository.findByPayment(idPayment);
        Payment payment = paymentRepository.findById(Payment.class, idPayment);
        if ((request.getDateStart().minusDays(request.getNumDays())).getHour() < 24 ) return false;
        else return true;
    }




}
