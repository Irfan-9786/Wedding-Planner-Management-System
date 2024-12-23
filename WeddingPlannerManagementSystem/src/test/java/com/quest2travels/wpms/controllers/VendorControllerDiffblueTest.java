package com.quest2travels.wpms.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quest2travels.wpms.payload.VendorRequestDto;
import com.quest2travels.wpms.payload.VendorResponseDto;
import com.quest2travels.wpms.services.VendorService;

import java.time.LocalDate;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {VendorController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class VendorControllerDiffblueTest {
    @Autowired
    private VendorController vendorController;

    @MockBean
    private VendorService vendorService;

    /**
     * Test
     * {@link VendorController#getVendorByServiceTypeAndDate(String, LocalDate)}.
     * <p>
     * Method under test:
     * {@link VendorController#getVendorByServiceTypeAndDate(String, LocalDate)}
     */
    @Test
    @DisplayName("Test getVendorByServiceTypeAndDate(String, LocalDate)")
    void testGetVendorByServiceTypeAndDate() throws Exception {
        // Arrange
        when(vendorService.findVendorByServiceTypeAndDate(Mockito.<String>any(), Mockito.<LocalDate>any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/vendors/available");
        MockHttpServletRequestBuilder requestBuilder = getResult
                .param("eventDate", String.valueOf(LocalDate.of(1970, 1, 1)))
                .param("serviceType", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link VendorController#registerVendor(VendorRequestDto)}.
     * <p>
     * Method under test: {@link VendorController#registerVendor(VendorRequestDto)}
     */
    @Test
    @DisplayName("Test registerVendor(VendorRequestDto)")
    void testRegisterVendor() throws Exception {
        // Arrange
        when(vendorService.registerVendor(Mockito.<VendorRequestDto>any())).thenReturn(new VendorResponseDto());

        VendorRequestDto vendorRequestDto = new VendorRequestDto();
        vendorRequestDto.setAvailable(true);
        vendorRequestDto.setContactInfo("Contact Info");
        vendorRequestDto.setName("Name");
        vendorRequestDto.setServiceType("Service Type");
        String content = (new ObjectMapper()).writeValueAsString(vendorRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/vendors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"contactInfo\":null,\"available\":null,\"serviceType\":null,\"bookingResponseDtos"
                                        + "\":null}"));
    }

    /**
     * Test {@link VendorController#updateVendorAvailability(Long, boolean)}.
     * <p>
     * Method under test:
     * {@link VendorController#updateVendorAvailability(Long, boolean)}
     */
    @Test
    @DisplayName("Test updateVendorAvailability(Long, boolean)")
    void testUpdateVendorAvailability() throws Exception {
        // Arrange
        when(vendorService.updateVendorAvailability(Mockito.<Long>any(), Mockito.<Boolean>any()))
                .thenReturn(new VendorResponseDto());
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/vendors/{id}/availability", 1L);
        MockHttpServletRequestBuilder requestBuilder = putResult.param("isAvailable", String.valueOf(true));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"name\":null,\"contactInfo\":null,\"available\":null,\"serviceType\":null,\"bookingResponseDtos"
                                        + "\":null}"));
    }
}
