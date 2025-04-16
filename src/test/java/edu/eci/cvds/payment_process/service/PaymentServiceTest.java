package edu.eci.cvds.payment_process.service;


import edu.eci.cvds.payment_process.DTO.PaymentRequestDTO;
import edu.eci.cvds.payment_process.DTO.PaymentResponseDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


 class PaymentServiceTest {

    @Mock
    private PaymentService paymentService;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testProcessPayment_Success() {
        // Arrange
        PaymentRequestDTO request = new PaymentRequestDTO();
        PaymentResponseDTO expectedResponse = new PaymentResponseDTO();
        when(paymentService.processPayment(request)).thenReturn(expectedResponse);

        // Act
        PaymentResponseDTO actualResponse = paymentService.processPayment(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(paymentService).processPayment(request);
    }

    @Test
     void testGetPaymentsByUserId_WithPayments() {
        // Arrange
        String userId = "user123";
        List<PaymentResponseDTO> expectedPayments = Arrays.asList(
                new PaymentResponseDTO(),
                new PaymentResponseDTO()
        );
        when(paymentService.getPaymentsByUserId(userId)).thenReturn(expectedPayments);

        // Act
        List<PaymentResponseDTO> actualPayments = paymentService.getPaymentsByUserId(userId);

        // Assert
        assertNotNull(actualPayments);
        assertEquals(2, actualPayments.size());
        assertEquals( expectedPayments, actualPayments);
        verify(paymentService).getPaymentsByUserId(userId);
    }

    @Test
     void testGetPaymentsByUserId_NoPayments() {
        // Arrange
        String userId = "userWithNoPayments";
        when(paymentService.getPaymentsByUserId(userId)).thenReturn(Collections.emptyList());

        // Act
        List<PaymentResponseDTO> actualPayments = paymentService.getPaymentsByUserId(userId);

        // Assert
        assertNotNull(actualPayments);
        assertTrue(actualPayments.isEmpty());
        verify(paymentService).getPaymentsByUserId(userId);
    }
}