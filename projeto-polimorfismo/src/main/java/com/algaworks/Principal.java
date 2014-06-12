package com.algaworks;

import com.algaworks.entrega.Entrega;
import com.algaworks.entrega.TipoEntrega;
import com.algaworks.gui.Gui;
import com.algaworks.notificacao.Notificador;
import com.algaworks.notificacao.TipoNotificador;
import com.algaworks.repositorio.Destinos;

public class Principal {

	public static void main(String[] args) {
		Destinos destinos = new Destinos();
		
		String cidade = Gui.obterCidade();
		TipoEntrega tipoEntrega = Gui.obterTipoEntrega();
		TipoNotificador tipoNotificador = Gui.obterTipoNotificador();
		String destinatario = Gui.obterDestinatario();
		
		Notificador notificador = tipoNotificador.obterNotificador();
		
		Entrega entrega = tipoEntrega.obterEntrega(destinos, notificador);
		Integer tempoParaEntrega = entrega.registrarEntrega(cidade, destinatario);
		
		Gui.mostrarResumo(tempoParaEntrega);
		System.exit(0);
	}
	
}
