package com.quest2travels.wpms.entities;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.ManyToAny;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quest2travels.wpms.payload.ClientDto;

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
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="event")
@Data

public class Event {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String name;
@FutureOrPresent(message = "EventRequestDto Date cannot be Past Date...!!!")
@DateTimeFormat(pattern = "dd/MM/yyyy")
private LocalDate eventDate;
@ManyToOne
@JoinColumn(name="client_id")
private Client client;
private boolean completed;
@OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
private List<Booking> bookings;

    public Event() {
    }

    public Event(Long id, String name, LocalDate eventDate, Client client, boolean completed, List<Booking> bookings) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
        this.client = client;
        this.completed = completed;
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

    public @FutureOrPresent(message = "EventRequestDto Date cannot be Past Date...!!!") LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(@FutureOrPresent(message = "EventRequestDto Date cannot be Past Date...!!!") LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", eventDate=" + eventDate + ", client=" + client + ", completed="
				+ completed + ", bookings=" + bookings + "]";
	}
    
}
