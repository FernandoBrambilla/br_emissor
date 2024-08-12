package com.fernando.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.Controllers.ProductController;
import com.fernando.Entities.Product;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.ProductRepository;

@Service
public class ProductService {

	ProductRepository repository;
	
	private final ModelMapper mapper;
	
	
	@Autowired
	public ProductService(ProductRepository repository,  ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	} 

	// FindAll
	public List<Product> findAll() {
		var entity = repository.findAll().stream()
				.map(product-> mapper.map(product, Product.class))
				.collect(Collectors.toList());
		//LINK HATEOAS
		entity.stream().forEach(product -> product.add(linkTo(methodOn(ProductController.class)
				.findById(product.getId())).withSelfRel()));
		return entity; 
	}

	//FindByDesc
	public List<Product> findByName(String desc){
		var entity = repository.findByName(desc).stream()
				.map(product -> mapper.map(product, Product.class))
				.collect(Collectors.toList());
		//LINK HATEOAS
		entity.stream().forEach(product -> product.add(linkTo(methodOn(ProductController.class)
				.findById(product.getId())).withSelfRel()));
		return entity;
	}

	// FindById
	public Product findById(Long id) {
		var entity = repository.findById(id);
		var productVO = entity.map(product -> mapper.map(product, Product.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		//LINK HATEOAS
		productVO.add(linkTo(methodOn(ProductController.class).findById(id)).withSelfRel());
		return productVO;	
	}	

	// Create
	public Product create(Product product) {
		if (product == null)
			throw new RequiredObjectIsNullException();
		var productVo = repository.save(product);
		
		//LINK HATEOAS
		productVo.add(linkTo(methodOn(ProductController.class).findById(productVo.getId())).withSelfRel());
		return repository.save(product);
	}

	// Update
	public Product update(Product product) {
		if (product == null)
			throw new RequiredObjectIsNullException();
		
		Product entity = repository.findById(product.getId())
				.orElseThrow(() -> new ResourceNotFoundException());
                entity.setDescricao(product.getDescricao());
                entity.setCodigo(product.getCodigo());
                entity.setValorVenda(product.getValorVenda());
                entity.setCusto(product.getCusto());
                entity.setEstoque(product.getEstoque());
                entity.setUnidadeProduto(product.getUnidadeProduto());
                entity.setCategoria(product.getCategoria());
                entity.setMarkup(product.getMarkup());
                entity.setFornecedor(product.getFornecedor());
                entity.setTributacao(product.getTributacao());
                entity.setNcm(product.getNcm());
                entity.setCest(product.getCest());
                entity.setDataInclusao(product.getDataInclusao());
                entity.setEAN_GTIN(product.getEAN_GTIN());
                entity.setObs(product.getObs());
               
             
		//LINK HATEOAS
		entity.add(linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel());
		return repository.save(entity);
	}

	// Delete
	public void delete(Long id) {
		Product entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
}