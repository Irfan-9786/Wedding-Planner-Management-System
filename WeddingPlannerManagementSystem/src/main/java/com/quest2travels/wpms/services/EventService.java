package com.quest2travels.wpms.services;

import java.util.List;

import com.quest2travels.wpms.entities.Event;
import com.quest2travels.wpms.payload.EventRequestDto;
import com.quest2travels.wpms.payload.EventResponseDto;

public interface EventService {
	EventResponseDto createEvent(EventRequestDto eventRequestDto);
	EventResponseDto getEventById(Long id);
	List<EventResponseDto> getEventByClientId(Long clientId);
	List<EventResponseDto> getEventByStatus(boolean completed);
	List<EventResponseDto> getAllEvents();
	EventResponseDto updateEventStatus(Long eventId,boolean completed);

}
