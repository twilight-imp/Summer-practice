package com.example.CarRental.services.impl;

import com.example.CarRental.domain.*;
import com.example.CarRental.repositories.*;
import com.example.CarRental.services.RequestDomainService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class RequestDomainServiceImpl implements RequestDomainService {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RentalConditionsRepository rentalConditions;
    @Autowired
    private ClientRepository clientRepository;

    //создание заявки с проверкой клиента и автомобиля
    @Override
    public void addRequest(Request request, int clientId, int carId) {
        carRepository.updateStatus(carId, CarStatus.RESERVED);
        requestRepository.create(request);
        if (checkClient(clientId, carId, request.getNumDays()) && checkCar(carId, request.getDateStart(), request.getNumDays())) {
            requestRepository.updateStatus(request.getId(), RequestStatus.APPROVED);
            Car car = carRepository.findById(Car.class, carId);
            Payment payment = new Payment(car.getCostPerDay() * request.getNumDays(), LocalDateTime.now(), request);
            paymentRepository.create(payment);
        } else requestRepository.updateStatus(request.getId(), RequestStatus.NOT_APPROVED);

        carRepository.updateStatus(carId, CarStatus.AVAILABLE);
    }


    @Override
    public boolean checkClient(int clientId, int carId, int numDays) {
        Client client = clientRepository.findById(Client.class, clientId);
        int minAge = 18;
        int minExperience = 0;
        int minDays = 1;
        RentalConditions rentalConditions = this.rentalConditions.findByCarId(carId);
        if (rentalConditions != null) {
            minAge = rentalConditions.getMinAge();
            minExperience = rentalConditions.getMinDrivingExperience();
            minDays = rentalConditions.getMinRentDays();
        }
        int clientAge = Period.between(LocalDate.now(), client.getBirthday()).getYears();
        if (clientAge >= minAge && client.getDrivingExperience() >= minExperience && numDays >= minDays)
            return true;
        else return false;
    }

    @Override
    public boolean checkCar(int id, LocalDateTime startDate, int numDays) {
        LocalDateTime endDate = startDate.plusDays(numDays);
        Car car = carRepository.findByDateRequest(id, startDate, endDate);
        if (car == null) return true;
        return false;
    }

    //отмена заявки не менее чем за сутки до начала аренды
    @Override
    public void returnRequest(int idRequest) {
        Request request = requestRepository.findById(Request.class, idRequest);
        Payment payment= paymentRepository.findByRequest(idRequest);
        if (checkRequest(idRequest)){
            if (request.getRequestStatus() == RequestStatus.APPROVED) {
                requestRepository.updateStatus(idRequest, RequestStatus.CANCELED);
            }else if(request.getRequestStatus() == RequestStatus.CONFIRMED){
                requestRepository.updateStatus(idRequest, RequestStatus.CANCELED);
                paymentRepository.updateStatus(payment.getId(),PaymentStatus.RETURNED);
            }
        }
    }

    @Override
    public boolean checkRequest(int idRequest) {
        Request request = requestRepository.findById(Request.class, idRequest);
        if ((request.getDateStart().minusDays(request.getNumDays())).getHour() < 24 ) return false;
        else return true;
    }



}
