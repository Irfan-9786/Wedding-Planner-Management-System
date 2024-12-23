package com.quest2travels.wpms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quest2travels.wpms.entities.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
List<Booking> findByEventId(Long eventId);
List<Booking> findByActive(Boolean active);
}
