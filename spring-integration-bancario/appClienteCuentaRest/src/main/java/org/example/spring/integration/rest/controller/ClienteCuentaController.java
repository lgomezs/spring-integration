package org.example.spring.integration.rest.controller;

import org.example.spring.integration.rest.bean.Cuentas;
import org.example.spring.integration.rest.service.ClienteCuentaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/cuentas")
public class ClienteCuentaController {

	
	private static final Logger LOG = LoggerFactory.getLogger(ClienteCuentaController.class);
	@Autowired
	private ClienteCuentaService clienteCuentaService;
		
	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	@ResponseBody
	public Cuentas showCustomer(@PathVariable String codigo) throws Exception {
		LOG.trace("Receiving codigo {}", codigo);		
	    try {
			return clienteCuentaService.obtenerDatosCuenta(codigo);
		} catch (Exception e) {			
			 e.printStackTrace();
			 throw new Exception(e.getMessage());
		}
	}	
	
}
