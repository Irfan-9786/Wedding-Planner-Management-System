package com.quest2travels.wpms.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest2travels.wpms.entities.Booking;
import com.quest2travels.wpms.entities.Event;
import com.quest2travels.wpms.entities.Vendor;
import com.quest2travels.wpms.exceptions.ResourceNotFoundException;
import com.quest2travels.wpms.payload.BookingRequestDto;
import com.quest2travels.wpms.payload.BookingResponseDto;
import com.quest2travels.wpms.repositories.BookingRepo;
import com.quest2travels.wpms.repositories.EventRepo;
import com.quest2travels.wpms.repositories.VendorRepo;
import com.quest2travels.wpms.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepo bookingRepo;

	@Autowired
	private EventRepo eventRepo;

	@Autowired
	private VendorRepo vendorRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public BookingResponseDto createBooking(BookingRequestDto bookingRequestDTO) {
        // Fetch event
        Event event = eventRepo.findById(bookingRequestDTO.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + bookingRequestDTO.getEventId()));

        // Validate event date
        if (event.getEventDate().isBefore(java.time.LocalDate.now())) {
            throw new RuntimeException("Cannot book a vendor for an event in the past.");
        }

        // Fetch vendor
        Vendor vendor = vendorRepo.findById(bookingRequestDTO.getVendorId())
                .orElseThrow(() -> new RuntimeException("Vendor not found with id " + bookingRequestDTO.getVendorId()));

        // Check vendor availability
        if (!vendor.getAvailable()) {
            throw new RuntimeException("Vendor is not available for booking.");
        }

        // Check budget
        double totalBookedAmount = event.getBookings().stream()
                .mapToDouble(Booking::getPrice)
                .sum();
        if (totalBookedAmount + bookingRequestDTO.getPrice() > event.getClient().getBudget()) {
            throw new RuntimeException("Client's budget exceeded.");
        }

        // Create and save booking
        Booking booking = new Booking();
        booking.setEvent(event);
        booking.setVendor(vendor);
        booking.setPrice(bookingRequestDTO.getPrice());
        booking.setActive(true);
        booking = bookingRepo.save(booking);
        
        // Map to response DTO
        return this.modelMapper.map(booking,BookingResponseDto.class);
	
	}

	@Override
	public BookingResponseDto getBookingById(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));

        return this.modelMapper.map(booking,BookingResponseDto.class);
	}

	@Override
	public List<BookingResponseDto> getBookingsByEventId(Long eventId) {
        List<Booking> bookings = bookingRepo.findByEventId(eventId);
        return bookings.stream().map(booking->this.modelMapper.map(booking, BookingResponseDto.class)).collect(Collectors.toList());

	}

	@Override
	public List<BookingResponseDto> getAllBookings() {
		 List<Booking> bookings = bookingRepo.findAll();
	 return bookings.stream().map(booking->this.modelMapper.map(booking, BookingResponseDto.class)).collect(Collectors.toList());
	}

	@Override
	public BookingResponseDto cancelBooking(Long id) {
        Booking booking = bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id " + id));
        booking.setActive(false); // Mark booking as inactive
        bookingRepo.save(booking);
        return this.modelMapper.map(booking,BookingResponseDto.class);
    	}

}
