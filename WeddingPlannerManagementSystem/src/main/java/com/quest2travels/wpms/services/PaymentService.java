package com.quest2travels.wpms.services;

import java.util.List;

import com.quest2travels.wpms.payload.PaymentRequestDto;
import com.quest2travels.wpms.payload.PaymentResponseDto;

public interface PaymentService {

	PaymentResponseDto recordPayment(PaymentRequestDto requestDto);
	List<PaymentResponseDto> getAllPayments(Boolean completed);
	
}
