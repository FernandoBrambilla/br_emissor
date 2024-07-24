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

import com.fernando.Entities.ProductUnity;
import com.fernando.services.ProductUnityService;

@RestController
@RequestMapping(value = "/unidade")
public class ProductUnityController {

	@Autowired
	private ProductUnityService service;

	// FindAll Controller
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductUnity> findAll() {
		return service.findAll();
	}

	// FindByName Controller
	@GetMapping(value = "/desc", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductUnity>> findByName(@RequestParam String desc) {
		return new ResponseEntity<List<ProductUnity>>(service.findByName(desc), HttpStatus.OK);
	}

	// FindById Controller
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductUnity findById(@PathVariable(value = "id") Integer id) {
		return service.findById(id);
	}

	// Create Controller
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductUnity create(@RequestBody ProductUnity unity) {
		return service.create(unity);
	}

	// Update Controller
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductUnity update(@RequestBody ProductUnity unity) {
		return service.update(unity);
	}

	// Delete Controller
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build(); 
	}

}