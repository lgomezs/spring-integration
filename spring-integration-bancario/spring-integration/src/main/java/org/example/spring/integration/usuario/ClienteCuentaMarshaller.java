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
import org.example.spring.integration.bean.ClienteEntidad;
import org.example.spring.integration.bean.CuentaSaldo;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.xml.transform.StringSource;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

public class ClienteCuentaMarshaller implements Marshaller,Unmarshaller {

	private static final Log LOG = LogFactory.getLog(ClienteCuentaMarshaller.class);
	private String entidadBancaria;
	
	@Override
	public void marshal(Object object, Result result) throws IOException, XmlMappingException {
		LOG.info("ClienteCuentaMarshaller ::: Inicio marshal");
		final ClienteEntidad cliente = (ClienteEntidad)object;
		entidadBancaria=cliente.getBanco();
		
		final String xmlString = "<ClienteCuentaRequest xmlns='http://www.example.org/ClienteCuenta'> "
				+ "<codigo>" +  cliente.getCodigo() + "</codigo></ClienteCuentaRequest>";

		try {
			final Transformer transformer = new TransformerFactoryImpl().newTransformer();
			transformer.transform(new StringSource(xmlString), result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("ClienteCuentaMarshaller ::: Fin marshal");
		
	}
	
	
	@Override
	public Object unmarshal(Source source) throws IOException, XmlMappingException {
		LOG.info("ClienteCuentaMarshaller ::: Inicio unmarshal");
		final Node cuentasNode = ((DOMSource)source).getNode();
		LOG.debug("Receiving request " + cuentasNode + " length ");

		final List<CuentaSaldo> cuentaSaldos = new ArrayList<CuentaSaldo>();
		if (cuentasNode.getLocalName().equals("ClienteCuentaResponse")) {
			final NodeList cuentas = cuentasNode.getChildNodes();
			for (int i = 0; i < cuentas.getLength(); i++) {
				final Node cuenta = cuentas.item(i);
				LOG.debug("Nodename " + cuenta.getNodeName());				
				cuentaSaldos.add(createCuentaSaldo(cuenta));				
			}

		}
		LOG.info("ClienteCuentaMarshaller ::: Fin unmarshal");
		return cuentaSaldos;
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private CuentaSaldo createCuentaSaldo(final Node node) {
		final NodeList childs = node.getChildNodes();
		String cuenta = "",moneda="";
		float saldo = 0 ;
		for (int i = 0; i < childs.getLength(); i++) {
			final Node child = childs.item(i);
			if (child.getLocalName().equals("cuenta")) {
				cuenta = child.getTextContent();
			} else if (child.getLocalName().equals("saldo")) {
				saldo = Float.parseFloat(child.getTextContent());
			}else if (child.getLocalName().equals("moneda")) {
				moneda = child.getTextContent();
			}
		}
		return new CuentaSaldo(cuenta, saldo,moneda,entidadBancaria);		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
