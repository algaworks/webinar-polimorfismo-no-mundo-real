package com.algaworks.notificacao;

import com.algaworks.notificacao.email.NotificadorEmail;
import com.algaworks.notificacao.sms.NotificadorSms;

public enum TipoNotificador {

	EMAIL {
		@Override
		public Notificador obterNotificador() {
			return new NotificadorEmail();
		}
	},
	SMS {
		@Override
		public Notificador obterNotificador() {
			return new NotificadorSms();
		}
	};
	
	public abstract Notificador obterNotificador();
	
}
	
