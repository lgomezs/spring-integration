package org.example.spring.integration.rest.bean;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlType
@XmlRootElement(name="listaCuentas")
public class Cuentas implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@XmlElements({ @XmlElement(type = CuentasSaldo.class, name = "cuenta") })
	private List<CuentasSaldo> listaCuentas;
	
}
