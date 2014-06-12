package com.algaworks.entrega;

import com.algaworks.entrega.expressa.EntregaExpresssa;
import com.algaworks.entrega.normal.EntregaNormal;
import com.algaworks.notificacao.Notificador;
import com.algaworks.repositorio.Destinos;

public enum TipoEntrega {

	NORMAL {
		@Override
		public Entrega obterEntrega(Destinos destinos, Notificador notificador) {
			return new EntregaNormal(destinos, notificador);
		}
	},
	EXPRESSA {
		@Override
		public Entrega obterEntrega(Destinos destinos, Notificador notificador) {
			return new EntregaExpresssa(destinos, notificador);
		}
	};
	
	public abstract Entrega obterEntrega(Destinos destinos, Notificador notificador);
	
}
