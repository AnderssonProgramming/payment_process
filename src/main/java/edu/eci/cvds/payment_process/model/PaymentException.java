package edu.eci.cvds.payment_process.model;

public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message);
    }
}
