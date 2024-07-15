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
    private PaymentRepository paymentRepository;
    private CarRepository carRepository;
    private RentalConditionsRepository rentalConditions;
    private ClientRepository clientRepository;
    @Override
    public void addRequest(Request request, int clientId, int carId) {
        carRepository.updateStatus(carId, CarStatus.RESERVED);
        requestRepository.create(request);
        if (checkClient(clientId, carId, request.getNumDays()) && checkCar(carId, request.getDateStart(), request.getNumDays())) {
            requestRepository.updateStatus(request.getId(), RequestStatus.APPROVED);
            Car car = carRepository.findById(Car.class,carId);
            Payment payment = new Payment(car.getCostPerDay() * request.getNumDays(), LocalDateTime.now(), request);
            paymentRepository.create(payment);
        }else requestRepository.updateStatus(request.getId(), RequestStatus.NOT_APPROVED);

        carRepository.updateStatus(carId, CarStatus.AVAILABLE);
    }


    @Override
    public boolean checkClient(int clientId, int carId, int numDays) {
        Client client = clientRepository.findById(Client.class, clientId);
        int minAge = 18;
        int minExperience = 0;
        int minDays = 1;
        RentalConditions rentalConditions = this.rentalConditions.findByCarId(carId);
        if (rentalConditions != null){
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
        Car car = carRepository.findByRequest(id, startDate, endDate);
        if(car == null) return true;
        return false;
    }


}
