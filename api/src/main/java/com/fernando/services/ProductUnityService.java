package com.fernando.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.Entities.ProductUnity;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.ProductUnityRepository;

@Service
public class ProductUnityService {

	/*
	 * CLASSE UNIDADE DO PRODUTO, NÃO RETORNA LINK HATEOAS
	 */

	ProductUnityRepository repository;

	private final ModelMapper mapper;

	@Autowired
	public ProductUnityService(ProductUnityRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// FindAll
	public List<ProductUnity> findAll() {
		var entity = repository.findAll().stream().map(unity -> mapper.map(unity, ProductUnity.class))
				.collect(Collectors.toList());
		return entity;
	}

	// FindByDesc
	public List<ProductUnity> findByName(String desc) {
		var entity = repository.findByName(desc).stream().map(unity -> mapper.map(unity, ProductUnity.class))
				.collect(Collectors.toList());
		return entity;
	}

	// FindById
	public ProductUnity findById(Integer id) {
		var entity = repository.findById(id);
		var unityVO = entity.map(unity -> mapper.map(unity, ProductUnity.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		return unityVO;
	}

	// Create
	public ProductUnity create(ProductUnity unity) {
		if (unity == null)
			throw new RequiredObjectIsNullException();
		return repository.save(unity);
	}

	// Update
	public ProductUnity update(ProductUnity unity) {
		if (unity == null)
			throw new RequiredObjectIsNullException();

		ProductUnity entity = repository.findById(unity.getId())
				.orElseThrow(() -> new ResourceNotFoundException());
		entity.setDescricao(unity.getDescricao());
		return repository.save(entity);
	}

	// Delete
	public void delete(Integer id) {
		ProductUnity entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
}