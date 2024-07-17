package com.example.CarRental.controllers;

import com.example.CarRental.services.impl.PaymentDomainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    private PaymentDomainServiceImpl paymentDomainService;


    @GetMapping("/payment/return/{id}")
    ResponseEntity<String> returnPayment(@PathVariable Integer id){
        Boolean checkReturnRequest = paymentDomainService.returnPayment(id);
        if(checkReturnRequest) return ResponseEntity.ok("Оплата успешно возвращена");
        else return ResponseEntity.ok("Оплата не может быть возвращена");
    }

    @GetMapping("/payment/make/{id}")
    ResponseEntity<String> makePayment(@PathVariable Integer id){
        Boolean checkPaymentRequest = paymentDomainService.makePayment(id);
        if(checkPaymentRequest) return ResponseEntity.ok("Оплата успешно совершена");
        else return ResponseEntity.ok("Оплата не может быть совершена");
    }

    @PostMapping("/payment/getDiscount/{id}")
    public ResponseEntity<String> getDiscount(@PathVariable Integer id) {
        int newAmount = paymentDomainService.applyDiscount(id);
        if(newAmount != 0) return ResponseEntity.ok("Скидка применена. Сумма платежа:" + newAmount);
        else return ResponseEntity.ok("У вас нет скидок");
    }
}
