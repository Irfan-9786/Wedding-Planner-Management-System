package com.quest2travels.wpms.payload;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;


public class ClientDto {
@NotNull
@NotEmpty
private String name;
@NotNull
@NotEmpty
private String contact;
@NotNull
@Email
private String email;
@NotNull
@FutureOrPresent(message = "Wedding Date cannot be Past Date...!!!")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
private LocalDate weddingDate;
@NotNull
private Double budget;
public ClientDto() {
	super();
	// TODO Auto-generated constructor stub
}
public ClientDto(@NotNull @NotEmpty String name, @NotNull @NotEmpty String contact, @NotNull @Email String email,
		@NotNull @FutureOrPresent(message = "Wedding Date cannot be Past Date...!!!") LocalDate weddingDate,
		@NotNull Double budget) {
	super();
	this.name = name;
	this.contact = contact;
	this.email = email;
	this.weddingDate = weddingDate;
	this.budget = budget;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public LocalDate getWeddingDate() {
	return weddingDate;
}
public void setWeddingDate(LocalDate weddingDate) {
	this.weddingDate = weddingDate;
}
public Double getBudget() {
	return budget;
}
public void setBudget(Double budget) {
	this.budget = budget;
}
@Override
public String toString() {
	return "ClientDto [name=" + name + ", contact=" + contact + ", email=" + email + ", weddingDate=" + weddingDate
			+ ", budget=" + budget + "]";
} 

}
