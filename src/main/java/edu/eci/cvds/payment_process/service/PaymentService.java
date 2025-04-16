package edu.eci.cvds.payment_process.service;

import java.util.List;

import edu.eci.cvds.payment_process.DTO.PaymentRequestDTO;
import edu.eci.cvds.payment_process.DTO.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO processPayment(PaymentRequestDTO request);
    List<PaymentResponseDTO> getPaymentsByUserId(String userId);
}
