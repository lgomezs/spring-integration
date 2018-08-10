package org.example.spring.integration.usuario;

import javax.xml.transform.dom.DOMSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.integration.bean.Cliente;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
public class UsuarioEnpoint {

	private static final Log LOG = LogFactory.getLog(UsuarioEnpoint.class);
	
	@ServiceActivator
	public Cliente handleRequest(DOMSource source) throws Exception {
		final NodeList nodeList = source.getNode().getChildNodes();
		LOG.info("Receiving request " + nodeList + " length " + nodeList.getLength());
		String dni = null,clave = null;
		if (nodeList.getLength() > 1) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				final Node node = nodeList.item(i);
				LOG.debug("Nodename " + node.getNodeName());
				if (node.getNodeName().equals("DNI")) {
					dni = node.getTextContent();
				}else if(node.getNodeName().equals("CLAVE"))
					clave = node.getTextContent();
			}			
		}		
		return getCliente(dni,clave);		
	}
	
	
	private Cliente getCliente(String dni,String clave) {
		Cliente _cliente= new Cliente();
		_cliente.setDni(dni);
		_cliente.setClave(clave);
		return _cliente;
	}
}
