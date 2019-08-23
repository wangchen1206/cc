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

import com.hp.cc.util.SslUtil;

@Configuration
@IntegrationComponentScan
public class MqttOutboundConfiguration {

//	private static final String URL = "tcp://localhost:1883";
	private static final String URL = "ssl://localhost:8883";
	private static final String USERNAME = "test1";
	private static final String PASSWORD = "123";
	private static final String CLIENTID = "clientId1";
	private static final String topic = "topic1";
	private static final String cerPath = "C:\\soft\\emq\\etc\\cer\\";
	private static final String cerPawd = "123456";

	@Bean
	public MqttConnectOptions mqttConnectOptions() {
		/**
		 * debug 模式 方便调试
		 */
		System.setProperty("javax.net.debug", "all");
		
		MqttConnectOptions options = new MqttConnectOptions();
		options.setServerURIs(new String[] { URL });
		options.setUserName(USERNAME);
		options.setPassword(PASSWORD.toCharArray());
		options.setCleanSession(false);

		// SSL
		try {
			options.setSocketFactory(SslUtil.getSocketFactory(cerPath + "ca.crt", cerPath + "client.crt",
					cerPath + "client.key", cerPawd));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return options;
	}

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setConnectionOptions(mqttConnectOptions());
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
