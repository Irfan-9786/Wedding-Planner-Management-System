package com.quest2travels.wpms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="booking")


public class Booking {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@ManyToOne
@JoinColumn(name="event_id")
private Event event;
@ManyToOne
@JoinColumn(name ="vendor_id")
private Vendor vendor;
private Boolean active;
private Double price;

public Booking(Long id, Event event, Vendor vendor, Boolean active, Double price) {
	super();
	this.id = id;
	this.event = event;
	this.vendor = vendor;
	this.active = active;
	this.price = price;
}

public Booking() {
	super();
	// TODO Auto-generated constructor stub
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Event getEvent() {
	return event;
}

public void setEvent(Event event) {
	this.event = event;
}

public Vendor getVendor() {
	return vendor;
}

public void setVendor(Vendor vendor) {
	this.vendor = vendor;
}

public Boolean getActive() {
	return active;
}

public void setActive(Boolean active) {
	this.active = active;
}

public Double getPrice() {
	return price;
}

public void setPrice(Double price) {
	this.price = price;
}

@Override
public String toString() {
	return "Booking [id=" + id + ", event=" + event + ", vendor=" + vendor + ", active=" + active + ", price=" + price
			+ "]";
}

}
