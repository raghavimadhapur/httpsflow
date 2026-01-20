package com.practise.httpsflow.api;

import com.practise.httpsflow.model.Payment;
import com.practise.httpsflow.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping("/payments")
    public Payment create(@RequestParam String orderId,
                          @RequestParam BigDecimal amount) {

        return paymentService.createPayment(orderId, amount);
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable Long id){
        return paymentService.getPaymentByID(id);
    }

    @GetMapping
    public List<Payment> getAll(){
        return paymentService.getAllPayments();
    }

}
