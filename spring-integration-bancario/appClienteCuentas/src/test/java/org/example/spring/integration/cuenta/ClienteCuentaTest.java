package org.example.spring.integration.cuenta;

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
public class ClienteCuentaTest {

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
				"<ClienteCuentaRequest  xmlns='http://www.example.org/ClienteCuenta'><codigo>12541</codigo></ClienteCuentaRequest>");
		
		
		final Source responsePayload = new StringSource(				
				 "      <ns3:ClienteCuentaResponse  xmlns:ns3='http://www.example.org/ClienteCuenta'> " + 
				 "		   <ns3:cuentaSaldo>  " +
				 "           <ns3:cuenta>1021-254122121021</ns3:cuenta>  " +
				 "           <ns3:saldo>1562.35</ns3:saldo> " +
				 "           <ns3:moneda>Soles</ns3:moneda> " +
				 "         </ns3:cuentaSaldo> " +
				 "		   <ns3:cuentaSaldo> " +
				 "           <ns3:cuenta>1021-254122121888</ns3:cuenta> " +
				 "           <ns3:saldo>122.35</ns3:saldo> " +
				 "           <ns3:moneda>DOLARES</ns3:moneda> " +
				 "         </ns3:cuentaSaldo>  " +	
				"      </ns3:ClienteCuentaResponse>  " 
			);
		
		mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload));		
	}	
	
}
