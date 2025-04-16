package edu.eci.cvds.payment_process.service;

import org.springframework.stereotype.Service;

import edu.eci.cvds.payment_process.DTO.PaymentItemDTO;
import edu.eci.cvds.payment_process.DTO.PaymentRequestDTO;
import edu.eci.cvds.payment_process.DTO.PaymentResponseDTO;
import edu.eci.cvds.payment_process.model.Payment;
import edu.eci.cvds.payment_process.model.PaymentException;
import edu.eci.cvds.payment_process.model.PaymentItem;
import edu.eci.cvds.payment_process.model.PaymentStatus;
import edu.eci.cvds.payment_process.repository.PaymentRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {

        List<PaymentItem> paymentItems = new ArrayList<>();
        double calculatedTotal = 0.0;

        for (PaymentItemDTO itemDTO : request.getItems()) {
            // Parse the date (DD-MM-YYYY)
            Date purchaseDate;
            try {
                purchaseDate = dateFormat.parse(itemDTO.getPurchaseDate());
            } catch (ParseException e) {
                throw new PaymentException("Invalid date format for item: " 
                                        + itemDTO.getProductName() 
                                        + ". Expected: DD-MM-YYYY");
            }

            // Create PaymentItem
            PaymentItem item = new PaymentItem(
                itemDTO.getProductName(),
                itemDTO.getUnitPrice(),
                itemDTO.getQuantity(),
                purchaseDate
            );
            paymentItems.add(item);

            // Calculate the total on the fly
            calculatedTotal += (itemDTO.getUnitPrice() * itemDTO.getQuantity());
        }

        // Instead of validating a passed total, we just use calculatedTotal.
        String transactionId = (request.getTransactionId() != null && !request.getTransactionId().isEmpty())
                            ? request.getTransactionId()
                            : UUID.randomUUID().toString();

        Payment payment = new Payment();
        payment.setUserId(request.getUserId());
        payment.setTransactionId(transactionId);
        payment.setItems(paymentItems);
        payment.setTotalAmount(calculatedTotal);
        payment.setPaymentDate(new Date());
        payment.setStatus(PaymentStatus.APPROVED);
        payment.setErrorMessage(null);

        Payment savedPayment = paymentRepository.save(payment);

        // Build a Response DTO
        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setTransactionId(savedPayment.getTransactionId());
        response.setUserId(savedPayment.getUserId());
        response.setTotalAmount(savedPayment.getTotalAmount());
        response.setStatus(savedPayment.getStatus());
        response.setErrorMessage(savedPayment.getErrorMessage());

        // Convert PaymentItems back to DTO
        List<PaymentItemDTO> itemDTOs = new ArrayList<>();
        for (PaymentItem pi : savedPayment.getItems()) {
            String formattedDate = dateFormat.format(pi.getPurchaseDate());
            PaymentItemDTO pidto = new PaymentItemDTO(
                pi.getProductName(),
                pi.getUnitPrice(),
                pi.getQuantity(),
                formattedDate
            );
            itemDTOs.add(pidto);
        }
        response.setItems(itemDTOs);

        return response;
    }

    @Override
    public List<PaymentResponseDTO> getPaymentsByUserId(String userId) {
        List<Payment> payments = paymentRepository.findByUserId(userId);
        List<PaymentResponseDTO> responses = new ArrayList<>();

        for (Payment payment : payments) {
            PaymentResponseDTO response = new PaymentResponseDTO();
            response.setTransactionId(payment.getTransactionId());
            response.setUserId(payment.getUserId());
            response.setTotalAmount(payment.getTotalAmount());
            response.setStatus(payment.getStatus());
            response.setErrorMessage(payment.getErrorMessage());

            List<PaymentItemDTO> itemDTOs = new ArrayList<>();
            for (PaymentItem item : payment.getItems()) {
                String formattedDate = dateFormat.format(item.getPurchaseDate());
                PaymentItemDTO dto = new PaymentItemDTO(item.getProductName(), item.getUnitPrice(),
                                                        item.getQuantity(), formattedDate);
                itemDTOs.add(dto);
            }
            response.setItems(itemDTOs);
            responses.add(response);
        }
        return responses;
    }
}