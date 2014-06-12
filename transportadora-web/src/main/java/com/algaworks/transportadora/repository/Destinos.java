package com.algaworks.transportadora.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.algaworks.transportadora.model.Destino;

public class Destinos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Destino> todos() {
		return manager.createQuery("from Destino").getResultList();
	}

	public Destino peloCodigo(Long codigo) {
		return manager.find(Destino.class, codigo);
	}
	
}
