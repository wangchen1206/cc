package com.hp.cc;

import java.time.LocalDateTime;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.hp.cc.util.MD5Util;


/**
 * @author ck
 * @date 2019年6月5日 下午4:49:40
 */
public class MQTTPub {

	String TOPIC125 = "cc/topic2";
	int qos = 2;
	 String broker = "tcp://localhost:1883";
//	String broker = "tcp://118.184.208.75:1883";
	String clientId = "clientId_Pub";

	private MqttClient client;
	private MqttTopic topic125;
	private MqttMessage message;

	public MQTTPub() throws MqttException {
		// MemoryPersistence设置clientid的保存形式，默认为以内存保存
		client = new MqttClient(broker, clientId, new MemoryPersistence());
		connect();
	}

	private void connect() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(false);
//		options.setUserName("00010000010000000008");
//		options.setPassword("6696f234".toCharArray());
		options.setUserName("test3");
		options.setPassword("123".toCharArray());
		// 设置超时时间
		options.setConnectionTimeout(10);
		// 设置会话心跳时间
		options.setKeepAliveInterval(20);
		client.setCallback(new PushCallback(client));
		try {
			client.connect(options);
		} catch (MqttException e) {
			e.printStackTrace();
		}
		topic125 = client.getTopic(TOPIC125);
	}

	public void publish(MqttTopic topic, MqttMessage message)
			throws MqttPersistenceException, MqttException {
		MqttDeliveryToken token = topic.publish(message);
		token.waitForCompletion();
		System.out.println(
				"message is published completely! " + token.isComplete());
	}

	public static void main(String[] args) throws MqttException {
		MQTTPub server = new MQTTPub();
		
		
		System.out.println(MD5Util.md5Encode("test", null));

		// server.message = new MqttMessage();
		// server.message.setQos(2);
		// server.message.setRetained(true);
		// server.message.setPayload((new Date()+"给客户端2推送的信息").getBytes());
		// server.publish(server.topic, server.message);
		
		server.message = new MqttMessage();
		server.message.setQos(1);
		server.message.setRetained(true);
//		server.message.setPayload("{'randomId':'qkqq787grrdddff07-4cdc-a043-47d75d48caa0','board':{'setupUrl':'','macAddress':'02:92:1b:55:dc:58','printers':[{'serialNumber':'VNC3D00737','cartridges':[{'serialNumber':'234947818','level':50,'familyName':'Black toner supply','pagesUsed':0,'productNumber':'CF231A','state':'ok','consumableType':'toner','estimatedPagesRemaining':5000},{'serialNumber':'235078527','level':100,'familyName':'OPC supply','pagesUsed':0,'productNumber':'CF232A','state':'ok','consumableType':'imageDrum','estimatedPagesRemaining':23000}],'color':false,'nickName':'','ipv4Address':'','trays':[{'size':'any','duplex':true,'trayIndex':1,'type':'auto'}],'counter':{'jamEvents':1,'totalImpressions':2275,'copyImpressions':116,'duplexSheets':139},'productNumber':'G3Q76A','uuid':'564E4333-4430-3037-3337-40B0349F1A0A','sid':0,'upTime':1420178629,'scanner':{'jamEvents':0,'AdfImages':0,'FlatbedImages':0},'eventlog':{'eventCode':'13.0201','pageNumber':'1567'},'isUsbConnected':true,'model':'HP LaserJet Ultra MFP M230sdn','status':'192.168.3.90'}],'hwversion':'1.0.0','latitude':0.0,'ipv4Address':'192.168.3.130','supportUrl':'','model':'m0.1','pid':'11351','firmware':'1.0.2','uuid':'aad2422a-68a0-4882-b5c2-02921b55dc58','longitude':0.0},'command':'board'}".getBytes());
		server.message.setPayload((LocalDateTime.now()+"test data").getBytes());
		server.publish(server.topic125, server.message);

		System.out.println(server.message.isRetained() + "------ratained状态");

	}
}
