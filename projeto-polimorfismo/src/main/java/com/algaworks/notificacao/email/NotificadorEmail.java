package com.algaworks.notificacao.email;

import java.util.ArrayList;

import com.algaworks.notificacao.Notificador;
import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;

public class NotificadorEmail implements Notificador {

	@Override
	public void notificar(String destinatario, String mensagem) {
		MandrillApi mandrillApi = new MandrillApi("5V9UJhWbBL-pSGtt3RBiNA");

		MandrillMessage message = new MandrillMessage();
		message.setSubject("Encomenda a caminho");
		message.setHtml(mensagem);
		message.setAutoText(true);
		message.setFromEmail("algaworks@algaworks.com");
		message.setFromName("AlgaWorks");

		ArrayList<Recipient> recipients = new ArrayList<Recipient>();
		Recipient recipient = new Recipient();
		recipient.setEmail(destinatario);
		recipients.add(recipient);

		message.setTo(recipients);
		try {
			mandrillApi.messages().send(message, false);
		} catch (Exception e) {
			System.err.println("Erro enviando e-mail - " + e.getMessage());
		}
	}

}
