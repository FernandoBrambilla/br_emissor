package com.fernando.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.fernando.Entities.Markup;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.MarkupRepository;

@Service
public class MarkupService {

	private final MarkupRepository repository;
	private final ModelMapper mapper;

	public MarkupService(MarkupRepository repository, ModelMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	/**
	 * Método retorna valor único da markup
	 * 
	 * @return Markup
	 */

	public Markup getMarkup(int id) {
		var entity = repository.findById(id);
		var markupVO = entity.map(markup -> mapper.map(markup, Markup.class))
				.orElseThrow(() -> new ResourceNotFoundException());
		return markupVO;
	}
	
	/**
	 * Médodo para salvar novo markup
	 * @param
	 * @return
	 */
	public Markup save(Markup markup) {
		if (markup == null)
			throw new RequiredObjectIsNullException();
		return repository.save(markup);
	}

	/**
	 * Método para atualizar o markup
	 * @param 
	 * @return
	 */
	public Markup updade(Markup markup) {
		Markup entity = repository.findById(markup.getId()).orElseThrow(() -> new ResourceNotFoundException());
		entity.setMarkup(markup.getMarkup());
		entity.setUtilizar(markup.isUtilizar());
		return repository.save(entity);
	}
}
