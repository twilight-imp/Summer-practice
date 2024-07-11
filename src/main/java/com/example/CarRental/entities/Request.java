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
    private Payment payment;
    private String status;

    public Request(Client client, Car car, LocalDateTime dateStart, LocalDateTime dateEnd, int numDays, Payment payment, String status) {
        this.client = client;
        this.car = car;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.numDays = numDays;
        this.payment = payment;
        this.status = status;
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

    @Column(nullable = false)
    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    @Column(nullable = false)
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
    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    @Column(length = 20, nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
