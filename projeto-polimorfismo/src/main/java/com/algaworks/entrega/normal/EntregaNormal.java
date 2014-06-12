package com.algaworks.entrega.normal;

import com.algaworks.entrega.Entrega;
import com.algaworks.notificacao.Notificador;
import com.algaworks.repositorio.Destinos;

public class EntregaNormal implements Entrega {

	private Destinos destinos;
	private Notificador notificador;
	
	public EntregaNormal(Destinos destinos, Notificador notificador) {
		this.destinos = destinos;
		this.notificador = notificador;
	}

	@Override
	public Integer registrarEntrega(String cidade, String destinatario) {
		Integer distancia = destinos.distanciaPara(cidade);
		
		float tempoPorKmEmMin = 0.95f;
		float tempoParaEntregaEmMin = distancia * tempoPorKmEmMin;
		
		float tempoParaEntregaEmHoras = tempoParaEntregaEmMin / 24;
		int margemSegurancaEmHoras = 48;
		tempoParaEntregaEmHoras += margemSegurancaEmHoras;
		
		Integer diasParaEntrega = (int) Math.ceil(tempoParaEntregaEmHoras) / 24;
		this.notificador.notificar(destinatario, "Entrega Normal - " + diasParaEntrega + " dia(s)");
		
		return diasParaEntrega;
	}

}
