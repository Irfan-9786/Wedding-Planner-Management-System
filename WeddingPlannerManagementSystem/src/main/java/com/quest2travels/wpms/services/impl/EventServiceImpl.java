package com.quest2travels.wpms.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest2travels.wpms.entities.Client;
import com.quest2travels.wpms.entities.Event;
import com.quest2travels.wpms.exceptions.ResourceNotFoundException;
import com.quest2travels.wpms.payload.ClientDto;
import com.quest2travels.wpms.payload.EventRequestDto;
import com.quest2travels.wpms.payload.EventResponseDto;
import com.quest2travels.wpms.repositories.ClientRepo;
import com.quest2travels.wpms.repositories.EventRepo;
import com.quest2travels.wpms.services.EventService;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private EventRepo eventRepo;
	@Autowired
	private ClientRepo clientRepo;

	@Override
	public EventResponseDto createEvent(EventRequestDto eventRequestDto) {
		Client client = clientRepo.findById(eventRequestDto.getClientId())
				.orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + eventRequestDto.getClientId()));
		Event event = new Event();
		event.setName(eventRequestDto.getName());
		event.setEventDate(eventRequestDto.getEventDate());
		event.setClient(client);
		// set initial status as false
		event.setCompleted(false);
		Event event2 = eventRepo.save(event);
		EventResponseDto eventResponseDto = this.modelMapper.map(event2, EventResponseDto.class);
		eventResponseDto.setClientDto(this.modelMapper.map(client, ClientDto.class));
		return eventResponseDto;
	}

	@Override
	public EventResponseDto getEventById(Long id) {

		Event event = eventRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + id));
		return this.modelMapper.map(event, EventResponseDto.class);
	}

	@Override
	public List<EventResponseDto> getEventByClientId(Long clientId) {
		List<Event> events = eventRepo.findByClientId(clientId);

		return events.stream().map(event -> this.modelMapper.map(event, EventResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<EventResponseDto> getEventByStatus(boolean completed) {
		List<Event> events = eventRepo.findByCompleted(completed);
		List<EventResponseDto> eventResponseDtos = events.stream().map(event -> {
			EventResponseDto dto = this.modelMapper.map(event, EventResponseDto.class);
			if (event.getClient() != null) {
				dto.setClientDto(this.modelMapper.map(event.getClient(), ClientDto.class));
			}
			return dto;
		}).collect(Collectors.toList());
		return eventResponseDtos;
	}

	@Override
	public List<EventResponseDto> getAllEvents() {
		List<Event> events = eventRepo.findAll();
		List<EventResponseDto> eventResponseDtos = events.stream().map(event -> {
			EventResponseDto dto = this.modelMapper.map(event, EventResponseDto.class);
			if (event.getClient() != null) {
				dto.setClientDto(this.modelMapper.map(event.getClient(), ClientDto.class));
			}
			return dto;
		}).collect(Collectors.toList());
		return eventResponseDtos;
	}

	@Override
	public EventResponseDto updateEventStatus(Long eventId, boolean completed) {
		Event event = eventRepo.findById(eventId)
				.orElseThrow(() -> new ResourceNotFoundException("Event not found with id " + eventId));
		event.setCompleted(completed);
		Event event2 = eventRepo.save(event);

		EventResponseDto dto = this.modelMapper.map(event2, EventResponseDto.class);
		if (event.getClient() != null) {
			dto.setClientDto(modelMapper.map(event.getClient(), ClientDto.class));
		}
		return dto;
	}

}
