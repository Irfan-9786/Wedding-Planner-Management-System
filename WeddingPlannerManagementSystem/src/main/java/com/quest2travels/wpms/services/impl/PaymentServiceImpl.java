package com.quest2travels.wpms.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest2travels.wpms.entities.Client;
import com.quest2travels.wpms.entities.Payment;
import com.quest2travels.wpms.exceptions.ResourceNotFoundException;
import com.quest2travels.wpms.payload.PaymentRequestDto;
import com.quest2travels.wpms.payload.PaymentResponseDto;
import com.quest2travels.wpms.repositories.ClientRepo;
import com.quest2travels.wpms.repositories.PaymentRepo;
import com.quest2travels.wpms.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{
@Autowired
	private PaymentRepo paymentRepo;
@Autowired    
private  ClientRepo clientRepo;
@Autowired    
private  ModelMapper modelMapper;

    

    @Override
    public PaymentResponseDto recordPayment(PaymentRequestDto requestDTO) {
    	Client client=clientRepo.findById(requestDTO.getClientId()).orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + requestDTO.getClientId()));


        double totalPayments = paymentRepo.findAll().stream()
                .filter(payment -> payment.getClient().getId().equals(client.getId()))
                .mapToDouble(Payment::getAmount)
                .sum();

        if (totalPayments + requestDTO.getAmount() > client.getBudget()) {
            throw new RuntimeException("Payment amount exceeds the remaining budget of the client");
        }

        Payment payment = modelMapper.map(requestDTO, Payment.class);
        payment.setClient(client);
        Payment savedPayment = paymentRepo.save(payment);
        return modelMapper.map(savedPayment, PaymentResponseDto.class);
    }

    @Override
    public List<PaymentResponseDto> getAllPayments(Boolean completed) {
        List<Payment> payments = (completed == null) 
            ? paymentRepo.findAll() 
            : paymentRepo.findByCompleted(completed);

        return payments.stream()
                .map(payment -> {
                    PaymentResponseDto dto = modelMapper.map(payment, PaymentResponseDto.class);
                    dto.setClientName(payment.getClient().getName());
                    return dto;
                }).collect(Collectors.toList());
    }


}
