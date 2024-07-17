package com.example.CarRental.services.impl;

import com.example.CarRental.domain.*;
import com.example.CarRental.dtos.CarDto;
import com.example.CarRental.dtos.OfficeDto;
import com.example.CarRental.dtos.RequestDto;
import com.example.CarRental.repositories.*;
import com.example.CarRental.services.RequestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDateTime;

@Service
public class RequestDomainServiceImpl implements RequestService {
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

    @Autowired
    private ModelMapper modelMapper;

    //создание заявки с проверкой клиента и автомобиля

    @Transactional
    @Override
    public boolean addRequest(RequestDto requestDto) {
        Client client = clientRepository.findById(Client.class, requestDto.getClientId()).
                orElseThrow(() -> new IllegalArgumentException("Клиент не найден"));
        Car car = carRepository.findById(Car.class, requestDto.getCarId()).
                orElseThrow(() -> new IllegalArgumentException("Автомобиль не найден"));;
        car.setCarStatus(CarStatus.RESERVED);
        Request request = new Request(client, car,requestDto.getDateStart(), requestDto.getDateEnd(), requestDto.getNumDays());
        requestRepository.create(request);
        if (checkClient(client.getId(), car.getId(), request.getNumDays()) && checkCar(car.getId(), request.getDateStart(), request.getNumDays())) {
            request.setRequestStatus(RequestStatus.APPROVED);
            Payment payment = new Payment(car.getCostPerDay() * request.getNumDays(), LocalDateTime.now(), request);
            paymentRepository.create(payment);
            car.setCarStatus(CarStatus.AVAILABLE);
            return true;
        }
        request.setRequestStatus(RequestStatus.NOT_APPROVED);
        car.setCarStatus(CarStatus.AVAILABLE);
        return false;

    }

    @Override
    public RequestDto getRequestById(int id) {
        return modelMapper.map(requestRepository.findById(Request.class, id), RequestDto.class);
    }


    public boolean checkClient(int clientId, int carId, int numDays) {
        Client client = clientRepository.findById(Client.class, clientId).
                orElseThrow(() -> new IllegalArgumentException("Клиент не найден"));;
        Car car = carRepository.findById(Car.class, carId).
                orElseThrow(() -> new IllegalArgumentException("Автомобиль не найден"));;
        int minAge = 18;
        int minExperience = 0;
        int minDays = 1;
        RentalConditions rentalConditions = car.getRentalConditions();
        if (rentalConditions != null) {
            minAge = rentalConditions.getMinAge();
            minExperience = rentalConditions.getMinDrivingExperience();
            minDays = rentalConditions.getMinRentDays();
        }
        int clientAge = Period.between(client.getBirthday(),LocalDate.now()).getYears();
        if (clientAge >= minAge && client.getDrivingExperience() >= minExperience && numDays >= minDays) {
            return true;
        }
        else return false;
    }

    public boolean checkCar(int id, LocalDateTime startDate, int numDays) {
        LocalDateTime endDate = startDate.plusDays(numDays);
        Car car = carRepository.findByDateRequest(id, startDate, endDate);
        if (car == null){
            return true;}
        return false;
    }

    //отмена заявки не менее чем за сутки до начала аренды

    @Transactional
    @Override
    public boolean returnRequest(int idRequest) {
        Request request = requestRepository.findById(Request.class, idRequest).
                orElseThrow(() -> new IllegalArgumentException("Заявка не найдена"));;
        Payment payment= paymentRepository.findByRequest(idRequest);
        if (checkRequest(idRequest)){
            if (request.getRequestStatus() == RequestStatus.APPROVED) {
                request.setRequestStatus(RequestStatus.CANCELED);
                payment.setPaymentStatus(PaymentStatus.CANCELED);
                return true;
            }else if(request.getRequestStatus() == RequestStatus.CONFIRMED){
                request.setRequestStatus(RequestStatus.CANCELED);
                payment.setPaymentStatus(PaymentStatus.RETURNED);
                return true;
            }
        }return false;
    }

    @Override
    public boolean checkRequest(int idRequest) {
        Request request = requestRepository.findById(Request.class, idRequest).
                orElseThrow(() -> new IllegalArgumentException("Заявка не найдена"));;
        LocalDateTime startDate = request.getDateStart();
        LocalDateTime now = LocalDateTime.now();
        if (Duration.between(now, startDate).toHours() < 24 ) return false;
        else return true;
    }



}
