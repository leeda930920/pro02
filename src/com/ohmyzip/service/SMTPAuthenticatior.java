package com.ohmyzip.service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticatior extends Authenticator{	
	@Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("ldy1257@naver.com","Elwkdls-2427");
    }
}