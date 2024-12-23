package com.quest2travels.wpms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.quest2travels.wpms.entities.Event;
import com.quest2travels.wpms.payload.EventRequestDto;
import com.quest2travels.wpms.payload.EventResponseDto;
import com.quest2travels.wpms.services.EventService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDto> createEvent(@RequestBody EventRequestDto eventDTO) {
        EventResponseDto createdEvent = eventService.createEvent(eventDTO);
        return ResponseEntity.ok(createdEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getEventById(@PathVariable Long id) {
        EventResponseDto event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<EventResponseDto>> getEventsByClient(@PathVariable Long clientId) {
        List<EventResponseDto> events = eventService.getEventByClientId(clientId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/status/{completed}")
    public ResponseEntity<List<EventResponseDto>> getEventsByStatus(@PathVariable boolean completed) {
        List<EventResponseDto> events = eventService.getEventByStatus(completed);
        return ResponseEntity.ok(events);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        List<EventResponseDto> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<EventResponseDto> updateEventStatus(@PathVariable Long id, @RequestParam boolean completed) {
        EventResponseDto updatedEvent = eventService.updateEventStatus(id, completed);
        return ResponseEntity.ok(updatedEvent);
    }
}