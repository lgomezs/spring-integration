package org.example.spring.integration.cuenta.service;

import java.util.List;

import org.example.clientecuenta.ClienteCuentaResponse;

public interface ClienteCuentaService {

	/**
	 * Metodo que devuelve las cuentas del cliente
	 * @param codCLiente
	 * @return
	 */
	public ClienteCuentaResponse obtenerDatosCuenta(String codCLiente);
}
