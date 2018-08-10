package org.example.spring.integration.ws;

import org.apache.log4j.Logger;
import org.example.cliente.ClienteEntidadResponse;
import org.example.cliente.ClienteRequest;
import org.example.spring.integration.service.ClienteEntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;



@Endpoint
public class ClienteEntidadServiceEndPoint {

	final static Logger LOG = Logger.getLogger(ClienteEntidadServiceEndPoint.class);
	private static final String NAMESPACE_URI = "http://www.example.org/cliente";
	@Autowired
	private ClienteEntidadService clienteEntidadService;	
	
	@PayloadRoot(namespace=NAMESPACE_URI,localPart="ClienteRequest")
	public @ResponsePayload ClienteEntidadResponse obtenerDatosCliente(@RequestPayload ClienteRequest cliente) throws Exception{
		LOG.info("Inicio obtenerDatosCliente");		
		 try {
			 
			 final String dni = cliente.getDni();
			 final String clave = cliente.getClave();
			 
			LOG.info("dni ingresado : "+ dni);
			LOG.info("clave ingresada : "+ clave);
			
			if(!String.valueOf(dni).isEmpty() && !String.valueOf(clave).isEmpty()) {
				return clienteEntidadService.buscarClienteEntidad(cliente);
			}
			
			 throw new Exception("datos de cliente no validos");	
		} catch (Exception exception) {
			 throw new Exception(exception);			 
		}
	}

}
