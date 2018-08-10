package org.example.spring.integration.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.example.spring.integration.rest.bean.Cuentas;
import org.example.spring.integration.rest.bean.CuentasSaldo;
import org.example.spring.integration.rest.service.ClienteCuentaService;
import org.springframework.stereotype.Service;

@Service
public class ClienteCuentaServiceImpl implements ClienteCuentaService{

	@Override
	public Cuentas obtenerDatosCuenta(String codCLiente) {
		Cuentas cuentas = new Cuentas();
		List<CuentasSaldo> lista = new ArrayList<CuentasSaldo>();
		CuentasSaldo cuentasSaldo = new CuentasSaldo("1021-254122121021", 1562.35f,"SOLES","SCOTIBANK");	
		lista.add(cuentasSaldo);	
		cuentas.setListaCuentas(lista);
		return cuentas;
	}

}
