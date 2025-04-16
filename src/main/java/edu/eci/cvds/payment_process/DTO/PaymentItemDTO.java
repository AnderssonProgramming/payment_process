package edu.eci.cvds.payment_process.DTO;

public class PaymentItemDTO {

    private String productName;
    private double unitPrice;
    private int quantity;
    private String purchaseDate; // Expecting format DD-MM-YYYY

    // Constructors, Getters, and Setters

    public PaymentItemDTO() {}

    public PaymentItemDTO(String productName, double unitPrice, int quantity, String purchaseDate) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    public String getProductName() {
        return productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
