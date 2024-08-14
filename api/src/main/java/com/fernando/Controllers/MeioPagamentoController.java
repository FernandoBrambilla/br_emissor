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

import com.fernando.Entities.MeioPagamento;
import com.fernando.services.MeioPagamentoService;

@RestController
@RequestMapping(value = "/meioPagamento")
public class MeioPagamentoController {

	@Autowired
	private MeioPagamentoService service;

	// FindAll Controller
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MeioPagamento> findAll() {
		return service.findAll();
	}

	// FindByName Controller
	@GetMapping(value = "/name", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MeioPagamento>> findByName(@RequestParam String name) {
		return new ResponseEntity<List<MeioPagamento>>(service.findByName(name), HttpStatus.OK);
	}

	// FindById Controller
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public MeioPagamento findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	// Create Controller
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public MeioPagamento create(@RequestBody MeioPagamento meio) {
		return service.create(meio);
	}

	// Update Controller
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public MeioPagamento update(@RequestBody MeioPagamento meio) {
		return service.update(meio);
	}

	// Delete Controller
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}