package com.fernando.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.Entities.Markup;
import com.fernando.services.MarkupService;

@RestController
@RequestMapping(value = "/markup")
public class MarkupController {
	
	MarkupService markupService;
	
	public MarkupController(MarkupService markupService) {
		this.markupService = markupService;
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Markup getMarkup() {
		return markupService.getMarkup(1);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Markup create(@RequestBody Markup markup) {
		return markupService.save(markup);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Markup update(@RequestBody Markup markup) {
		return markupService.updade(markup);
		
	}  
	
}
