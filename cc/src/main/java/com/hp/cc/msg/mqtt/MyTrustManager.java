package com.hp.cc.msg.mqtt;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MyTrustManager implements TrustManager, X509TrustManager{

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		
	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		
	}

	public boolean isServerTrusted(X509Certificate[] certs) {
        return true;
    }
	
	public boolean isClientTrusted(X509Certificate[] certs) {
        return true;
    }
	
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
