package com.practise.httpsflow.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practise.httpsflow.exception.PaymentException;
import com.practise.httpsflow.model.OutboxEvent;
import com.practise.httpsflow.model.Payment;
import com.practise.httpsflow.repository.OutboxRepository;
import com.practise.httpsflow.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OutboxRepository outboxRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public PaymentService(PaymentRepository paymentRepository, OutboxRepository outboxRepository) {
        this.paymentRepository = paymentRepository;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    public Payment createPayment(String orderId, BigDecimal amount){

        if(orderId ==null){
            throw PaymentException.missingField("orderId");
        }
        if(amount ==null && amount.compareTo(BigDecimal.ZERO) <=0){
            throw PaymentException.invalidAmount();
        }
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setStatus("CREATED");

        Payment saved = paymentRepository.save(payment);
        OutboxEvent event = new OutboxEvent();
        event.setAggregateType("PAYMENT");
        event.setAggregateId(saved.getId().toString());
        event.setEventType("PAYMENT_CREATED");
        try {
            event.setPayload(mapper.writeValueAsString(
                    Map.of("paymentid", saved.getId(),
                            "orderid", saved.getOrderId(),
                            "amount", saved.getAmount())));
        }catch (Exception e){
            throw new RuntimeException();
        }
        outboxRepository.save(event);
        return saved;
    }

    public Payment getPaymentByID(Long id){
        return paymentRepository.findById(id).orElseThrow(() -> PaymentException.notFound(id));
    }

    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

}
