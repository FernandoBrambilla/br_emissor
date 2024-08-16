package com.fernando.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fernando.Controllers.VendaController;
import com.fernando.Entities.Venda;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.VendaRepository;

@Service
public class VendaService {

	VendaRepository repository;

	private final ModelMapper mapper;

	public VendaService(VendaRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// FindAll
	public List<Venda> findAll() {
		var entity = repository.findAll().stream().map(venda -> mapper.map(venda, Venda.class))
				.collect(Collectors.toList());
		// LINK HATEOAS
		entity.stream().forEach(
				venda -> venda.add(linkTo(methodOn(VendaController.class).findById(venda.getId())).withSelfRel()));
		return entity;
	}

	// FindById
	public Venda findById(Long id) {
		var entity = repository.findById(id);
		var vendaVo = entity.map(venda -> mapper.map(venda, Venda.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		// LINK HATEOAS
		vendaVo.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
		return vendaVo;
	}

	// Create
	public Venda create(Venda venda) {
		if (venda == null)
			throw new RequiredObjectIsNullException();
		var vendaVo = repository.save(venda);

		// LINK HATEOAS
		vendaVo.add(linkTo(methodOn(VendaController.class).findById(vendaVo.getId())).withSelfRel());
		return repository.save(vendaVo);
	}

	// Update
	public Venda update(Venda venda) {
		if (venda == null)
			throw new RequiredObjectIsNullException();
		Venda entity = repository.findById(venda.getId()).orElseThrow(() -> new ResourceNotFoundException());
		entity.setCliente(venda.getCliente());
		entity.setDataVenda(venda.getDataVenda());
		entity.setDesconto(venda.getDesconto());
		entity.setMeioPgto(venda.getMeioPgto());
		entity.setTroco(venda.getTroco());
		entity.setStatus(venda.getStatus());
		entity.setUsuario(venda.getUsuario());
		entity.setValorPago(venda.getValorPago());
		
		// LINK HATEOAS
		entity.add(linkTo(methodOn(VendaController.class).findById(venda.getId())).withSelfRel());
		return repository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		Venda entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);

	}

}
