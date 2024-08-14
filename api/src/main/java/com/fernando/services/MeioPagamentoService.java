package com.fernando.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fernando.Controllers.MeioPagamentoController;
import com.fernando.Entities.MeioPagamento;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.MeioPagamentoRepository;

@Service
public class MeioPagamentoService {

	MeioPagamentoRepository repository;

	private final ModelMapper mapper;

	public MeioPagamentoService(MeioPagamentoRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// FindAll
	public List<MeioPagamento> findAll() {
		var entity = repository.findAll().stream().map(meios -> mapper.map(meios, MeioPagamento.class))
				.collect(Collectors.toList());
		// LINK HATEOAS
		entity.stream().forEach(meios -> meios
				.add(linkTo(methodOn(MeioPagamentoController.class).findById(meios.getId())).withSelfRel()));
		return entity;
	}

	// FindByName
	public List<MeioPagamento> findByName(String name) {
		var entity = repository.findByName(name).stream().map(meios -> mapper.map(meios, MeioPagamento.class))
				.collect(Collectors.toList());
		// LINK HATEOAS
		entity.stream().forEach(meios -> meios
				.add(linkTo(methodOn(MeioPagamentoController.class).findById(meios.getId())).withSelfRel()));
		return entity;
	}

	// FindById
	public MeioPagamento findById(Long id) {
		var entity = repository.findById(id);
		var meioVo = entity.map(meios -> mapper.map(meios, MeioPagamento.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		// LINK HATEOAS
		meioVo.add(linkTo(methodOn(MeioPagamentoController.class).findById(id)).withSelfRel());
		return meioVo;
	}

	// Create
	public MeioPagamento create(MeioPagamento meio) {
		if (meio == null)
			throw new RequiredObjectIsNullException();
		var meioVo = repository.save(meio);

		// LINK HATEOAS
		meioVo.add(linkTo(methodOn(MeioPagamentoController.class).findById(meioVo.getId())).withSelfRel());
		return repository.save(meioVo);
	}

	// Update
	public MeioPagamento update(MeioPagamento meio) {
		if (meio == null)
			throw new RequiredObjectIsNullException();
		MeioPagamento entity = repository.findById(meio.getId()).orElseThrow(() -> new ResourceNotFoundException());
		entity.setMeio(meio.getMeio());
		// LINK HATEOAS
		entity.add(linkTo(methodOn(MeioPagamentoController.class).findById(meio.getId())).withSelfRel());
		return repository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		MeioPagamento entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);

	}

}
