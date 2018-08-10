package org.example.spring.integration.usuario;

import java.util.Collection;
import java.util.List;

import org.example.spring.integration.bean.ClienteEntidad;
import org.springframework.integration.annotation.Splitter;
import org.springframework.stereotype.Component;

@Component
public class ClienteEntidadSplitter {

	@Splitter
	public Collection<ClienteEntidad> getSplit(List<ClienteEntidad> lista){
		return lista;
	}
}
