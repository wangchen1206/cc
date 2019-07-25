package com.hp.cc;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.springframework.util.Assert;

import com.hp.cc.config.TreadPoolConfig;
import com.hp.cc.entity.Msg;

import lombok.val;

/**
 * @author ck
 * @date 2019年2月28日 下午2:38:53
 */
public class TestSecurity {

	private final String[] CanPrintStatus = { "Ready", "Sleep", "Cartridge(\\s+)(\\D*)(\\s+)Low", "Used cartridge" };

	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TreadPoolConfig.class);

	
	
	@Test
	public void  testString() {
		String urlString = "https://s3.cn-east-1.jdcloud-oss.com/blue-oss/c154c92c3ed8a9238efda344b57754e4.docx";
		String ossString = "https://s3.cn-east-1.jdcloud-oss.com/blue-oss";
		System.out.println(urlString.contains(ossString));
	}
	
	@Test
	public void testRegex() {
//		String pattern = "Cartridge(\\s+)(\\D*)(\\s+)Low";
////		String pattern1 = "[Cc][Aa][Rr][]";
		String input = "read";
		String input1 = "Cartridge Yellow Low";
		String input3 = "used cartridge";
		String input2 = "Sleep";
//		System.out.println(Pattern.matches(pattern, input));
//		System.out.println(Pattern.matches(pattern, input1));
//		System.out.println(Pattern.matches(pattern, input2));
//		
//		Pattern pattern2 = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
//		Matcher matcher = pattern2.matcher(input2);
//		while (matcher.find()) {
//			System.out.println("ignore match successfully "+true);
//			System.out.println(matcher.group()+" 位置["+matcher.start()+","+matcher.end()+"]");
//		}

//		Arrays.asList(CanPrintStatus).stream().forEach(canPrintStatus -> {
//			System.out.println(canPrintStatus);
//			Pattern pattern = Pattern.compile(canPrintStatus, Pattern.CASE_INSENSITIVE);
//			Matcher matcher = pattern.matcher(input);
//			if (matcher.find()) {
//				Assert.isTrue(true, "The printer's status is " + input + " so it can not work now !");
//			} else {
//
//			}
//		});
//		int count = (int) Arrays.asList(CanPrintStatus).stream().filter(canPrintStatus -> canPrint(canPrintStatus, input))
//				.count();

		long count = Arrays.asList(CanPrintStatus).stream().filter(canPrintStatus -> {
			Pattern pattern = Pattern.compile(canPrintStatus, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(input);
			boolean flag = matcher.find();
			if (flag && canPrintStatus.length() == input.length() && !canPrintStatus.equals(CanPrintStatus[2])) {
				return true;
			}
			if (canPrintStatus.equals(CanPrintStatus[2]) && flag) {
				return true;
			}
			return false;
		}).count();
		Assert.isTrue(count > 0, "The printer's status is " + input + " so it can not work now !");

	}

	public boolean canPrint(String canPrintStatus, String input) {
		Pattern pattern = Pattern.compile(canPrintStatus, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		boolean flag = matcher.find();
		System.out.println(flag);
		if (flag && canPrintStatus.length() == input.length() && !canPrintStatus.equals(CanPrintStatus[2])) {
			return true;
		}
		if (canPrintStatus.equals(CanPrintStatus[2]) && flag) {
			return true;
		}
		return false;
	}

	@Test
	public void testBCryptPassword() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("admin").trim());
		System.out.println(bCryptPasswordEncoder.encode("abel").trim());
	}

	@Test
	public void testAssert() {
//		Assert.doesNotContain("sss", "s", "does not contain");
		if (false) {

		} else {
			Assert.isTrue(false, "the url doesn't match JDOSS Url");
		}
//		Assert.isTrue(true, "not true");

	}

	@Test
	public void testAssert1() {
		Msg msg = new Msg();
		if (null == msg) {
			msg = new Msg();
		} else {
			System.out.println(msg);
		}
	}

	static int port = 5222;
	static String host = "127.0.0.1";
	static String SERVERNAME3 = "wachen7.auth.hpicorp.net";
	static String[] sslProtocols = { "TLS", "TLSv1", "TLSv1.1", "TLSv1.2" };

	@Test
	public void testXmpp() throws Exception {
		XMPPTCPConnectionConfiguration configBuilder;
		configBuilder = XMPPTCPConnectionConfiguration.builder().setXmppDomain(SERVERNAME3)
				.setUsernameAndPassword("admin_pms", "123").setSecurityMode(SecurityMode.required)
//				.setSocketFactory(new DummySSLSocketFactory())
				.setConnectTimeout(45000).setCustomSSLContext(SSLContext.getDefault())
//				.setEnabledSSLProtocols(sslProtocols)
				.setHost(host).setPort(port).setSendPresence(true).build();
		XMPPTCPConnection connection = new XMPPTCPConnection(configBuilder);
		connection.connect().login();
		String msg = "heart beat";
		while (true) {
			sendMsg(connection, "admin_pms@" + SERVERNAME3, "adminjob@" + SERVERNAME3, msg, Type.chat);
		}
	}

	public static void sendMsg(XMPPTCPConnection connection, String sendfrom, String sendTo, String msg, Type type)
			throws Exception {

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
