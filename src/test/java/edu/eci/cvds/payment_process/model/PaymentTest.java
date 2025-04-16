package edu.eci.cvds.payment_process.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;





class PaymentTest {

    @Test
    void testEmptyConstructor() {
        Payment payment = new Payment();
        assertNotNull(payment);
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        String userId = "user123";
        String transactionId = "trans456";
        List<PaymentItem> items = new ArrayList<>();
        double totalAmount = 100.50;
        PaymentStatus status = PaymentStatus.APPROVED; // Assuming this is an enum
        String errorMessage = null;
        Date paymentDate = new Date();

        // Act
        Payment payment = new Payment(userId, transactionId, items, totalAmount, status, errorMessage, paymentDate);

        // Assert
        assertEquals(userId, payment.getUserId());
        assertEquals(transactionId, payment.getTransactionId());
        assertEquals(items, payment.getItems());
        assertEquals(totalAmount, payment.getTotalAmount(), 0.001);
        assertEquals(status, payment.getStatus());
        assertEquals(errorMessage, payment.getErrorMessage());
        assertEquals(paymentDate, payment.getPaymentDate());
    }

    @Test
    void testIdGetterAndSetter() {
        Payment payment = new Payment();
        String id = "payment123";
        payment.setId(id);
        assertEquals(id, payment.getId());
    }

    @Test
    void testUserIdGetterAndSetter() {
        Payment payment = new Payment();
        String userId = "user123";
        payment.setUserId(userId);
        assertEquals(userId, payment.getUserId());
    }

    @Test
    void testTransactionIdGetterAndSetter() {
        Payment payment = new Payment();
        String transactionId = "trans456";
        payment.setTransactionId(transactionId);
        assertEquals(transactionId, payment.getTransactionId());
    }

    @Test
    void testItemsGetterAndSetter() {
        Payment payment = new Payment();
        List<PaymentItem> items = new ArrayList<>();
        payment.setItems(items);
        assertEquals(items, payment.getItems());
    }

    @Test
    void testTotalAmountGetterAndSetter() {
        Payment payment = new Payment();
        double totalAmount = 100.50;
        payment.setTotalAmount(totalAmount);
        assertEquals(totalAmount, payment.getTotalAmount(), 0.001);
    }

    @Test
    void testStatusGetterAndSetter() {
        Payment payment = new Payment();
        PaymentStatus status = PaymentStatus.DECLINED; // Assuming this enum exists
        payment.setStatus(status);
        assertEquals(status, payment.getStatus());
    }

    @Test
    void testErrorMessageGetterAndSetter() {
        Payment payment = new Payment();
        String errorMessage = "Payment failed";
        payment.setErrorMessage(errorMessage);
        assertEquals(errorMessage, payment.getErrorMessage());
    }

    @Test
    void testPaymentDateGetterAndSetter() {
        Payment payment = new Payment();
        Date paymentDate = new Date();
        payment.setPaymentDate(paymentDate);
        assertEquals(paymentDate, payment.getPaymentDate());
    }
}