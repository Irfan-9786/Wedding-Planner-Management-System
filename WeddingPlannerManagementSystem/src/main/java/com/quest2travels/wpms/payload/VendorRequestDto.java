package com.quest2travels.wpms.payload;

import java.util.List;

import jakarta.persistence.CascadeType;
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


public class VendorRequestDto {

private String name;
private String contactInfo;
private String serviceType;
private Boolean available;
public VendorRequestDto() {
	super();
	// TODO Auto-generated constructor stub
}
public VendorRequestDto(String name, String contactInfo, String serviceType, Boolean available) {
	super();
	this.name = name;
	this.contactInfo = contactInfo;
	this.serviceType = serviceType;
	this.available = available;
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
@Override
public String toString() {
	return "VendorRequestDto [name=" + name + ", contactInfo=" + contactInfo + ", serviceType=" + serviceType
			+ ", available=" + available + "]";
}

}
