package com.quest2travels.wpms.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quest2travels.wpms.payload.PaymentRequestDto;
import com.quest2travels.wpms.payload.PaymentResponseDto;
import com.quest2travels.wpms.services.PaymentService;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PaymentController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PaymentControllerDiffblueTest {
    @Autowired
    private PaymentController paymentController;

    @MockBean
    private PaymentService paymentService;

    /**
     * Test {@link PaymentController#recordPayment(PaymentRequestDto)}.
     * <ul>
     *   <li>Then status {@link StatusResultMatchers#isCreated()}.</li>
     * </ul>
     * <p>
     * Method under test: {@link PaymentController#recordPayment(PaymentRequestDto)}
     */
    @Test
    @DisplayName("Test recordPayment(PaymentRequestDto); then status isCreated()")
    void testRecordPayment_thenStatusIsCreated() throws Exception {
        // Arrange
        when(paymentService.recordPayment(Mockito.<PaymentRequestDto>any())).thenReturn(new PaymentResponseDto());

        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setAmount(10.0d);
        paymentRequestDto.setClientId(1L);
        paymentRequestDto.setCompleted(true);
        paymentRequestDto.setPaymentDate(null);
        String content = (new ObjectMapper()).writeValueAsString(paymentRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/payments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"client\":null,\"amount\":null,\"completed\":false,\"clientName\":null}"));
    }

    /**
     * Test {@link PaymentController#getAllPayments(Boolean)}.
     * <p>
     * Method under test: {@link PaymentController#getAllPayments(Boolean)}
     */
    @Test
    @DisplayName("Test getAllPayments(Boolean)")
    void testGetAllPayments() throws Exception {
        // Arrange
        when(paymentService.getAllPayments(Mockito.<Boolean>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payments");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
