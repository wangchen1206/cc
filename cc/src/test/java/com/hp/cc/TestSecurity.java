package com.hp.cc;

import javax.net.ssl.SSLContext;

import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hp.cc.config.TreadPoolConfig;

/**
 * @author ck
 * @date 2019年2月28日 下午2:38:53
 */
public class TestSecurity {
	
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
			TreadPoolConfig.class);
	

	@Test
	public void testBCryptPassword(){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("admin").trim());
		System.out.println(bCryptPasswordEncoder.encode("abel").trim());
	}
	
	static int port = 5222;
	static String host = "127.0.0.1";
	static String SERVERNAME3 = "wachen7.auth.hpicorp.net";
	static String[] sslProtocols = {"TLS", "TLSv1", "TLSv1.1", "TLSv1.2"};

	@Test
	public void testXmpp() throws Exception {
		XMPPTCPConnectionConfiguration configBuilder;
		configBuilder = XMPPTCPConnectionConfiguration.builder()
				.setXmppDomain(SERVERNAME3)
				.setUsernameAndPassword("admin_pms", "123")
				.setSecurityMode(SecurityMode.required)
//				.setSocketFactory(new DummySSLSocketFactory())
				.setConnectTimeout(45000)
				.setCustomSSLContext(SSLContext.getDefault())
//				.setEnabledSSLProtocols(sslProtocols)
				.setHost(host).setPort(port).setSendPresence(true).build();
		XMPPTCPConnection connection = new XMPPTCPConnection(configBuilder);
		connection.connect().login();
		String msg = "heart beat";
		while (true) {
			sendMsg(connection, "admin_pms@" + SERVERNAME3,
					"adminjob@" + SERVERNAME3, msg, Type.chat);
		}
	}

	public static void sendMsg(XMPPTCPConnection connection, String sendfrom,
			String sendTo, String msg, Type type) throws Exception {

		try {
			int i = 200;
			Message message = new Message();
			message.setBody(msg);
			message.setType(type);// 离线支持
			DeliveryReceiptRequest deliveryReceiptRequest = new DeliveryReceiptRequest();
			message.addExtension(deliveryReceiptRequest);
			connection.sendStanza(message);

			try {
				Thread.sleep(1000);
				System.out.println(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} catch (NotConnectedException e) {
			e.printStackTrace();
		}
	}
}
