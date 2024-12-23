package com.quest2travels.wpms.entities;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="client")
@Data

public class Client {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
private String contact;
private String email;
private LocalDate weddingDate;
private Double budget; 
@OneToMany(mappedBy = "client",cascade=CascadeType.ALL)
@JsonIgnoreProperties
private List<Event> events;

    public Client() {
    }

    public Client(Long id, String name, String contact, String email, LocalDate weddingDate, Double budget, List<Event> events) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.weddingDate = weddingDate;
        this.budget = budget;
        this.events = events;
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", contact=" + contact + ", email=" + email + ", weddingDate="
				+ weddingDate + ", budget=" + budget + ", events=" + events + "]";
	}
    
}
