package com.spring.spring.service;



import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.spring.spring.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg );


	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);


}
