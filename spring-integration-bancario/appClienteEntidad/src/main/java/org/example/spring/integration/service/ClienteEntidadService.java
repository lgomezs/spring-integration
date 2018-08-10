package org.example.spring.integration.service;

import org.example.cliente.ClienteEntidadResponse;
import org.example.cliente.ClienteRequest;

public interface ClienteEntidadService {

	/**
	 *  busca un cliente por medio de su DNI y clave de 4
	 * @param cliente
	 * @return
	 */
	public ClienteEntidadResponse buscarClienteEntidad(ClienteRequest cliente);
}
