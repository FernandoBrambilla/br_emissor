package com.fernando.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.Controllers.ClientController;
import com.fernando.Entities.Clients;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.ClientRepository;

@Service
public class ClientService {

	ClientRepository repository;
	
	private final ModelMapper mapper;
	
	
	@Autowired
	public ClientService(ClientRepository repository,  ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// FindAll
	public List<Clients> findAll() {
		var entity = repository.findAll().stream()
				.map(clients -> mapper.map(clients, Clients.class))
				.collect(Collectors.toList());
		//LINK HATEOAS
		entity.stream().forEach(clients -> clients.add(linkTo(methodOn(ClientController.class)
				.findById(clients.getId())).withSelfRel()));
		return entity;
	}

	//FindByName
	public List<Clients> findByName(String name){
		var entity = repository.findByName(name).stream()
				.map(client -> mapper.map(client, Clients.class))
				.collect(Collectors.toList());
		//LINK HATEOAS
		entity.stream().forEach(client -> client.add(linkTo(methodOn(ClientController.class)
				.findById(client.getId())).withSelfRel()));
		return entity;
	}

	// FindById
	public Clients findById(Long id) {
		var entity = repository.findById(id);
		var clientPFVo = entity.map(client -> mapper.map(client, Clients.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		//LINK HATEOAS
		clientPFVo.add(linkTo(methodOn(ClientController.class).findById(id)).withSelfRel());
		return clientPFVo;	
	}	

	// Create
	public Clients create(Clients clientPF) {
		if (clientPF == null)
			throw new RequiredObjectIsNullException();
		var insuranceVo = repository.save(clientPF);
		
		//LINK HATEOAS
		insuranceVo.add(linkTo(methodOn(ClientController.class).findById(insuranceVo.getId())).withSelfRel());
		return repository.save(clientPF);
	}

	// Update
	public Clients update(Clients client) {
		if (client == null)
			throw new RequiredObjectIsNullException();
		
		Clients entity = repository.findById(client.getId())
				.orElseThrow(() -> new ResourceNotFoundException());
		entity.setName(client.getName());
		entity.setPhone(client.getPhone());
		entity.setEmail(client.getEmail());
		entity.setCpf_cnpj(client.getCpf_cnpj());
		entity.setRg_ie(client.getRg_ie());
		entity.setDateNasc_const(client.getDateNasc_const());
		entity.setDateExp(client.getDateExp());
		entity.setAddress(client.getAddress());
		entity.setAddressNumber(client.getAddressNumber());
		entity.setAddressComplement(client.getAddressComplement());
		entity.setCity(client.getCity());
		entity.setUf(client.getUf());
		entity.setCep(client.getCep());

		//LINK HATEOAS
		entity.add(linkTo(methodOn(ClientController.class).findById(client.getId())).withSelfRel());
		return repository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		Clients entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);

	}
}