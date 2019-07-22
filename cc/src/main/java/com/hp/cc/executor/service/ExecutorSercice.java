package com.hp.cc.executor.service;

import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hp.cc.entity.SysUser;


/**
 * @author ck
 * @date 2019年3月13日 下午5:01:05
 */
@Service
public class ExecutorSercice {

	static int port = 5222;
	static String host = "127.0.0.1";
	static String SERVERNAME3 = "wachen7.auth.hpicorp.net";
	static String[] sslProtocols = {"TLS", "TLSv1", "TLSv1.1", "TLSv1.2"};
	
	@Resource(name="getAsyncExecutor")
	private Executor executor;
	
	@Async
	public void executeAsyncTask(SysUser user) throws Exception{
//		XMPPTCPConnectionConfiguration configBuilder;
//		configBuilder = XMPPTCPConnectionConfiguration.builder()
//				.setXmppDomain(SERVERNAME3)
//				.setUsernameAndPassword(user.getUsername(), user.getPassword())
//				.setSecurityMode(SecurityMode.required)
////				.setSocketFactory(new DummySSLSocketFactory())
//				.setConnectTimeout(45000)
//				.setCustomSSLContext(SSLContext.getDefault())
////				.setEnabledSSLProtocols(sslProtocols)
//				.setHost(host).setPort(port).setSendPresence(true).build();
//		XMPPTCPConnection connection = new XMPPTCPConnection(configBuilder);
//		connection.connect().login();
//		String msg = "heart beat";
//		while (true) {
//			sendMsg(connection, user.getUsername()+"@" + SERVERNAME3,
//					"adminjob@" + SERVERNAME3, msg, Type.chat);
//		}
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
	
	@Async
	public void testTask1(Integer integer){
		System.out.println(Thread.currentThread().getName()+"任务"+integer);
	}
}
