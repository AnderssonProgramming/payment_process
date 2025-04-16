package edu.eci.cvds.payment_process.DTO;

import java.util.List;

public class PaymentRequestDTO {

    private String userId;
    private String transactionId; // Optional, can be generated on backend if null
    private List<PaymentItemDTO> items;


    // Constructors, Getters, and Setters

    public PaymentRequestDTO() {}

    public PaymentRequestDTO(String userId, String transactionId, List<PaymentItemDTO> items, double totalAmount) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.items = items;

    }

    public String getUserId() {
        return userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public List<PaymentItemDTO> getItems() {
        return items;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setItems(List<PaymentItemDTO> items) {
        this.items = items;
    }

    
}