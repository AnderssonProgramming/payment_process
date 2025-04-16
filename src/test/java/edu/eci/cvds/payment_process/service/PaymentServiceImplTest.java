package edu.eci.cvds.payment_process.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import edu.eci.cvds.payment_process.DTO.PaymentItemDTO;
import edu.eci.cvds.payment_process.DTO.PaymentRequestDTO;
import edu.eci.cvds.payment_process.DTO.PaymentResponseDTO;
import edu.eci.cvds.payment_process.model.Payment;
import edu.eci.cvds.payment_process.model.PaymentItem;
import edu.eci.cvds.payment_process.model.PaymentStatus;
import edu.eci.cvds.payment_process.repository.PaymentRepository;



class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @BeforeEach
     void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testProcessPaymentSuccess() {
        // Arrange
        PaymentRequestDTO request = new PaymentRequestDTO();
        request.setUserId("user123");
        request.setTransactionId("tx123");
        
        List<PaymentItemDTO> items = new ArrayList<>();
        items.add(new PaymentItemDTO("Product1", 10.0, 2, "01-01-2023"));
        items.add(new PaymentItemDTO("Product2", 15.0, 1, "02-01-2023"));
        request.setItems(items);

        Payment savedPayment = new Payment();
        savedPayment.setUserId("user123");
        savedPayment.setTransactionId("tx123");
        savedPayment.setTotalAmount(35.0);
        savedPayment.setStatus(PaymentStatus.APPROVED);
        savedPayment.setErrorMessage(null);
        savedPayment.setPaymentDate(new Date());
        
        List<PaymentItem> paymentItems = new ArrayList<>();
        try {
            paymentItems.add(new PaymentItem("Product1", 10.0, 2, dateFormat.parse("01-01-2023")));
            paymentItems.add(new PaymentItem("Product2", 15.0, 1, dateFormat.parse("02-01-2023")));
        } catch (Exception e) {
            fail("Date parsing should not fail in test setup");
        }
        savedPayment.setItems(paymentItems);

        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);

        // Act
        PaymentResponseDTO response = paymentService.processPayment(request);

        // Assert
        assertNotNull(response);
        assertEquals("tx123", response.getTransactionId());
        assertEquals("user123", response.getUserId());
        assertEquals(35.0, response.getTotalAmount(), 0.001);
        assertEquals(PaymentStatus.APPROVED, response.getStatus());
        assertEquals(2, response.getItems().size());
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
     void testProcessPaymentGeneratesTransactionId() {
        // Arrange
        PaymentRequestDTO request = new PaymentRequestDTO();
        request.setUserId("user123");
        // No transaction ID provided
        
        List<PaymentItemDTO> items = new ArrayList<>();
        items.add(new PaymentItemDTO("Product1", 10.0, 2, "01-01-2023"));
        request.setItems(items);

        Payment savedPayment = new Payment();
        savedPayment.setUserId("user123");
        savedPayment.setTransactionId("generated-uuid"); // Mock generated ID
        savedPayment.setTotalAmount(20.0);
        savedPayment.setStatus(PaymentStatus.APPROVED);
        savedPayment.setItems(new ArrayList<>());

        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);

        // Act
        PaymentResponseDTO response = paymentService.processPayment(request);

        // Assert
        assertNotNull(response);
        assertEquals("generated-uuid", response.getTransactionId());
        verify(paymentRepository).save(any(Payment.class));
    }

    @Test
     void testGetPaymentsByUserId() {
        // Arrange
        String userId = "user123";
        
        List<Payment> paymentList = new ArrayList<>();
        Payment payment1 = new Payment();
        payment1.setUserId(userId);
        payment1.setTransactionId("tx1");
        payment1.setTotalAmount(20.0);
        payment1.setStatus(PaymentStatus.APPROVED);
        
        List<PaymentItem> items1 = new ArrayList<>();
        try {
            items1.add(new PaymentItem("Product1", 10.0, 2, dateFormat.parse("01-01-2023")));
        } catch (Exception e) {
            fail("Date parsing should not fail in test setup");
        }
        payment1.setItems(items1);
        paymentList.add(payment1);

        when(paymentRepository.findByUserId(userId)).thenReturn(paymentList);

        // Act
        List<PaymentResponseDTO> responses = paymentService.getPaymentsByUserId(userId);

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(userId, responses.get(0).getUserId());
        assertEquals("tx1", responses.get(0).getTransactionId());
        assertEquals(20.0, responses.get(0).getTotalAmount(), 0.001);
        assertEquals(PaymentStatus.APPROVED, responses.get(0).getStatus());
        assertEquals(1, responses.get(0).getItems().size());
        assertEquals("Product1", responses.get(0).getItems().get(0).getProductName());
        
        verify(paymentRepository).findByUserId(userId);
    }

    @Test
     void testGetPaymentsByUserIdEmpty() {
        // Arrange
        String userId = "nonexistentUser";
        when(paymentRepository.findByUserId(userId)).thenReturn(new ArrayList<>());

        // Act
        List<PaymentResponseDTO> responses = paymentService.getPaymentsByUserId(userId);

        // Assert
        assertNotNull(responses);
        assertTrue(responses.isEmpty());
        verify(paymentRepository).findByUserId(userId);
    }
}