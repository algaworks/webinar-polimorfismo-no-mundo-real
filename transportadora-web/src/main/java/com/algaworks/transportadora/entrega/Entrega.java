package com.algaworks.transportadora.entrega;

import com.algaworks.transportadora.model.Destino;
import com.algaworks.transportadora.notificacao.TipoNotificador;

public interface Entrega {

	public Integer registrarEncomenda(String destinatario, Destino destino, TipoNotificador tipoNotificador);
	
}
