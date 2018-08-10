Aplicacion usando spring-integration and spring-ws

Aplicativo compuesto por los modulos:
	appClienteEntidad 	 
		Expone un servicio SOAP que devuelve datos de un usuario, segun su DNI y codigo enviado
	appClienteCuentaRest 
		Expone un servicio rest que devuelve datos de una cuenta, segun el codigo de usario enviado.
	appClienteCuentas 	 
		Expone un servicio SOAP que devuelve datos de una cuenta, segun el codigo de usario enviado.
	spring-integration   
		Aplicaion que usa spring-integration que recupera los mensajes de los servicios SOAP y REST descritos.
		Se utilizan los conceptos de canal, ws:inbound-gateway , int:service-activator,ws:outbound-gateway,router,aggregator.
		
			
	
PASOS PARA LEVANTAR EL PROYECTO:

	 1. LEVANTAR EL PROYECTO appClienteEntidad
			WSDL:
			http://localhost:<PUERTO>/appClienteEntidad/endpoints/ClienteEntidadService.wsdl
			
	 2. LEVANTAR EL PROYECTO appClienteCuentas
			WSDL:
			http://localhost:<PUERTO>/appClienteCuentas/endpoints/ClienteCuentaService.wsdl
			
	 3. LEVANTAR EL PROYECTO appClienteCuentaRest
			http://localhost:<PUERTO>/appClienteCuentaRest/cuentas/2
			
	 4. Levantar el aplicativo spring-integration
	 
			http://localhost:<PUERTO>/spring-integration
			
			Trama para enviar un SOAP a la aplicacion:
				
				<?xml version="1.0" encoding="UTF-8"?>
				<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
					xmlns="http://www.example.org/ClienteCuenta">   
				   <soapenv:Body>
					  <usuario>
						  <DNI>42554231</DNI>
						  <CLAVE>1256</CLAVE>
					  </usuario>
				   </soapenv:Body>
				</soapenv:Envelope>
				
			la respuesta en un conjunto de cuentas con las que dispone dicho usuario, ejemplo (datos estaticos);
				 <cuenta>
					<cuenta>1021-254122121021</cuenta>
					<moneda>Soles</moneda>
					<saldo>1562.35</saldo>
					<entidadBancaria>BVVA</entidadBancaria>
				 </cuenta>
				 ......
				 .....
				 ....
				 .....
				 
				 
				 

		 