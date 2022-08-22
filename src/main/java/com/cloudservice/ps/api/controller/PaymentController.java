package com.cloudservice.ps.api.controller;

import com.cloudservice.ps.api.entity.Payment;
import com.cloudservice.ps.api.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping(value="/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/process")
    @ResponseStatus(HttpStatus.OK)
    public Payment doPayment(@RequestBody Payment payment){
        log.info("Process Payment:{}",payment);
        return paymentService.doPayment(payment);
    }
    @GetMapping(value="/{idPayment}")
    @ResponseStatus(HttpStatus.OK)
    public Payment getPayment(@PathVariable Integer idPayment){
        log.info("Get Payment by idPayment:{}",idPayment);
       return paymentService.getPayment(idPayment);
    }

}
