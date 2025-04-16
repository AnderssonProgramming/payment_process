package edu.eci.cvds.payment_process.DTO;


import edu.eci.cvds.payment_process.model.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;


class PaymentResponseDTOTest {

    @Test
     void testDefaultConstructor() {
        PaymentResponseDTO response = new PaymentResponseDTO();
        assertNull(response.getTransactionId());
        assertNull(response.getUserId());
        assertEquals(0.0, response.getTotalAmount(), 0.001);
        assertNull(response.getStatus());
        assertNull(response.getErrorMessage());
        assertNull(response.getItems());
    }

    @Test
     void testParameterizedConstructor() {
        String transactionId = "tx123";
        String userId = "user456";
        double totalAmount = 150.75;
        PaymentStatus status = PaymentStatus.APPROVED;
        String errorMessage = "No error";
        List<PaymentItemDTO> items = Arrays.asList(new PaymentItemDTO());

        PaymentResponseDTO response = new PaymentResponseDTO(transactionId, userId, totalAmount, 
                                                           status, errorMessage, items);
        
        assertEquals(transactionId, response.getTransactionId());
        assertEquals(userId, response.getUserId());
        assertEquals(totalAmount, response.getTotalAmount(), 0.001);
        assertEquals(status, response.getStatus());
        assertEquals(errorMessage, response.getErrorMessage());
        assertEquals(items, response.getItems());
    }

    @Test
     void testSettersAndGetters() {
        PaymentResponseDTO response = new PaymentResponseDTO();
        
        String transactionId = "tx789";
        response.setTransactionId(transactionId);
        assertEquals(transactionId, response.getTransactionId());
        
        String userId = "user321";
        response.setUserId(userId);
        assertEquals(userId, response.getUserId());
        
        double totalAmount = 299.99;
        response.setTotalAmount(totalAmount);
        assertEquals(totalAmount, response.getTotalAmount(), 0.001);
        
        PaymentStatus status = PaymentStatus.DECLINED;
        response.setStatus(status);
        assertEquals(status, response.getStatus());
        
        String errorMessage = "Processing payment";
        response.setErrorMessage(errorMessage);
        assertEquals(errorMessage, response.getErrorMessage());
        
        List<PaymentItemDTO> items = new ArrayList<>();
        items.add(new PaymentItemDTO());
        response.setItems(items);
        assertEquals(items, response.getItems());
    }
}