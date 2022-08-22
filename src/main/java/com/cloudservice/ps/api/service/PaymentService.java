package com.cloudservice.ps.api.service;

import com.cloudservice.ps.api.entity.Payment;
import com.cloudservice.ps.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    public PaymentRepository paymentRepository;
    private final String PAYMENT_APPROVED="Approved";
    private final String PAYMENT_FAILED="Failed";

    public Payment doPayment(Payment payment){
        payment.setPaymentStatus(PaymentStatus()); // TODO : Status from third party payment capture api call
        this.generateTransactionId(payment);//TODO: set Transaction ID but it should be coming from call provider
       this.generatePaymentId(payment); //TODO: Auto increament from  by database
        return paymentRepository.save(payment);
    }

    public Payment getPayment(Integer idPayment){
         Optional<Payment> optPayment=paymentRepository.findById(idPayment);
        Payment payment=optPayment.orElse(new Payment());
         payment.setPaymentStatus(UUID.randomUUID().toString());
        return payment;
    }

    public String PaymentStatus(){
        return new  Random().nextBoolean()?PAYMENT_APPROVED:PAYMENT_FAILED;
    }

    public Payment generateTransactionId(Payment payment){
        payment.setTransactionId(UUID.randomUUID().toString());
        return payment;
    }

    public Payment generatePaymentId(Payment payment){
        payment.setPaymentId(new Random().nextInt(200));
        return payment;
    }

}
