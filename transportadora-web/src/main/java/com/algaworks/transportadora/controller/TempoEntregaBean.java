package com.algaworks.transportadora.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.transportadora.entrega.Entrega;
import com.algaworks.transportadora.entrega.TipoEntrega;
import com.algaworks.transportadora.model.Destino;
import com.algaworks.transportadora.notificacao.NotificacaoException;
import com.algaworks.transportadora.notificacao.TipoNotificador;
import com.algaworks.transportadora.repository.Destinos;
import com.algaworks.transportadora.util.jsf.FacesUtil;

@Named
@ViewScoped
public class TempoEntregaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Destinos destinos;
	
	@Inject @Any
	private Instance<Entrega> entregas;
	
	// Listas para os selects
	private List<Destino> todasDestinos;
	private List<TipoEntrega> tiposEntregas;
	private List<TipoNotificador> tiposNotificadores;
	
	// Opções selecionadas pelos usuários
	private Destino destinoSelecionado;
	private TipoEntrega tipoEntregaSelecionada;
	private TipoNotificador tipoNotificadorSelecionado;
	private String destinatario;
	
	@PostConstruct
	public void init() {
		this.todasDestinos = destinos.todos();
		this.tiposEntregas = Arrays.asList(TipoEntrega.values());
		this.tiposNotificadores = Arrays.asList(TipoNotificador.values());
	}
	
	public void registrar() {
		Entrega entrega = tipoEntregaSelecionada.obterEntrega(entregas);
		try {
			Integer tempoParaEntrega = entrega
						.registrarEncomenda(destinatario, destinoSelecionado, tipoNotificadorSelecionado);
			FacesUtil.addSuccessMessage("Encomenda registrada com sucesso. Serão " + tempoParaEntrega + " dia(s).");
		} catch (NotificacaoException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}
	
	public List<Destino> getTodosDestinos() {
		return todasDestinos;
	}

	public List<TipoEntrega> getTiposEntregas() {
		return tiposEntregas;
	}

	public List<TipoNotificador> getTiposNotificadores() {
		return tiposNotificadores;
	}

	public TipoNotificador getTipoNotificadorSelecionado() {
		return tipoNotificadorSelecionado;
	}

	public void setTipoNotificadorSelecionado(
			TipoNotificador tipoNotificadorSelecionado) {
		this.tipoNotificadorSelecionado = tipoNotificadorSelecionado;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public TipoEntrega getTipoEntregaSelecionada() {
		return tipoEntregaSelecionada;
	}

	public void setTipoEntregaSelecionada(TipoEntrega tipoEntregaSelecionada) {
		this.tipoEntregaSelecionada = tipoEntregaSelecionada;
	}

	public Destino getDestinoSelecionado() {
		return destinoSelecionado;
	}

	public void setDestinoSelecionado(Destino destinoSelecionado) {
		this.destinoSelecionado = destinoSelecionado;
	}

}
