package edu.eci.cvds.payment_process.controller;

import edu.eci.cvds.payment_process.DTO.PaymentRequestDTO;
import edu.eci.cvds.payment_process.DTO.PaymentResponseDTO;
import edu.eci.cvds.payment_process.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;






@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @SuppressWarnings("unused")
    @Test
    void createPayment_ShouldReturnCreatedStatus() throws Exception {
        // Arrange
        PaymentRequestDTO requestDTO = new PaymentRequestDTO();
        PaymentResponseDTO responseDTO = new PaymentResponseDTO();
        
        when(paymentService.processPayment(any(PaymentRequestDTO.class))).thenReturn(responseDTO);

        // Act & Assert
        mockMvc.perform(post("/api/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getPayments_ShouldReturnPaymentsList() throws Exception {
        // Arrange
        String userId = "user123";
        List<PaymentResponseDTO> payments = Arrays.asList(new PaymentResponseDTO(), new PaymentResponseDTO());
        
        when(paymentService.getPaymentsByUserId(anyString())).thenReturn(payments);

        // Act & Assert
        mockMvc.perform(get("/api/payments/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}