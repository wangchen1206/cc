package com.hp.cc.msg.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@IntegrationComponentScan
public class MqttOutboundConfiguration {

	private static final String URL = "tcp://localhost:1883";
	private static final String USERNAME = "test1";
	private static final String PASSWORD = "123";
	private static final String CLIENTID = "clientId1";
	private static final String topic = "topic1";

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		MqttConnectOptions options = new MqttConnectOptions();
		options.setServerURIs(new String[] { URL });
		options.setUserName(USERNAME);
		options.setPassword(PASSWORD.toCharArray());
		options.setCleanSession(false);
		factory.setConnectionOptions(options);
		return factory;

	}

	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel")
	public MessageHandler mqttOutbound() {
		MqttPahoMessageHandler handler = new MqttPahoMessageHandler(CLIENTID, mqttClientFactory());
		handler.setAsync(true);
		handler.setDefaultTopic(topic);
		return handler;
	}
	
	@Bean
	public MessageChannel mqttOutboundChannel() {
		return new DirectChannel();
	}
	
	
	
}
