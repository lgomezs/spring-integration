package org.example.spring.integration.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaSaldo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cuenta;
	private float saldo;
	private String moneda;
	private String entidadBancaria;
}
