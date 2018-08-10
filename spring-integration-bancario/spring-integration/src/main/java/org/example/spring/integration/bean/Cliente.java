package org.example.spring.integration.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dni;
	private String clave;	
}
