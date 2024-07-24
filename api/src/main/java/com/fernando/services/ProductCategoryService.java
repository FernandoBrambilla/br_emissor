package com.fernando.services;

import com.fernando.Controllers.ProductCategoryController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.Entities.Product;
import com.fernando.Entities.ProductCategory;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	/*
	 * CLASSE CATEGORIA DO PRODUTO, NÃO RETORNA LINK HATEOAS
	 */

	ProductCategoryRepository repository;

	private final ModelMapper mapper;

	@Autowired
	public ProductCategoryService(ProductCategoryRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	// FindAll
	public List<ProductCategory> findAll() {
		var entity = repository.findAll().stream().map(category -> mapper.map(category, ProductCategory.class))
				.collect(Collectors.toList());
		return entity;
	}

	// FindByDesc
	public List<ProductCategory> findByName(String desc) {
		var entity = repository.findByName(desc).stream().map(category -> mapper.map(category, ProductCategory.class))
				.collect(Collectors.toList());
		return entity;
	}

	// FindById
	public ProductCategory findById(Integer id) {
		var entity = repository.findById(id);
		var categoryVO = entity.map(category -> mapper.map(category, ProductCategory.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		return categoryVO;
	}

	// Create
	public ProductCategory create(ProductCategory category) {
		if (category == null)
			throw new RequiredObjectIsNullException();
		return repository.save(category);
	}

	// Update
	public ProductCategory update(ProductCategory category) {
		if (category == null)
			throw new RequiredObjectIsNullException();

		ProductCategory entity = repository.findById(category.getId())
				.orElseThrow(() -> new ResourceNotFoundException());
		entity.setDescricao(category.getDescricao());
		return repository.save(entity);
	}

	// Delete
	public void delete(Integer id) {
		ProductCategory entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
}