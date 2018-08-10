package org.example.spring.integration.config;

import org.example.spring.integration.bean.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.ws.server.endpoint.MessageEndpoint;
import org.springframework.ws.server.endpoint.mapping.UriEndpointMapping;


@Configuration
public class SpringIntegrationConfiguration {

	@Value("#{wsInboungGateway}")
	private MessageEndpoint wsInboundGateway;

	@Bean
	public UriEndpointMapping uriEndpointMapping() {
		final UriEndpointMapping uriEndpointMapping = new UriEndpointMapping();
		uriEndpointMapping.setDefaultEndpoint(wsInboundGateway);
		return uriEndpointMapping;
	}

	@Bean
	public CastorMarshaller castorMarshaller() {
		CastorMarshaller castorMarshaller = new CastorMarshaller();
		castorMarshaller.setTargetClasses(new Class[] { Cliente.class });
		return castorMarshaller;
	}
	
}
