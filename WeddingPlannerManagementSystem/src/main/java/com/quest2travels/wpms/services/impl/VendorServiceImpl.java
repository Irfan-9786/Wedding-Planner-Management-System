package com.quest2travels.wpms.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest2travels.wpms.entities.Vendor;
import com.quest2travels.wpms.exceptions.ResourceNotFoundException;
import com.quest2travels.wpms.payload.BookingResponseDto;
import com.quest2travels.wpms.payload.VendorRequestDto;
import com.quest2travels.wpms.payload.VendorResponseDto;
import com.quest2travels.wpms.repositories.VendorRepo;
import com.quest2travels.wpms.services.VendorService;

@Service
public class VendorServiceImpl implements VendorService {
	@Autowired
	private VendorRepo vendorRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public VendorResponseDto registerVendor(VendorRequestDto vendorRequestDto) {
		Vendor vendor = this.modelMapper.map(vendorRequestDto, Vendor.class);
		Vendor vendor2 = vendorRepo.save(vendor);
		VendorResponseDto vendorResponseDto2=this.modelMapper.map(vendor2, VendorResponseDto.class);
		return vendorResponseDto2;
	}

	@Override
	public VendorResponseDto updateVendorAvailability(Long vendorId, Boolean available) {
		Vendor vendor = vendorRepo.findById(vendorId).orElseThrow(() -> new ResourceNotFoundException(
				"VendorRequestDto with ID=" + vendorId + " doesn't exist in database....!!!"));
		vendor.setAvailable(available);
		Vendor vendor2 = vendorRepo.save(vendor);
		return this.modelMapper.map(vendor2, VendorResponseDto.class);
	}

	@Override
	public List<VendorResponseDto> findVendorByServiceTypeAndDate(String serviceType, LocalDate date) {
		return vendorRepo.findVendorsByServiceTypeAndDate(serviceType, date).stream().map(this::convertToVendorResponseDTO).collect(Collectors.toList());

	}

	/**
     * Helper method to convert Vendor to VendorResponseDTO
     * Includes mapping nested bookings using ModelMapper.
     */
    private VendorResponseDto convertToVendorResponseDTO(Vendor vendor) {
        VendorResponseDto vendorResponseDTO = modelMapper.map(vendor, VendorResponseDto.class);

        // Map nested bookings
        List<BookingResponseDto> bookingDTOList = vendor.getBookings().stream()
            .map(booking -> modelMapper.map(booking, BookingResponseDto.class))
            .collect(Collectors.toList());
        vendorResponseDTO.setBookingResponseDtos(bookingDTOList);

        return vendorResponseDTO;
    }
}
