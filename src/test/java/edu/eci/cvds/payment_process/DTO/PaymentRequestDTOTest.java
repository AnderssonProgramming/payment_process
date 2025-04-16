package edu.eci.cvds.payment_process.DTO;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;


class PaymentRequestDTOTest {

    @Test
     void testDefaultConstructor() {
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
        assertNull(paymentRequest.getUserId());
        assertNull(paymentRequest.getTransactionId());
        assertNull(paymentRequest.getItems());
    }

    @Test
     void testParameterizedConstructor() {
        // Arrange
        String userId = "user123";
        String transactionId = "trans456";
        List<PaymentItemDTO> items = createSampleItems();
        double totalAmount = 100.0;

        // Act
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO(userId, transactionId, items, totalAmount);
        
        // Assert
        assertEquals(userId, paymentRequest.getUserId());
        assertEquals(transactionId, paymentRequest.getTransactionId());
        assertEquals(items, paymentRequest.getItems());
    }

    @Test
     void testGetUserId() {
        // Arrange
        String userId = "testUser";
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO(userId, null, null, 0.0);
        
        // Act & Assert
        assertEquals(userId, paymentRequest.getUserId());
    }

    @Test
     void testGetTransactionId() {
        // Arrange
        String transactionId = "testTransaction";
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO(null, transactionId, null, 0.0);
        
        // Act & Assert
        assertEquals(transactionId, paymentRequest.getTransactionId());
    }

    @Test
     void testGetItems() {
        // Arrange
        List<PaymentItemDTO> items = createSampleItems();
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO(null, null, items, 0.0);
        
        // Act & Assert
        assertEquals(items, paymentRequest.getItems());
        assertEquals(2, paymentRequest.getItems().size());
    }

    @Test
     void testWithEmptyItemsList() {
        // Arrange
        List<PaymentItemDTO> items = new ArrayList<>();
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO("user1", "trans1", items, 0.0);
        
        // Act & Assert
        assertNotNull(paymentRequest.getItems());
        assertEquals(0, paymentRequest.getItems().size());
    }

    @Test
     void testWithNullValues() {
        // Arrange
        PaymentRequestDTO paymentRequest = new PaymentRequestDTO(null, null, null, 0.0);
        
        // Act & Assert
        assertNull(paymentRequest.getUserId());
        assertNull(paymentRequest.getTransactionId());
        assertNull(paymentRequest.getItems());
    }

    // Helper method to create sample items
    private List<PaymentItemDTO> createSampleItems() {
        // Note: Assuming PaymentItemDTO has appropriate constructor and methods
        PaymentItemDTO item1 = new PaymentItemDTO(); // Replace with actual constructor if needed
        PaymentItemDTO item2 = new PaymentItemDTO();
        
        return Arrays.asList(item1, item2);
    }
}