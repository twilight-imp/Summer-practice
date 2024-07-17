package com.example.CarRental.controllers;

import com.example.CarRental.dtos.RequestDto;
import com.example.CarRental.services.impl.RequestDomainServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {

    @Autowired
    private RequestDomainServiceImpl requestDomainService;


    @GetMapping("/requests/{id}")
    RequestDto getOne(@PathVariable Integer id){
        return requestDomainService.getRequestById(id);
    }


    @GetMapping("/requests/return/{id}")
    ResponseEntity<String> returnRequest(@PathVariable Integer id){
        Boolean checkReturnRequest = requestDomainService.returnRequest(id);
        if(checkReturnRequest) return ResponseEntity.ok("Заявка успешно отменена");
        else return ResponseEntity.ok("Заявка не может быть отменена");
    }

    @PostMapping("/requests/create")
    public ResponseEntity<String> create(@RequestBody RequestDto requestDto) {
        Boolean checkAddRequest = requestDomainService.addRequest(requestDto);
        if(checkAddRequest) return ResponseEntity.ok("Заявка успешно создана");
        else return ResponseEntity.ok("Заявка отклонена");
    }

}
