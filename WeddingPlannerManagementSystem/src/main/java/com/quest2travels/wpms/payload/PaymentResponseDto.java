package com.quest2travels.wpms.payload;

import java.time.LocalDate;

import com.quest2travels.wpms.entities.Client;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

public class PaymentResponseDto {
	private Long id;
	private Client client;
	private Double amount;
	private boolean completed;
	private String clientName;
	public PaymentResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentResponseDto(Long id, Client client, Double amount, boolean completed, String clientName) {
		super();
		this.id = id;
		this.client = client;
		this.amount = amount;
		this.completed = completed;
		this.clientName = clientName;
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
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	@Override
	public String toString() {
		return "PaymentResponseDto [id=" + id + ", client=" + client + ", amount=" + amount + ", completed=" + completed
				+ ", clientName=" + clientName + "]";
	}
	
}
