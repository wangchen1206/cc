package com.hp.cc.config;

import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XmppConfig {
	
	@Bean
	public XMPPTCPConnectionConfiguration createXMPPTCPConnectionConfiguration() throws XmppStringprepException{
		XMPPTCPConnectionConfiguration configBuilder;
		configBuilder = XMPPTCPConnectionConfiguration.builder()
				.setXmppDomain("192.168.1.105")
				.setSecurityMode(SecurityMode.disabled)
//			.setSocketFactory(new DummySSLSocketFactory())
				.setConnectTimeout(45000)
//			.setCustomSSLContext(SSLContext.getDefault())
//			.setEnabledSSLProtocols(sslProtocols)
				.setHost("127.0.0.1").setPort(5222).setSendPresence(true).build();
		return configBuilder;
	}
}
