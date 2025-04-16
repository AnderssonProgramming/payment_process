package edu.eci.cvds.payment_process.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import edu.eci.cvds.payment_process.DTO.PaymentRequestDTO;
import edu.eci.cvds.payment_process.DTO.PaymentResponseDTO;
import edu.eci.cvds.payment_process.service.PaymentService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // This allows requests from localhost:3000
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @ApiResponse(responseCode = "201", description = "Payment processed successfully")
    @ApiResponse(responseCode = "400", description = "Invalid payment request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "404", description = "Payment not found")
    // Endpoint to process a payment
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponseDTO createPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        return paymentService.processPayment(paymentRequest);
    }

    @ApiResponse(responseCode = "200", description = "Payment retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Payment not found")
    // Endpoint to retrieve payments by userId
    @GetMapping("/{userId}")
    public List<PaymentResponseDTO> getPayments(@PathVariable String userId) {
        return paymentService.getPaymentsByUserId(userId);
    }
}