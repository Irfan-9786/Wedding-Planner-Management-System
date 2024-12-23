package com.quest2travels.wpms.controllers;

import static org.mockito.Mockito.when;

import com.quest2travels.wpms.payload.BookingRequestDto;
import com.quest2travels.wpms.payload.BookingResponseDto;
import com.quest2travels.wpms.services.BookingService;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BookingController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class BookingControllerDiffblueTest {
    @Autowired
    private BookingController bookingController;

    @MockBean
    private BookingService bookingService;

    /**
     * Test {@link BookingController#createBooking(BookingRequestDto)}.
     * <p>
     * Method under test: {@link BookingController#createBooking(BookingRequestDto)}
     */
    @Test
    @DisplayName("Test createBooking(BookingRequestDto)")
    @Disabled("TODO: Complete this test")
    void testCreateBooking() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: jakarta.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint 'jakarta.validation.constraints.NotEmpty' validating type 'java.lang.Long'. Check configuration for 'eventId'
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   jakarta.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint 'jakarta.validation.constraints.NotEmpty' validating type 'java.lang.Long'. Check configuration for 'eventId'
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.getExceptionForNullValidator(ConstraintTree.java:116)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.getInitializedConstraintValidator(ConstraintTree.java:162)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.SimpleConstraintTree.validateConstraints(SimpleConstraintTree.java:58)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.validateConstraints(ConstraintTree.java:75)
        //       at org.hibernate.validator.internal.metadata.core.MetaConstraint.doValidateConstraint(MetaConstraint.java:130)
        //       at org.hibernate.validator.internal.metadata.core.MetaConstraint.validateConstraint(MetaConstraint.java:123)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateMetaConstraint(ValidatorImpl.java:555)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForSingleDefaultGroupElement(ValidatorImpl.java:518)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForDefaultGroup(ValidatorImpl.java:488)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForCurrentGroup(ValidatorImpl.java:450)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateInContext(ValidatorImpl.java:400)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validate(ValidatorImpl.java:172)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        BookingController bookingController = new BookingController();

        // Act
        bookingController.createBooking(new BookingRequestDto());
    }

    /**
     * Test {@link BookingController#getBookingById(Long)}.
     * <p>
     * Method under test: {@link BookingController#getBookingById(Long)}
     */
    @Test
    @DisplayName("Test getBookingById(Long)")
    void testGetBookingById() throws Exception {
        // Arrange
        when(bookingService.getBookingById(Mockito.<Long>any())).thenReturn(new BookingResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bookings/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"vendorId\":null,\"eventId\":null,\"price\":null,\"active\":null}"));
    }

    /**
     * Test {@link BookingController#getBookingsByEvent(Long)}.
     * <p>
     * Method under test: {@link BookingController#getBookingsByEvent(Long)}
     */
    @Test
    @DisplayName("Test getBookingsByEvent(Long)")
    void testGetBookingsByEvent() throws Exception {
        // Arrange
        when(bookingService.getBookingsByEventId(Mockito.<Long>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bookings/event/{eventId}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link BookingController#getAllBookings()}.
     * <p>
     * Method under test: {@link BookingController#getAllBookings()}
     */
    @Test
    @DisplayName("Test getAllBookings()")
    void testGetAllBookings() throws Exception {
        // Arrange
        when(bookingService.getAllBookings()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/bookings");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Test {@link BookingController#cancelBooking(Long)}.
     * <p>
     * Method under test: {@link BookingController#cancelBooking(Long)}
     */
    @Test
    @DisplayName("Test cancelBooking(Long)")
    void testCancelBooking() throws Exception {
        // Arrange
        when(bookingService.cancelBooking(Mockito.<Long>any())).thenReturn(new BookingResponseDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/bookings/{id}/cancel", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(bookingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"vendorId\":null,\"eventId\":null,\"price\":null,\"active\":null}"));
    }
}
