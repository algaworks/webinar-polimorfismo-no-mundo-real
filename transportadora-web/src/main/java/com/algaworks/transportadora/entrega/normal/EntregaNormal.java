package com.algaworks.transportadora.entrega.normal;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.algaworks.transportadora.entrega.Entrega;
import com.algaworks.transportadora.model.Destino;
import com.algaworks.transportadora.notificacao.Notificador;
import com.algaworks.transportadora.notificacao.TipoNotificador;

public class EntregaNormal implements Entrega {

	@Inject @Any
	private Instance<Notificador> notificadores;
	
	@Override
	public Integer registrarEncomenda(String destinatario, Destino destino, TipoNotificador tipoNotificador) {
		float tempoPorKmEmMin = 0.95f;
		Integer distancia = destino.getDistancia();
		float tempoParaEntregaEmMin = distancia * tempoPorKmEmMin;
		
		float tempoParaEntregaEmHoras = tempoParaEntregaEmMin / 24;
		int margemSegurancaEmHoras = 48;
		tempoParaEntregaEmHoras += margemSegurancaEmHoras;
		
		Integer diasParaEntrega = (int) Math.ceil(tempoParaEntregaEmHoras) / 24;
		
		Notificador notificador = tipoNotificador.obterNotificador(notificadores);
		notificador.notificar(destinatario, "Encomenda Normal em " + diasParaEntrega + " dia(s)");
		return diasParaEntrega;
	}

}
