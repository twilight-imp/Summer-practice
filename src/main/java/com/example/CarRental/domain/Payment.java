package com.example.CarRental.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {
    private int amount;
    private LocalDateTime date;
    private PaymentStatus paymentStatus;
    private Request request;

    public Payment(int amount, LocalDateTime date, Request request) {
        this.amount = amount;
        this.date = date;
        this.paymentStatus = paymentStatus;
        this.request = request;
    }

    protected Payment() {
    }

    @Column(nullable = false)
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    @Column(nullable = false)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    @OneToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
