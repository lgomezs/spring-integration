package org.springframework.integration.samples.ws;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import org.springframework.integration.xml.source.DomSourceFactory;
import org.springframework.stereotype.Component;

@Component
public class SimpleEchoResponder {

	public Source issueResponseFor(DOMSource request) {
		return new DomSourceFactory().createSource(
				"<echoResponse xmlns=\"http://www.springframework.org/spring-ws/samples/echo\">" +
				request.getNode().getTextContent() + "</echoResponse>");
	}	
	
}
