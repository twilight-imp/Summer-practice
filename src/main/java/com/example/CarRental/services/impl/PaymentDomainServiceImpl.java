package com.example.CarRental.services.impl;

import com.example.CarRental.domain.*;
import com.example.CarRental.dtos.CarDto;
import com.example.CarRental.dtos.PaymentDto;
import com.example.CarRental.repositories.CarRepository;
import com.example.CarRental.repositories.ClientRepository;
import com.example.CarRental.repositories.PaymentRepository;
import com.example.CarRental.repositories.RequestRepository;
import com.example.CarRental.services.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class PaymentDomainServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelMapper modelMapper;



    //произведение оплаты со сменой статуса платежа и заявки и применение скидки перед оплатой
    @Transactional
    @Override
    public boolean makePayment(int idPayment) {
        Payment payment = paymentRepository.findById(Payment.class, idPayment).
                orElseThrow(() -> new IllegalArgumentException("Оплата не найдена"));
        Request request = requestRepository.findByPayment(idPayment);
        if(payment.getPaymentStatus() == PaymentStatus.AWAITING) {
            if (checkPayment(idPayment)) {
                payment.setPaymentStatus(PaymentStatus.COMPLETED);
                request.setRequestStatus(RequestStatus.CONFIRMED);
                return true;
            }
            else {
                payment.setPaymentStatus(PaymentStatus.OVERDUE);
            }
        }return false;
    }

    //возврат платежа не менее чем за сутки до начала аренды
    @Transactional
    @Override
    public boolean returnPayment(int idPayment) {
        Payment payment = paymentRepository.findById(Payment.class, idPayment).
                orElseThrow(() -> new IllegalArgumentException("Оплата не найдена"));;
        if(payment.getPaymentStatus() == PaymentStatus.COMPLETED)
        if (checkPayment((idPayment))) {
            payment.setPaymentStatus(PaymentStatus.RETURNED);
            return true;
        }
        return false;
    }

    //проверка просрочки платежа
    public void overduePayment(int idPayment) {
        Payment payment = paymentRepository.findById(Payment.class, idPayment).
                orElseThrow(() -> new IllegalArgumentException("Оплата не найдена"));;
        Request request = requestRepository.findByPayment(idPayment);
        if(!checkPayment(idPayment)){
            payment.setPaymentStatus(PaymentStatus.OVERDUE);
            request.setRequestStatus(RequestStatus.CANCELED);
        }
    }

    //получение персональной скидки
    @Override
    public int getDiscountByClient(int idClient) {
        Client client = clientRepository.findById(Client.class, idClient).
                orElseThrow(() -> new IllegalArgumentException("Клиент не найден"));;
        if (LocalDate.now().getDayOfMonth() == client.getBirthday().getDayOfMonth()) return 25;
        else if (paymentRepository.findByClientId(idClient).size() > 30) return 15;
        else if (paymentRepository.findByClientId(idClient).size() > 15) return 10;
        else if (paymentRepository.findByClientId(idClient).size() > 5) return 5;
        else return 0;
    }

    @Transactional
    @Override
    public int applyDiscount(int idPayment) {
        Payment payment = paymentRepository.findById(Payment.class, idPayment).
                orElseThrow(() -> new IllegalArgumentException("Оплата не найдена"));
        Client client = requestRepository.findByPayment(idPayment).getClient();
        int discount = getDiscountByClient(client.getId());
        if (discount!= 0){
            int newAmount = (int) (payment.getAmount() - Math.round(payment.getAmount() * discount * 0.01));
            payment.setAmount(newAmount);
            return newAmount;
        }
        return 0;
    }

    public boolean checkPayment(int idPayment) {
        Request request = requestRepository.findByPayment(idPayment);
        LocalDateTime startDate = request.getDateStart();
        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(now, startDate).toHours() < 24 ) return false;
        else return true;
    }




}
