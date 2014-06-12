package com.algaworks.transportadora.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.transportadora.model.Destino;
import com.algaworks.transportadora.repository.Destinos;
import com.algaworks.transportadora.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Destino.class)
public class DestinoConverter implements Converter {

	private Destinos destinos;

	public DestinoConverter() {
		this.destinos = CDIServiceLocator.getBean(Destinos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Destino destino = null;

		if (value != null) {
			destino = this.destinos.peloCodigo(new Long(value));
		}

		return destino;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Long codigo = ((Destino) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}
