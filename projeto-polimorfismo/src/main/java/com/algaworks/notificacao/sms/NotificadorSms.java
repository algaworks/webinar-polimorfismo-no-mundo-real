package com.algaworks.notificacao.sms;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.algaworks.notificacao.Notificador;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class NotificadorSms implements Notificador {

	@Override
	public void notificar(String destinatario, String mensagem) {
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
			System.err.println("Erro enviando SMS - " + e.getMessage());
		}
	}

}
