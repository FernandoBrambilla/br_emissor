package com.fernando.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.Controllers.NCMController;
import com.fernando.Entities.NCM;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.NcmRepository;

@Service
public class NcmService {

	NcmRepository repository;

	private final ModelMapper mapper;

	@Autowired
	public NcmService(NcmRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
 
	// FindAll
	public List<NCM> findAll() {
		var entity = repository.findAll().stream().map(ncm -> mapper.map(ncm, NCM.class)).collect(Collectors.toList());
		// LINK HATEOAS
		entity.stream()
				.forEach(ncm -> ncm.add(linkTo(methodOn(NCMController.class).findById(ncm.getNcm())).withSelfRel()));
		return entity;
	}

	// FindByDesc
	public List<NCM> findByName(String desc) {
		var entity = repository.findByName(desc).stream().map(ncm -> mapper.map(ncm, NCM.class))
				.collect(Collectors.toList());
		// LINK HATEOAS
		entity.stream()
				.forEach(ncm -> ncm.add(linkTo(methodOn(NCMController.class).findById(ncm.getNcm())).withSelfRel()));
		return entity;
	} 

	// FindById
	public NCM findById(Long id) {
		var entity = repository.findById(id);
		var ncmVO = entity.map(ncm -> mapper.map(ncm, NCM.class)).orElseThrow(() -> new ResourceNotFoundException());
		// LINK HATEOAS
		ncmVO.add(linkTo(methodOn(NCMController.class).findById(id)).withSelfRel());
		return ncmVO;
	}

	// Create
	public NCM create(NCM ncm) {
		if (ncm == null)
			throw new RequiredObjectIsNullException();
		var ncmVO = repository.save(ncm);

		// LINK HATEOAS
		ncmVO.add(linkTo(methodOn(NCMController.class).findById(ncmVO.getNcm())).withSelfRel());
		return repository.save(ncmVO);
	}

	// Update
	public NCM update(NCM ncm) {
		if (ncm == null)
			throw new RequiredObjectIsNullException(); 
		NCM entity = repository.findById(ncm.getNcm()).orElseThrow(() -> new ResourceNotFoundException()); 
		entity.setNcm(ncm.getNcm());
		entity.setEx(ncm.getEx());
		entity.setTipo(ncm.getTipo());
		entity.setDescricao(ncm.getDescricao());
		entity.setNacionalfederal(ncm.getNacionalfederal());
		entity.setImportadosfederal(ncm.getImportadosfederal());
		entity.setEstadual(ncm.getEstadual());
		entity.setMunicipal(ncm.getMunicipal());
		entity.setVigenciainicio(ncm.getVigenciainicio());
		entity.setVigenciafim(ncm.getVigenciafim());
		entity.setChave(ncm.getChave());
		entity.setVersao(ncm.getVersao());
		entity.setFonte(ncm.getFonte());
		// LINK HATEOAS
		entity.add(linkTo(methodOn(NCMController.class).findById(ncm.getNcm())).withSelfRel());
		return repository.save(entity);
	}

}