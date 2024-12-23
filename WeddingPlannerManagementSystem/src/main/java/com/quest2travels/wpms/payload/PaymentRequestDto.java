package com.quest2travels.wpms.payload;

import java.time.LocalDate;

import com.quest2travels.wpms.entities.Client;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

public class PaymentRequestDto {
	private Long clientId;
	private Double amount;
	private boolean completed;
	private LocalDate paymentDate;
	public PaymentRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentRequestDto(Long clientId, Double amount, boolean completed, LocalDate paymentDate) {
		super();
		this.clientId = clientId;
		this.amount = amount;
		this.completed = completed;
		this.paymentDate = paymentDate;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
		return "PaymentRequestDto [clientId=" + clientId + ", amount=" + amount + ", completed=" + completed
				+ ", paymentDate=" + paymentDate + "]";
	}
	
}
