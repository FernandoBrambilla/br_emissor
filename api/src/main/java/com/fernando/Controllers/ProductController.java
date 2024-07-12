package com.fernando.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.Entities.Product;
import com.fernando.services.ProductService;

 
@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	
	//FindAll Controller
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> findAll(){
		return service.findAll();
	}
	
	//FindByName Controller
	@GetMapping(value= "/desc", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> findByName(@RequestParam String desc){
		return new ResponseEntity<List<Product>> (service.findByName(desc),HttpStatus.OK);
	}
	
	//FindById Controller
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Product findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	//Create Controller
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Product create(@RequestBody Product product) {
		return service.create(product);	
	}
	
	//Update Controller
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Product update(@RequestBody Product product) {
		return service.update(product);
	}
	
	//Delete Controller
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable (value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}