package org.example.spring.integration.ws.test;

import static org.springframework.ws.test.server.RequestCreators.withPayload;


import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class ClienteEntidadServiceTest {

	@Autowired
	private ApplicationContext applicationContext;
	private MockWebServiceClient mockClient;
	
	@Before
	public void creaCliente() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}
	
	@Test
	public void customerEndpoint() throws Exception {
		final Source requestPayload = new StringSource(
				"<ClienteRequest xmlns='http://www.example.org/cliente'><dni>42554161</dni><clave>1234</clave></ClienteRequest>");
		
		
		final Source responsePayload = new StringSource(				
				"      <ns2:ClienteEntidadResponse xmlns:ns2='http://www.example.org/cliente'> " + 
				"         <ns2:cliente> " + 
				"            <ns2:codigo>12541</ns2:codigo> " + 
				"            <ns2:nombre>luis miguel</ns2:nombre> " + 
				"            <ns2:apellido>gomez saavedra</ns2:apellido>  " + 
				"            <ns2:banco>BVVA</ns2:banco> " + 				
				"         </ns2:cliente>  " + 
				"         <ns2:cliente> " + 
				"            <ns2:codigo>1452</ns2:codigo> " + 
				"            <ns2:nombre>luis andres</ns2:nombre> " + 
				"            <ns2:apellido>gomez test</ns2:apellido>  " + 	
				"             <ns2:banco>SCOTIBANK</ns2:banco> " + 			
				"         </ns2:cliente>  " + 
				"      </ns2:ClienteEntidadResponse>  " 
			);
		
		mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));		
	}	
	
}
