package com.example.CarRental.dtos;

import com.example.CarRental.domain.PaymentStatus;
import com.example.CarRental.domain.Request;

import java.time.LocalDateTime;

public class PaymentDto {
    private int id;
    private int amount;
    private LocalDateTime date;
    private PaymentStatus paymentStatus;
    private int requestId;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
}
