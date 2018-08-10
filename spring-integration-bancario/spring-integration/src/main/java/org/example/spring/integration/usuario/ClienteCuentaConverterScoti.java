package org.example.spring.integration.usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.dom.DOMSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.spring.integration.bean.CuentaSaldo;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.integration.xml.source.DomSourceFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


//clase qu inova al servicio rest para recuperar las cuentas respectivas de un usuario
@Component
public class ClienteCuentaConverterScoti implements HttpMessageConverter<List<CuentaSaldo>>{

	private static final Log LOG = LogFactory.getLog(ClienteCuentaConverterScoti.class);
	
	private static final List<MediaType> SUPPORTED_MEDIA_TYPES = new ArrayList<MediaType>();
	static {
		SUPPORTED_MEDIA_TYPES.add(MediaType.APPLICATION_XML);
		SUPPORTED_MEDIA_TYPES.add(MediaType.TEXT_XML);
	}
	
	
	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return clazz.equals(String.class);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return SUPPORTED_MEDIA_TYPES;
	}

	@Override
	public List<CuentaSaldo> read(Class<? extends List<CuentaSaldo>> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		final String xmlResponse = getResponse(inputMessage.getBody());		
		return getCuentas(xmlResponse);
	}

	private List<CuentaSaldo> getCuentas(final String response) {
		final List<CuentaSaldo> cuentas = new ArrayList<CuentaSaldo>();
		final DOMSource source = (DOMSource) new DomSourceFactory().createSource(response);
		final NodeList nodelist = source.getNode().getChildNodes();
		
		final Node cuentaNode = nodelist.item(0);
		if (cuentaNode.getLocalName().equals("listaCuentas")) {			
			for (int i=0; i<nodelist.getLength(); i++) {
				final Node cuenta = nodelist.item(i);
				cuentas.add(getCuentaSaldo(cuenta));
			}
		}		
		return cuentas;
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private CuentaSaldo getCuentaSaldo(final Node node) {
		final CuentaSaldo cuentaSaldo = new CuentaSaldo();
		String cuenta = "",moneda="",entidadBancaria="";
		float saldo = 0 ;
		if (node.getLocalName().equals("listaCuentas")) {
			final NodeList cuentaAndSaldo = node.getChildNodes();
			for (int i=0; i<cuentaAndSaldo.getLength(); i++) {
				final Node nodeCuenta = cuentaAndSaldo.item(i);
				if (nodeCuenta.getLocalName().equals("cuenta")) {
					cuenta = nodeCuenta.getTextContent();
				} else if (nodeCuenta.getLocalName().equals("moneda")) {
					moneda = nodeCuenta.getTextContent();
				}else if (nodeCuenta.getLocalName().equals("saldo")) {
					saldo = Float.parseFloat(nodeCuenta.getTextContent());
				}else if (nodeCuenta.getLocalName().equals("entidadBancaria")) {
					entidadBancaria = nodeCuenta.getTextContent();
				}
			}
		}
		cuentaSaldo.setCuenta(cuenta);
		cuentaSaldo.setSaldo(saldo);
		cuentaSaldo.setMoneda(moneda);
		cuentaSaldo.setEntidadBancaria(entidadBancaria);
		return cuentaSaldo;
	}
	
	
	private String getResponse(InputStream inputStream) throws IOException {
		if (inputStream != null) {
			final Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				final Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				inputStream.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}
	
	
	@Override
	public void write(List<CuentaSaldo> t, MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
	}

}
