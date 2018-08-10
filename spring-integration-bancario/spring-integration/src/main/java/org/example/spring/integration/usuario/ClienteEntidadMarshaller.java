package org.example.spring.integration.usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.integration.bean.Cliente;
import org.example.spring.integration.bean.ClienteEntidad;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.xml.transform.StringSource;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;


public class ClienteEntidadMarshaller implements Marshaller,Unmarshaller{

	private static final Log LOG = LogFactory.getLog(ClienteEntidadMarshaller.class);
	
	//Metodo que recibe los usuarios que entran por el canal y los transforma en XML para enviarselo al servicio clienteEntidad.
	@Override
	public void marshal(Object object, Result result) throws IOException, XmlMappingException {
		LOG.info("ClienteEntidadMarshaller ::: Inicio marshal");
		final Cliente cliente = (Cliente) object;
		
		final String xmlString = "<ClienteRequest xmlns='http://www.example.org/cliente'>"
				+ "<dni>" + cliente.getDni() + "</dni><clave>  " + cliente.getClave() + " </clave></ClienteRequest>";

		try {
			final Transformer transformer = new TransformerFactoryImpl().newTransformer();
			transformer.transform(new StringSource(xmlString), result);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		LOG.info("ClienteEntidadMarshaller ::: Fin marshal");
	}
	
	//Metodo que recibe la respuesta del servicio y lo tranforma en una lista ClienteEntidad 
	@Override
	public Object unmarshal(Source source) throws IOException, XmlMappingException {
		LOG.info("ClienteEntidadMarshaller ::: Inicio unmarshal");
		final NodeList nodeList = ((DOMSource)source).getNode().getChildNodes();
		LOG.debug("Receiving request " + nodeList + " length " + nodeList.getLength());		
		final List<ClienteEntidad> listClienteEntidades = new ArrayList<ClienteEntidad>();
		if (nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				final Node node = nodeList.item(i);
				LOG.debug("Nodename " + node.getNodeName());
				if (node.getLocalName().equals("cliente")) {
					listClienteEntidades.add(crearClienteEntidad(node));
				}
			}
		}	
		LOG.info("ClienteEntidadMarshaller ::: Fin unmarshal");
		return listClienteEntidades;
		
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private ClienteEntidad crearClienteEntidad(final Node node) {
		final NodeList childs = node.getChildNodes();
		String codigo = "";
		String nombre = "",apellido="",banco="";
		for (int i=0;i<childs.getLength(); i++) {
			final Node child = childs.item(i);
			if (child.getLocalName().equals("codigo")) {
				codigo = child.getTextContent();
			} else if (child.getLocalName().equals("nombre")) {
				nombre = child.getTextContent();
			}else if (child.getLocalName().equals("apellido")) {
				apellido = child.getTextContent();
			}else if (child.getLocalName().equals("banco")) {
				banco = child.getTextContent();
			}
		}
		return new ClienteEntidad(codigo, nombre,apellido,banco);		
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
