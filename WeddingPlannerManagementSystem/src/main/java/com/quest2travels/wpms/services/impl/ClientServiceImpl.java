package com.quest2travels.wpms.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest2travels.wpms.entities.Client;
import com.quest2travels.wpms.exceptions.ResourceNotFoundException;
import com.quest2travels.wpms.payload.ClientDto;
import com.quest2travels.wpms.repositories.ClientRepo;
import com.quest2travels.wpms.services.ClientService;
@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepo clientRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ClientDto registerClient(ClientDto clientDto) {
		Client client=this.modelMapper.map(clientDto, Client.class);
		Client client2=clientRepo.save(client);
		System.out.println("client id>>>"+client2.getId());
		return this.modelMapper.map(client2, ClientDto.class);
	}

	@Override
	public List<ClientDto> getAllClients() {
		List<Client> getAllClients=clientRepo.findAll();
		if(getAllClients.isEmpty()) {
			throw new ResourceNotFoundException("None of the client has been registered yet...!!!");
		}
		List<ClientDto> getallClientDtos=getAllClients.stream().map(client->this.modelMapper.map(client, ClientDto.class)).collect(Collectors.toList());
		return getallClientDtos;
	}

	@Override
	public ClientDto getClientById(Long id) {
		Client clientByID=clientRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Either invalid ClientDto Id \nOR \nClient with id="+id+" doesn't exist in database...!!!"));
		return this.modelMapper.map(clientByID, ClientDto.class);
	}

}
