package com.quest2travels.wpms.services;

import java.time.LocalDate;
import java.util.List;

import com.quest2travels.wpms.entities.Vendor;
import com.quest2travels.wpms.payload.VendorRequestDto;
import com.quest2travels.wpms.payload.VendorResponseDto;

public interface VendorService {

public VendorResponseDto registerVendor(VendorRequestDto vendorRequestDto);
public List<VendorResponseDto> findVendorByServiceTypeAndDate(String serviceType,LocalDate date);
public VendorResponseDto updateVendorAvailability(Long vendorId,Boolean available);

}
