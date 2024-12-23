package com.quest2travels.wpms.payload;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

public class BookingRequestDto {
@NotNull
@NotEmpty
private Long eventId;
@NotNull
@NotEmpty
private Long vendorId;
@NotNull 
@NotEmpty
private Double price;
public BookingRequestDto() {
	super();
	// TODO Auto-generated constructor stub
}
public BookingRequestDto(@NotNull @NotEmpty Long eventId, @NotNull @NotEmpty Long vendorId,
		@NotNull @NotEmpty Double price) {
	super();
	this.eventId = eventId;
	this.vendorId = vendorId;
	this.price = price;
}
public Long getEventId() {
	return eventId;
}
public void setEventId(Long eventId) {
	this.eventId = eventId;
}
public Long getVendorId() {
	return vendorId;
}
public void setVendorId(Long vendorId) {
	this.vendorId = vendorId;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
@Override
public String toString() {
	return "BookingRequestDto [eventId=" + eventId + ", vendorId=" + vendorId + ", price=" + price + "]";
}


}
