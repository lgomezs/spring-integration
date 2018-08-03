package org.springframework.integration.samples.ws;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import javax.xml.transform.Source;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;


public class InContainerTests {

	private static Log logger = LogFactory.getLog(InContainerTests.class);
	private static final String WS_URI = "http://localhost:8085/ws-inbound.gateway/echoservice";
	private final WebServiceTemplate template = new WebServiceTemplate();

	@Test
	public void testWebServiceRequestAndResponse() {
		StringResult result = new StringResult();
		Source payload = new StringSource(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<echoRequest xmlns=\"http://www.springframework.org/spring-ws/samples/echo\">hello</echoRequest>");

		template.sendSourceAndReceiveToResult(WS_URI, payload, result);
		logger.info("RESULT: " + result.toString());
		assertThat(result.toString(), equalTo(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<echoResponse xmlns=\"http://www.springframework.org/spring-ws/samples/echo\">hello</echoResponse>"));
	}
	
}
