package com.quest2travels.wpms.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Payment {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private Long id;
@ManyToOne
@JoinColumn(name = "client_id",nullable = false)
private Client client;
@Column(nullable = false)
private Double amount;
@Column(nullable = false)
private boolean completed;
@Column(nullable = false)
private LocalDate paymentDate;

    public Payment() {
    }

    public Payment(Long id, Client client, Double amount, boolean completed, LocalDate paymentDate) {
        this.id = id;
        this.client = client;
        this.amount = amount;
        this.completed = completed;
        this.paymentDate = paymentDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

	@Override
	public String toString() {
		return "Payment [id=" + id + ", client=" + client + ", amount=" + amount + ", completed=" + completed
				+ ", paymentDate=" + paymentDate + "]";
	}
    
}
