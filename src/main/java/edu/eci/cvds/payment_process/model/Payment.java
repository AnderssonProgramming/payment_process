package edu.eci.cvds.payment_process.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Document(collection = "payments")
public class Payment {

    @Id
    private String id;
    private String userId;
    private String transactionId;
    private List<PaymentItem> items;
    private double totalAmount;
    private PaymentStatus status;
    private String errorMessage;
    private Date paymentDate;

    // Constructors, Getters, and Setters

    public Payment() {}

    public Payment(String userId, String transactionId, List<PaymentItem> items, double totalAmount,
                   PaymentStatus status, String errorMessage, Date paymentDate) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = status;
        this.errorMessage = errorMessage;
        this.paymentDate = paymentDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<PaymentItem> getItems() {
        return items;
    }

    public void setItems(List<PaymentItem> items) {
        this.items = items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
