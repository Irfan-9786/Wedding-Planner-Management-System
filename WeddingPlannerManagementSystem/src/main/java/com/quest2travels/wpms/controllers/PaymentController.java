package com.quest2travels.wpms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quest2travels.wpms.payload.PaymentRequestDto;
import com.quest2travels.wpms.payload.PaymentResponseDto;
import com.quest2travels.wpms.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
@Autowired
    private PaymentService paymentService;


    @PostMapping
    public ResponseEntity<PaymentResponseDto> recordPayment(@RequestBody PaymentRequestDto requestDTO) {
        return new ResponseEntity<>(paymentService.recordPayment(requestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getAllPayments(@RequestParam(required = false) Boolean completed) {
        return new ResponseEntity<>(paymentService.getAllPayments(completed), HttpStatus.OK);
    }
}