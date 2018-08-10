package org.example.spring.integration.usuario;

import javax.xml.transform.Source;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.integration.bean.Cuenta;
import org.example.spring.integration.bean.CuentaSaldo;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.springframework.xml.transform.StringSource;

@Component
public class ResponseClienteCuentasServiceActivator {

	private static final Log LOG = LogFactory.getLog(ResponseClienteCuentasServiceActivator.class);
	
	@ServiceActivator
	public Source devuelveCuentas(final Cuenta cuenta) {
		LOG.info("ResponseClienteCuentasServiceActivator:::: Inicio devuelveCuentas");
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<cuentas>");
		for(CuentaSaldo cuentaSaldo : cuenta.getLista()) {
			add(cuentaSaldo,stringBuilder);
		}
		stringBuilder.append("</cuentas>");
		LOG.info("ResponseClienteCuentasServiceActivator:::: Fin devuelveCuentas");
		return new StringSource(stringBuilder.toString());
	}
	
	/**
	 * 
	 * @param cuentaSaldo
	 * @param stringBuilder
	 */
	private void add(CuentaSaldo cuentaSaldo, StringBuilder stringBuilder) {
		stringBuilder.append("<cuenta>").
		append("<cuenta>").append(cuentaSaldo.getCuenta()).append("</cuenta>").
		append("<moneda>").append(cuentaSaldo.getMoneda()).append("</moneda>").
		append("<saldo>").append(cuentaSaldo.getSaldo()).append("</saldo>").
		append("<entidadBancaria>").append(cuentaSaldo.getEntidadBancaria()).append("</entidadBancaria>").
		append("</cuenta>");	
	}
}
