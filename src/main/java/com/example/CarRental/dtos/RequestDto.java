package com.example.CarRental.dtos;

import com.example.CarRental.domain.Car;
import com.example.CarRental.domain.Client;
import com.example.CarRental.domain.Payment;
import com.example.CarRental.domain.RequestStatus;

import java.time.LocalDateTime;

public class RequestDto {
    private int clientId;
    private int carId;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;

    private int numDays;

    private RequestStatus requestStatus;

    public RequestDto(int clientId, int carId, LocalDateTime dateStart, LocalDateTime dateEnd, int numDays) {
        this.clientId = clientId;
        this.carId = carId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.numDays = numDays;
        this.requestStatus = RequestStatus.CREATED;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
