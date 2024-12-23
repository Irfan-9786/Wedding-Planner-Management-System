package com.quest2travels.wpms.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quest2travels.wpms.entities.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
List<Payment> findByCompleted(Boolean completed);
List<Payment> findByPaymentDateBetween(LocalDate start, LocalDate end);
}
