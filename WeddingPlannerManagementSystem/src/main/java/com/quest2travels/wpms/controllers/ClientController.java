package com.quest2travels.wpms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quest2travels.wpms.entities.Client;
import com.quest2travels.wpms.payload.ClientDto;
import com.quest2travels.wpms.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {
	@Autowired
	private ClientService clientService;

	@PostMapping
	public ResponseEntity<ClientDto> addClient(@RequestBody @Valid ClientDto clientDto) {
		ClientDto clientDto2 = clientService.registerClient(clientDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(clientDto2);
	}
	@GetMapping("/{id}")
	public ResponseEntity<ClientDto> getClientById(@PathVariable("id") Long id) {
		ClientDto clientDto = clientService.getClientById(id);
		return ResponseEntity.ok(clientDto);
	}

	
	@GetMapping
	public ResponseEntity<List<ClientDto>> retrieveAllClients() {
		List<ClientDto> allClientDtos = clientService.getAllClients();
		return ResponseEntity.ok(allClientDtos);

	}
	
}
