package com.fernando.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.Entities.ProductCategory;
import com.fernando.services.ProductCategoryService;

 
@RestController
@RequestMapping(value = "/category")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService service;

	
	//FindAll Controller
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductCategory> findAll(){
		return service.findAll();
	}
	
	//FindByName Controller
	@GetMapping(value= "/desc", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductCategory>> findByName(@RequestParam String desc){
		return new ResponseEntity<List<ProductCategory>> (service.findByName(desc),HttpStatus.OK);
	}
	
	//FindById Controller
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductCategory findById(@PathVariable(value = "id") Integer id) {
		return service.findById(id);
	}
	
	//Create Controller
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductCategory create(@RequestBody ProductCategory category) {
		return service.create(category);	
	}
	
	//Update Controller
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductCategory update(@RequestBody ProductCategory category) {
		return service.update(category);
	}
}