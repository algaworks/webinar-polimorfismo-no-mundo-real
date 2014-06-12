package com.algaworks.entrega.expressa;

import com.algaworks.entrega.Entrega;
import com.algaworks.notificacao.Notificador;
import com.algaworks.repositorio.Destinos;

public class EntregaExpresssa implements Entrega {

	private Destinos destinos;
	private Notificador notificador;
	
	public EntregaExpresssa(Destinos destinos, Notificador notificador) {
		this.destinos = destinos;
		this.notificador = notificador;
	}

	@Override
	public Integer registrarEntrega(String cidade, String destinatario) {
		Integer distancia = destinos.distanciaPara(cidade);
		
		float tempoPorKmEmMin = 0.12f;
		float tempoParaEntregaEmMin = distancia * tempoPorKmEmMin;
		
		float tempoParaEntregaEmHoras = tempoParaEntregaEmMin / 24;
		int margemSegurancaEmHoras = 24;
		tempoParaEntregaEmHoras += margemSegurancaEmHoras;
		
		Integer diasParaEntrega = (int) Math.floor(tempoParaEntregaEmHoras) / 24; 
		this.notificador.notificar(destinatario, "Entrega Expressa - " + diasParaEntrega + " dia(s)");
		return diasParaEntrega;
	}

}
