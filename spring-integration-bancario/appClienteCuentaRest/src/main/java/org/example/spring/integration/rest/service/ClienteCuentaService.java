package org.example.spring.integration.rest.service;

import org.example.spring.integration.rest.bean.Cuentas;


public interface ClienteCuentaService {

	/**
	 * Metodo que devuelve las cuentas del cliente
	 * @param codCLiente
	 * @return
	 */
	public Cuentas obtenerDatosCuenta(String codCLiente)throws Exception;
}
