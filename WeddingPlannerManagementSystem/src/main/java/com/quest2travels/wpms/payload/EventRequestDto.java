package com.quest2travels.wpms.payload;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public class EventRequestDto {
@NotNull
@NotEmpty
private String name;

@FutureOrPresent(message = "EventRequestDto Date cannot be Past Date...!!!")
private LocalDate eventDate;
@NotNull
@NotEmpty
private Long clientId;
public EventRequestDto() {
	super();
	// TODO Auto-generated constructor stub
}
public EventRequestDto(@NotNull @NotEmpty String name,
		@FutureOrPresent(message = "EventRequestDto Date cannot be Past Date...!!!") LocalDate eventDate,
		@NotNull @NotEmpty Long clientId) {
	super();
	this.name = name;
	this.eventDate = eventDate;
	this.clientId = clientId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public LocalDate getEventDate() {
	return eventDate;
}
public void setEventDate(LocalDate eventDate) {
	this.eventDate = eventDate;
}
public Long getClientId() {
	return clientId;
}
public void setClientId(Long clientId) {
	this.clientId = clientId;
}


}
