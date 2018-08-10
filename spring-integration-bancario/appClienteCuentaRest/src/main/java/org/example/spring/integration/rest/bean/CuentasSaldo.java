package org.example.spring.integration.rest.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="cuenta")
public class CuentasSaldo implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String cuenta;
	private float saldo;
	private String moneda;
	private String entidadBancaria;
	
}
