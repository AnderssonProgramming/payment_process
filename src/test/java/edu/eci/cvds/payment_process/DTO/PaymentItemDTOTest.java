package edu.eci.cvds.payment_process.DTO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PaymentItemDTOTest {

    @Test
     void testDefaultConstructor() {
        PaymentItemDTO paymentItem = new PaymentItemDTO();
        assertNull(paymentItem.getProductName());
        assertEquals(0.0, paymentItem.getUnitPrice(), 0.001);
        assertEquals(0, paymentItem.getQuantity());
        assertNull(paymentItem.getPurchaseDate());
    }

    @Test
     void testParameterizedConstructor() {
        String productName = "Test Product";
        double unitPrice = 15.99;
        int quantity = 3;
        String purchaseDate = "01-05-2023";
        
        PaymentItemDTO paymentItem = new PaymentItemDTO(productName, unitPrice, quantity, purchaseDate);
        
        assertEquals(productName, paymentItem.getProductName());
        assertEquals(unitPrice, paymentItem.getUnitPrice(), 0.001);
        assertEquals(quantity, paymentItem.getQuantity());
        assertEquals(purchaseDate, paymentItem.getPurchaseDate());
    }

    @Test
     void testGetProductName() {
        String productName = "Laptop";
        PaymentItemDTO paymentItem = new PaymentItemDTO(productName, 999.99, 1, "15-06-2023");
        assertEquals(productName, paymentItem.getProductName());
    }

    @Test
     void testGetUnitPrice() {
        double unitPrice = 25.50;
        PaymentItemDTO paymentItem = new PaymentItemDTO("Headphones", unitPrice, 2, "20-06-2023");
        assertEquals(unitPrice, paymentItem.getUnitPrice(), 0.001);
    }

    @Test
     void testGetQuantity() {
        int quantity = 5;
        PaymentItemDTO paymentItem = new PaymentItemDTO("Mouse", 12.99, quantity, "25-06-2023");
        assertEquals(quantity, paymentItem.getQuantity());
    }

    @Test
     void testGetPurchaseDate() {
        String purchaseDate = "30-06-2023";
        PaymentItemDTO paymentItem = new PaymentItemDTO("Keyboard", 45.99, 1, purchaseDate);
        assertEquals(purchaseDate, paymentItem.getPurchaseDate());
    }
}