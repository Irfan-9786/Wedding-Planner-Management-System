package com.quest2travels.wpms.services;

import java.util.List;
import java.util.Optional;

import com.quest2travels.wpms.entities.Client;
import com.quest2travels.wpms.payload.ClientDto;

public interface ClientService {
	public ClientDto registerClient(ClientDto clientDto);
	public List<ClientDto> getAllClients();
	public ClientDto getClientById(Long id);

}
