package edu.eci.cvds.payment_process.DTO;

import java.util.List;

import edu.eci.cvds.payment_process.model.PaymentStatus;

public class PaymentResponseDTO {

    private String transactionId;
    private String userId;
    private double totalAmount;
    private PaymentStatus status;
    private String errorMessage;
    private List<PaymentItemDTO> items;

    // Constructors, Getters, and Setters

    public PaymentResponseDTO() {}

    public PaymentResponseDTO(String transactionId, String userId, double totalAmount, PaymentStatus status,
                              String errorMessage, List<PaymentItemDTO> items) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.errorMessage = errorMessage;
        this.items = items;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<PaymentItemDTO> getItems() {
        return items;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setItems(List<PaymentItemDTO> items) {
        this.items = items;
    }
}
