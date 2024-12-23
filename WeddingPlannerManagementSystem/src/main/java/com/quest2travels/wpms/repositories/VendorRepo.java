package com.quest2travels.wpms.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quest2travels.wpms.entities.Vendor;

@Repository
public interface VendorRepo extends	JpaRepository<Vendor, Long> {
	@Query("SELECT v FROM Vendor v WHERE v.serviceType = :serviceType AND v.available = true AND NOT EXISTS " +
	           "(SELECT b FROM Booking b WHERE b.vendor = v AND b.event.eventDate = :date AND b.active = true)")
 public List<Vendor> findVendorsByServiceTypeAndDate(@Param("serviceType") String serviceType,@Param("eventDate") LocalDate eventDate );
}
