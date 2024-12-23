package com.quest2travels.wpms.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quest2travels.wpms.entities.Vendor;
import com.quest2travels.wpms.payload.VendorRequestDto;
import com.quest2travels.wpms.payload.VendorResponseDto;
import com.quest2travels.wpms.services.VendorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@PostMapping
	public ResponseEntity<VendorResponseDto> registerVendor(@RequestBody @Valid VendorRequestDto vendorRequestDto) {
		VendorResponseDto vendorDto2 = vendorService.registerVendor(vendorRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(vendorDto2);
	}

	@PutMapping("/{id}/availability")
	public ResponseEntity<VendorResponseDto> updateVendorAvailability(@PathVariable("id") Long id,
			@RequestParam("isAvailable")@Valid boolean isAvailable) {
		VendorResponseDto vendorResponseDto = vendorService.updateVendorAvailability(id, isAvailable);
		return ResponseEntity.ok(vendorResponseDto);
	}

	@GetMapping("/available")
	public ResponseEntity<List<VendorResponseDto>> getVendorByServiceTypeAndDate(@RequestParam("serviceType") String serviceType,
			@RequestParam("eventDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate) {
		List<VendorResponseDto> vendorResponseDtos = vendorService.findVendorByServiceTypeAndDate(serviceType, eventDate);
		return ResponseEntity.ok(vendorResponseDtos);
	}

}
