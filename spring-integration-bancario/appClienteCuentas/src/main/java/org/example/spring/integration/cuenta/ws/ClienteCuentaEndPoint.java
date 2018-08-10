package org.example.spring.integration.cuenta.ws;

import org.apache.log4j.Logger;
import org.example.clientecuenta.ClienteCuentaRequest;
import org.example.clientecuenta.ClienteCuentaResponse;
import org.example.spring.integration.cuenta.service.ClienteCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ClienteCuentaEndPoint {

	final static Logger LOG = Logger.getLogger(ClienteCuentaEndPoint.class);
	private static final String NAMESPACE_URI = "http://www.example.org/ClienteCuenta";
	@Autowired
	private ClienteCuentaService clienteCuentaService;
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="ClienteCuentaRequest")
	public @ResponsePayload ClienteCuentaResponse obtenerDatosCuentaCliente(@RequestPayload ClienteCuentaRequest cliente) {
		String codigo = cliente.getCodigo();
		ClienteCuentaResponse _cClienteCuentaResponse =null;
		if(codigo!=null && !String.valueOf(codigo).isEmpty()) {
			_cClienteCuentaResponse = clienteCuentaService.obtenerDatosCuenta(codigo);
		}
		return _cClienteCuentaResponse;
	}	
	
}
