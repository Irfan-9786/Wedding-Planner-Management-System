package com.quest2travels.wpms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quest2travels.wpms.payload.BookingRequestDto;
import com.quest2travels.wpms.payload.BookingResponseDto;
import com.quest2travels.wpms.services.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody @Valid BookingRequestDto bookingRequestDTO) {
        BookingResponseDto booking = bookingService.createBooking(bookingRequestDTO);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBookingById(@PathVariable Long id) {
        BookingResponseDto booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<BookingResponseDto>> getBookingsByEvent(@PathVariable Long eventId) {
        List<BookingResponseDto> bookings = bookingService.getBookingsByEventId(eventId);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAllBookings() {
        List<BookingResponseDto> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingResponseDto> cancelBooking(@PathVariable Long id) {
        BookingResponseDto booking = bookingService.cancelBooking(id);
        return ResponseEntity.ok(booking);
    }
}