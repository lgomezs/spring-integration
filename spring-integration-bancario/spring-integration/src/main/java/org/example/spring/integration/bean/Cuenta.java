package org.example.spring.integration.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cuenta implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	private final List<CuentaSaldo> lista;
	
	public Cuenta() {
		lista = new ArrayList<CuentaSaldo>();
	}
	
	public void addCuenta(final CuentaSaldo cuenta) {
		lista.add(cuenta);
	}
	
}
