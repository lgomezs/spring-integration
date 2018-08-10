package org.example.spring.integration.service.impl;

import java.util.List;

import org.example.cliente.ClienteEntidadResponse;
import org.example.cliente.ClienteRequest;
import org.example.cliente.ClienteType;
import org.example.spring.integration.service.ClienteEntidadService;
import org.springframework.stereotype.Service;

@Service
public class ClienteEntidadServiceImpl implements ClienteEntidadService{

	@Override
	public ClienteEntidadResponse buscarClienteEntidad(ClienteRequest cliente) {
		ClienteEntidadResponse _clienteEntidadResponse = new ClienteEntidadResponse();
		List<ClienteType> lista= _clienteEntidadResponse.getCliente();
		
		ClienteType clienteType = new ClienteType();
		clienteType.setCodigo("12541");
		clienteType.setApellido("gomez saavedra");
		clienteType.setNombre("luis miguel");
		clienteType.setBanco("BVVA");
		
		ClienteType clienteType1 = new ClienteType();
		clienteType1.setCodigo("1452");
		clienteType1.setApellido("gomez test");
		clienteType1.setNombre("luis andres");
		clienteType1.setBanco("SCOTIBANK");
		
		lista.add(clienteType);
		lista.add(clienteType1);
		
		return _clienteEntidadResponse;
	}

}
