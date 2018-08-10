package org.example.spring.integration.cuenta.service.impl;

import java.util.List;

import org.example.cliente.CuentaSaldo;
import org.example.clientecuenta.ClienteCuentaResponse;

import org.example.spring.integration.cuenta.service.ClienteCuentaService;
import org.springframework.stereotype.Service;

@Service
public class ClienteCuentaServiceImpl implements ClienteCuentaService{

	@Override
	public ClienteCuentaResponse obtenerDatosCuenta(String codCLiente) {
		List<CuentaSaldo> listDatosCuenta = null;
		
		ClienteCuentaResponse clienteCuentaResponse = new ClienteCuentaResponse();
		listDatosCuenta = clienteCuentaResponse.getCuentaSaldo();
		
		CuentaSaldo _cCuentaSaldo = new CuentaSaldo();
		_cCuentaSaldo.setCuenta("1021-254122121021");
		_cCuentaSaldo.setSaldo((float)1562.35);
		_cCuentaSaldo.setMoneda("Soles");
		listDatosCuenta.add(_cCuentaSaldo);
		
		CuentaSaldo _cCuentaSaldo1 = new CuentaSaldo();
		_cCuentaSaldo1.setCuenta("1021-254122121888");
		_cCuentaSaldo1.setSaldo((float)122.35);
		_cCuentaSaldo1.setMoneda("DOLARES");
		listDatosCuenta.add(_cCuentaSaldo1);
		
		
		return clienteCuentaResponse;
	}

}
