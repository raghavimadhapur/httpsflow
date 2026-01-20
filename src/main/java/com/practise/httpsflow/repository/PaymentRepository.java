package com.practise.httpsflow.repository;

import com.practise.httpsflow.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

}
