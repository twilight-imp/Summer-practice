package com.example.CarRental.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {
    private int amount;
    private LocalDateTime date;
    private String status;
    private Request request;

    public Payment(int amount, LocalDateTime date, String status, Request request) {
        this.amount = amount;
        this.date = date;
        this.status = status;
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
    @Column(nullable = false)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @OneToOne(mappedBy = "payment", targetEntity = Request.class)
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
