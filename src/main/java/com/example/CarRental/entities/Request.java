package com.example.CarRental.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "request")
public class Request extends BaseEntity {
    private Client client;
    private Car car;

    private LocalDateTime dateStart;

    private LocalDateTime dateEnd;
    private int numDays;
    private RequestStatus requestStatus;

    private Payment payment;

    public Request(Client client, Car car, LocalDateTime dateStart, LocalDateTime dateEnd, int numDays, RequestStatus requestStatus) {
        this.client = client;
        this.car = car;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.numDays = numDays;
        this.requestStatus = requestStatus;
    }

    protected Request() {
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Column(name = "date_start", nullable = false)
    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    @Column(name = "date_end",nullable = false)
    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Column(nullable = false)
    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    @OneToOne(mappedBy = "request", targetEntity = Payment.class)
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

}
