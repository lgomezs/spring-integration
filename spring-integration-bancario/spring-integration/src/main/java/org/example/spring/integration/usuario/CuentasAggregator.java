package org.example.spring.integration.usuario;

import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.integration.bean.Cuenta;
import org.example.spring.integration.bean.CuentaSaldo;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.stereotype.Component;



@Component
public class CuentasAggregator {

	private static final Log LOG = LogFactory.getLog(CuentasAggregator.class);
	
	@Aggregator
	public Cuenta agregarCuentas(List<Collection<CuentaSaldo>> listCuentaSaldo){
		LOG.info("CuentasAggregator :::: Inicio agregarCuentas");
		final Cuenta cuenta = new Cuenta();
		for(Collection<CuentaSaldo> cuentaSaldo: listCuentaSaldo) {
			LOG.info("Adding " + cuentaSaldo + " to final data");
			for(CuentaSaldo _cuenta :cuentaSaldo) {
				cuenta.addCuenta(_cuenta);
			}
		}
		LOG.info("CuentasAggregator :::: Fin agregarCuentas");
		return cuenta;
	}
}
