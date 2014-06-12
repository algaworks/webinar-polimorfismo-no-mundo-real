package com.algaworks.entrega;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.algaworks.notificacao.TipoNotificador;
import com.algaworks.repositorio.Destinos;
import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage.Recipient;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class Entrega {

	private Destinos destinos;
	
	public Entrega(Destinos destinos) {
		this.destinos = destinos;
	}

	/*
	 * ##### COMO NÃO FAZER #######
	 */
	public Integer registrarEntrega(String cidade, TipoEntrega tipoEntrega,
			TipoNotificador tipoNotificador, String destinatario) {
		
		Integer distancia = destinos.distanciaPara(cidade);
		
		String mensagem = null;
		Integer tempoParaEntrega = null;
		if (tipoEntrega.equals(TipoEntrega.NORMAL)) {
			tempoParaEntrega = this.calcularTempoEntregaNormal(distancia);
			mensagem = "Encomenda via normal em " + tempoParaEntrega + " dias";
		} else if (tipoEntrega.equals(TipoEntrega.EXPRESSA)) {
			tempoParaEntrega = this.calcularTempoEntregaExpressa(distancia);
			mensagem = "* Encomenda expressa *: " + tempoParaEntrega + " dias";
		} 
		
		if (tipoNotificador.equals(TipoNotificador.EMAIL)) {
			notificarViaEmail(destinatario, mensagem);
		} else if (tipoNotificador.equals(TipoNotificador.SMS)) {
			notificarViaSms(destinatario, mensagem);
		}
		
		return tempoParaEntrega;
	}

	private Integer calcularTempoEntregaExpressa(Integer distancia) {
		float tempoPorKmEmMin = 0.12f;
		float tempoParaEntregaEmMin = distancia * tempoPorKmEmMin;
		
		float tempoParaEntregaEmHoras = tempoParaEntregaEmMin / 24;
		int margemSegurancaEmHoras = 24;
		tempoParaEntregaEmHoras += margemSegurancaEmHoras;
		
		Integer diasParaEntrega = (int) Math.floor(tempoParaEntregaEmHoras) / 24; 
		return diasParaEntrega;
	}

	private Integer calcularTempoEntregaNormal(Integer distancia) {
		float tempoPorKmEmMin = 0.95f;
		float tempoParaEntregaEmMin = distancia * tempoPorKmEmMin;
		
		float tempoParaEntregaEmHoras = tempoParaEntregaEmMin / 24;
		int margemSegurancaEmHoras = 48;
		tempoParaEntregaEmHoras += margemSegurancaEmHoras;
		
		Integer diasParaEntrega = (int) Math.ceil(tempoParaEntregaEmHoras) / 24;
		return diasParaEntrega;
	}
	
	private void notificarViaSms(String destinatario, String mensagem) {
		String ACCOUNT_SID = "3C25B38975401091325e41c17cfc325215";
		String AUTH_TOKEN = "f888b6ac092ae7df576af8bb9568c4e2";
		
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		 
	    // Build a filter for the MessageList
	    List<NameValuePair> params = new ArrayList<>();
	    params.add(new BasicNameValuePair("Body", mensagem));
	    params.add(new BasicNameValuePair("To", "+55" + destinatario));
	    params.add(new BasicNameValuePair("From", "+16176411232"));
	 
	    MessageFactory messageFactory = client.getAccount().getMessageFactory();
	    Message message;
		try {
			message = messageFactory.create(params);
			message.getSid();
		} catch (TwilioRestException e) {
			// Não é legal assim! Trate a exception
			e.printStackTrace();
			System.err.println("Erro enviando SMS - " + e.getMessage());
		}
	}
	
	private void notificarViaEmail(String destinatario, String mensagem) {
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
			// Não é legal assim! Trate a exception
			e.printStackTrace();
			System.err.println("Erro enviando e-mail - " + e.getMessage());
		}
	}

}
