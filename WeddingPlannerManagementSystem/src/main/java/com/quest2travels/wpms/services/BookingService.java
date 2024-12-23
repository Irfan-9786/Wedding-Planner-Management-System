package com.quest2travels.wpms.services;

import java.util.List;

import com.quest2travels.wpms.payload.BookingRequestDto;
import com.quest2travels.wpms.payload.BookingResponseDto;

public interface BookingService {
	   BookingResponseDto createBooking(BookingRequestDto bookingRequestDTO);

	    BookingResponseDto getBookingById(Long id);

	    List<BookingResponseDto> getBookingsByEventId(Long eventId);

	    List<BookingResponseDto> getAllBookings();

	    BookingResponseDto cancelBooking(Long id);
}
