package com.algaworks.transportadora.entrega.expressa;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.algaworks.transportadora.entrega.Entrega;
import com.algaworks.transportadora.model.Destino;
import com.algaworks.transportadora.notificacao.Notificador;
import com.algaworks.transportadora.notificacao.TipoNotificador;

public class EntregaExpressa implements Entrega {

	@Inject @Any
	private Instance<Notificador> notificadores;
	
	@Override
	public Integer registrarEncomenda(String destinatario, Destino destino, TipoNotificador tipoNotificador) {
		float tempoPorKmEmMin = 0.12f;
		Integer distancia = destino.getDistancia();
		float tempoParaEntregaEmMin = distancia * tempoPorKmEmMin;
		
		float tempoParaEntregaEmHoras = tempoParaEntregaEmMin / 24;
		int margemSegurancaEmHoras = 24;
		tempoParaEntregaEmHoras += margemSegurancaEmHoras;
		
		Integer diasParaEntrega = (int) Math.floor(tempoParaEntregaEmHoras) / 24;
		
		Notificador notificador = tipoNotificador.obterNotificador(notificadores);
		notificador.notificar(destinatario, "Encomenda Expressa em " + diasParaEntrega + " dia(s)");
		return diasParaEntrega;
	}

}
