package com.algaworks.transportadora.notificacao;

import javax.enterprise.inject.Instance;

import com.algaworks.transportadora.notificacao.email.NotificadorEmail;
import com.algaworks.transportadora.notificacao.sms.NotificadorSms;

public enum TipoNotificador {

	EMAIL {
		@Override
		public Notificador obterNotificador(Instance<Notificador> notificadores) {
			return notificadores.select(NotificadorEmail.class).get();
		}
	},
	SMS {
		@Override
		public Notificador obterNotificador(Instance<Notificador> notificadores) {
			return notificadores.select(NotificadorSms.class).get();
		}
	};
	
	public abstract Notificador obterNotificador(Instance<Notificador> notificadores);
	
}
