package com.algaworks.repositorio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Destinos {

	private static Map<String, Integer> destinos = new HashMap<>();
	
	static {
		destinos.put("São Paulo", 590);
		destinos.put("Rio de Janeiro", 986);
		destinos.put("Belo Horizonte", 535);
		destinos.put("Recife", 2290);
	}
	
	public Set<String> todos() {
		return destinos.keySet();
	}
	
	public Integer distanciaPara(String cidade) {
		return destinos.get(cidade);
	}
	
}
