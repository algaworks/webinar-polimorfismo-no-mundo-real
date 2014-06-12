package com.algaworks.gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.algaworks.entrega.TipoEntrega;
import com.algaworks.notificacao.TipoNotificador;
import com.algaworks.repositorio.Destinos;

public class Gui {
	
	public static String obterCidade() {
		Object[] possibilities = (Object[]) new Destinos().todos().toArray();
		return (String) JOptionPane.showInputDialog(null,
				"Selecione o destino", "Destino", JOptionPane.PLAIN_MESSAGE,
				null, possibilities, "São Paulo");
	}

	public static TipoEntrega obterTipoEntrega() {
		TipoEntrega[] options = TipoEntrega.values();
		int n = JOptionPane.showOptionDialog(null, "Qual o tipo de entrega?",
				"Tipo de Entrega", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return TipoEntrega.values()[n];
	}
	
	public static TipoNotificador obterTipoNotificador() {
		TipoNotificador[] options = TipoNotificador.values();
		int n = JOptionPane.showOptionDialog(null, "Qual o tipo de notificação?",
				"Tipo de Notificação", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return TipoNotificador.values()[n];
	}
	
	public static String obterDestinatario() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Informe o destinatário:");
		JPasswordField pass = new JPasswordField(30);
		panel.add(label);
		panel.add(pass);
		String[] options = new String[]{"OK"};
		JOptionPane.showOptionDialog(null, panel, "Destinatário",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, "E-mail ou telefone");
		char[] password = pass.getPassword();
	    return new String(password);
	}
	
	public static void mostrarResumo(Integer tempoEnvio) {
		JOptionPane.showMessageDialog(null,
			    String.format("O tempo para envio dessa encomenda será de %d dia(s).", tempoEnvio),
			    "Tempo entrega",
			    JOptionPane.PLAIN_MESSAGE);
	}
	
}
