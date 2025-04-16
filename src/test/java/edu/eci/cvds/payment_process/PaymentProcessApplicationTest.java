package edu.eci.cvds.payment_process;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
class PaymentProcessApplicationTest {

    @Test
    void mainMethodExecutesWithoutException() {
        // This test verifies that the main method can be executed without throwing exceptions
        assertDoesNotThrow(() -> PaymentProcessApplication.main(new String[]{}));
    }

    @Test
    void mainMethodWithArgumentsExecutesWithoutException() {
        // This test verifies that the main method handles command line arguments properly
        assertDoesNotThrow(() -> PaymentProcessApplication.main(new String[]{"--server.port=8081"}));
    }

    @Test
    void mainMethodPassesCorrectClassToSpringApplication() {
        // This test requires a mock to verify SpringApplication.run is called with correct parameters
        try (MockedStatic<SpringApplication> mockedSpringApplication = Mockito.mockStatic(SpringApplication.class)) {
            PaymentProcessApplication.main(new String[]{});
            mockedSpringApplication.verify(() -> 
                SpringApplication.run(PaymentProcessApplication.class, new String[]{}));
        }
    }
}