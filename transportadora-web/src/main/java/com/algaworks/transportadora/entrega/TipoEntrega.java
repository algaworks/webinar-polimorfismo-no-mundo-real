package com.algaworks.transportadora.entrega;

import javax.enterprise.inject.Instance;

import com.algaworks.transportadora.entrega.expressa.EntregaExpressa;
import com.algaworks.transportadora.entrega.normal.EntregaNormal;

public enum TipoEntrega {

	NORMAL {
		@Override
		public Entrega obterEntrega(Instance<Entrega> entregas) {
			return entregas.select(EntregaNormal.class).get();
		}
	},
	EXPRESSA {
		@Override
		public Entrega obterEntrega(Instance<Entrega> entregas) {
			return entregas.select(EntregaExpressa.class).get();
		}
	};
	
	public abstract Entrega obterEntrega(Instance<Entrega> entregas);
	
}
