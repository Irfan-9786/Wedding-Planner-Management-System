package com.quest2travels.wpms.payload;

import java.util.List;

import lombok.Data;


public class VendorResponseDto {
private Long id;
private String name;
private String contactInfo;
private Boolean available;
private String serviceType;
private List<BookingResponseDto> bookingResponseDtos;
public VendorResponseDto() {
	super();
	// TODO Auto-generated constructor stub
}
public VendorResponseDto(Long id, String name, String contactInfo, Boolean available, String serviceType,
		List<BookingResponseDto> bookingResponseDtos) {
	super();
	this.id = id;
	this.name = name;
	this.contactInfo = contactInfo;
	this.available = available;
	this.serviceType = serviceType;
	this.bookingResponseDtos = bookingResponseDtos;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getContactInfo() {
	return contactInfo;
}
public void setContactInfo(String contactInfo) {
	this.contactInfo = contactInfo;
}
public Boolean getAvailable() {
	return available;
}
public void setAvailable(Boolean available) {
	this.available = available;
}
public String getServiceType() {
	return serviceType;
}
public void setServiceType(String serviceType) {
	this.serviceType = serviceType;
}
public List<BookingResponseDto> getBookingResponseDtos() {
	return bookingResponseDtos;
}
public void setBookingResponseDtos(List<BookingResponseDto> bookingResponseDtos) {
	this.bookingResponseDtos = bookingResponseDtos;
}
@Override
public String toString() {
	return "VendorResponseDto [id=" + id + ", name=" + name + ", contactInfo=" + contactInfo + ", available="
			+ available + ", serviceType=" + serviceType + ", bookingResponseDtos=" + bookingResponseDtos + "]";
}


}
