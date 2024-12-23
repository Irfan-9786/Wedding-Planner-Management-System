package com.quest2travels.wpms.payload;

import lombok.Data;


public class BookingResponseDto {
private Long id;
private Long vendorId;
private Long eventId;
private Double price;
private Boolean active;
public BookingResponseDto() {
	super();
	// TODO Auto-generated constructor stub
}
public BookingResponseDto(Long id, Long vendorId, Long eventId, Double price, Boolean active) {
	super();
	this.id = id;
	this.vendorId = vendorId;
	this.eventId = eventId;
	this.price = price;
	this.active = active;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getVendorId() {
	return vendorId;
}
public void setVendorId(Long vendorId) {
	this.vendorId = vendorId;
}
public Long getEventId() {
	return eventId;
}
public void setEventId(Long eventId) {
	this.eventId = eventId;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public Boolean getActive() {
	return active;
}
public void setActive(Boolean active) {
	this.active = active;
}
@Override
public String toString() {
	return "BookingResponseDto [id=" + id + ", vendorId=" + vendorId + ", eventId=" + eventId + ", price=" + price
			+ ", active=" + active + "]";
}

}
