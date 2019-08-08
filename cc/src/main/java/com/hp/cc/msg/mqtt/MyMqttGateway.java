package com.hp.cc.msg.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

@Component
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MyMqttGateway {

	void sendToMqtt(String data);
}
