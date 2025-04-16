package edu.eci.cvds.payment_process.model;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;



class PaymentItemTest {

    @Test
     void testDefaultConstructor() {
        PaymentItem item = new PaymentItem();
        assertNull(item.getProductName());
        assertEquals(0.0, item.getUnitPrice(), 0.001);
        assertEquals(0, item.getQuantity());
        assertNull(item.getPurchaseDate());
    }

    @Test
     void testParameterizedConstructor() {
        String productName = "Test Product";
        double unitPrice = 19.99;
        int quantity = 3;
        Date purchaseDate = new Date();
        
        PaymentItem item = new PaymentItem(productName, unitPrice, quantity, purchaseDate);
        
        assertEquals(productName, item.getProductName());
        assertEquals(unitPrice, item.getUnitPrice(), 0.001);
        assertEquals(quantity, item.getQuantity());
        assertEquals(purchaseDate, item.getPurchaseDate());
    }

    @Test
     void testGettersAndSetters() {
        PaymentItem item = new PaymentItem();
        
        // Test productName getter/setter
        String productName = "New Product";
        item.setProductName(productName);
        assertEquals(productName, item.getProductName());
        
        // Test unitPrice getter/setter
        double unitPrice = 29.99;
        item.setUnitPrice(unitPrice);
        assertEquals(unitPrice, item.getUnitPrice(), 0.001);
        
        // Test quantity getter/setter
        int quantity = 5;
        item.setQuantity(quantity);
        assertEquals(quantity, item.getQuantity());
        
        // Test purchaseDate getter
        Date purchaseDate = new Date();
        PaymentItem itemWithDate = new PaymentItem("Product", 10.0, 1, purchaseDate);
        assertEquals(purchaseDate, itemWithDate.getPurchaseDate());
    }
    
    @Test
     void testNegativeValues() {
        PaymentItem item = new PaymentItem();
        
        // Test negative price
        item.setUnitPrice(-10.5);
        assertEquals(-10.5, item.getUnitPrice(), 0.001);
        
        // Test negative quantity
        item.setQuantity(-5);
        assertEquals(-5, item.getQuantity());
    }
}