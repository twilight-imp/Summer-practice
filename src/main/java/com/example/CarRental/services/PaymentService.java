package com.example.CarRental.services;



public interface PaymentService {

    boolean makePayment(int idPayment);

    boolean returnPayment(int idPayment);

    int getDiscountByClient(int idClient);

    int applyDiscount(int idPayment);

}
