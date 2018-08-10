package org.example.spring.integration.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteEntidad implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private String apellido;
	private String banco;
}
