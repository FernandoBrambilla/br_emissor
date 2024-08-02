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

import com.fernando.Entities.NCM;
import com.fernando.Entities.Product;
import com.fernando.services.NcmService;

@RestController
@RequestMapping(value = "/ncm")
public class NCMController {

	@Autowired
	private NcmService service;

	// FindAll Controller
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<NCM> findAll() {
		return service.findAll();
	}

	// FindByName Controller
	@GetMapping(value = "/desc", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<NCM>> findByName(@RequestParam String desc) {
		return new ResponseEntity<List<NCM>>(service.findByName(desc), HttpStatus.OK);
	}

	// FindById Controller
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public NCM findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	// Create Controller
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public NCM create(@RequestBody NCM ncm) {
		return service.create(ncm);
	}

	// Update Controller
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public NCM update(@RequestBody NCM ncm) {
		return service.update(ncm);
	}

}