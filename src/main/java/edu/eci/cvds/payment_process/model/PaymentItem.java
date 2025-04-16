package edu.eci.cvds.payment_process.model;

import java.util.Date;

public class PaymentItem {

    private String productName;
    private double unitPrice;
    private int quantity;
    private Date purchaseDate; // Store as Date, conversion done during DTO mapping

    // Constructors, Getters, and Setters
    public PaymentItem() {}

    public PaymentItem(String productName, double unitPrice, int quantity, Date purchaseDate) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }
}