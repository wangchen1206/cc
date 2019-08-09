package com.hp.cc.msg.mqtt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import lombok.extern.slf4j.Slf4j;

@Configuration
@IntegrationComponentScan
@Slf4j
public class MqttInboundConfiguration {
	
	private static final String CLIENTID = "clientId1";
	private static final String URL = "tcp://localhost:1883";
	/**
	 * # 匹配 cc/a   cc/a/b/c
	 */
//	private static final String topic = "cc/#";
	/**
	 * + 匹配单个  只能是   cc/a cc/b
	 */
	private static final String topic = "cc/+";

	
	@Autowired
	private MqttPahoClientFactory mqttPahoClientFactory;
	
	/**
	 * 配置通道 
	 * @return
	 */
	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}
	
	/**
	 * 配置消息适配器
	 * @return
	 */
	@Bean
	public MessageProducer inbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(URL,CLIENTID,mqttPahoClientFactory,topic);
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(1);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}
	
	
	/**
	 * 配置服务激活器 接收到消息然后处理
	 * @return
	 */
	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return new MessageHandler() {
			
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				log.debug("接收到消息，然后开始进行业务处理");
				log.info("the message is :"+message.getPayload());
			}
		};
	}
	
}
