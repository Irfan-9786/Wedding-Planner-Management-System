package com.quest2travels.wpms.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendor")
@Data

public class Vendor {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private Long id;
@Column(nullable = false,unique = true)
private String name;
private String contactInfo;
@Column(nullable = false)
private String serviceType;
private Boolean available;
@OneToMany(mappedBy = "vendor",cascade = CascadeType.ALL)
private List<Booking> bookings;

    public Vendor() {
    }

    public Vendor(Long id, String name, String contactInfo, String serviceType, Boolean available, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.serviceType = serviceType;
        this.available = available;
        this.bookings = bookings;
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

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + ", contactInfo=" + contactInfo + ", serviceType=" + serviceType
				+ ", available=" + available + ", bookings=" + bookings + "]";
	}
    
}
