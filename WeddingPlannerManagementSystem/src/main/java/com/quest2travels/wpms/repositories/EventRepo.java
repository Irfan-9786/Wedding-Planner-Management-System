package com.quest2travels.wpms.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quest2travels.wpms.entities.Event;

@Repository
public interface EventRepo extends JpaRepository<Event, Long>{

List<Event> findByClientId(Long clientId);
List<Event> findByCompleted(boolean completed);
}
