package com.quest2travels.wpms.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quest2travels.wpms.entities.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long>  {
}
