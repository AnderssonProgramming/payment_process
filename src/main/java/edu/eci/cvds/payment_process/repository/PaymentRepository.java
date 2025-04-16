package edu.eci.cvds.payment_process.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.eci.cvds.payment_process.model.Payment;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByUserId(String userId);
}

