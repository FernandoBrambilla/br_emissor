package com.fernando.Entities;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "meio_pagamento")
public class MeioPagamento  extends RepresentationModel<MeioPagamento> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String meio;

	public Long getId() {
		return id;
	}

	public String getMeio() {
		return meio;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMeio(String meio) {
		this.meio = meio;
	}
	
}
