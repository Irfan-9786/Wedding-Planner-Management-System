package com.quest2travels.wpms.payload;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.quest2travels.wpms.entities.Booking;
import com.quest2travels.wpms.entities.Client;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

public class EventResponseDto {
	private Long id;
	private String name;
	private LocalDate eventDate;
	private ClientDto clientDto;
	private boolean completed;
	public EventResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventResponseDto(Long id, String name, LocalDate eventDate, ClientDto clientDto, boolean completed) {
		super();
		this.id = id;
		this.name = name;
		this.eventDate = eventDate;
		this.clientDto = clientDto;
		this.completed = completed;
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
	public LocalDate getEventDate() {
		return eventDate;
	}
	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}
	public ClientDto getClientDto() {
		return clientDto;
	}
	public void setClientDto(ClientDto clientDto) {
		this.clientDto = clientDto;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	@Override
	public String toString() {
		return "EventResponseDto [id=" + id + ", name=" + name + ", eventDate=" + eventDate + ", clientDto=" + clientDto
				+ ", completed=" + completed + "]";
	}

	
}
