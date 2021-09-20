package com.example.spring_reddit_clone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.example.spring_reddit_clone.exception.SpringRedditCloneException;
import com.example.spring_reddit_clone.model.NotificationEmail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import service.MailContentBuilder;
//import service.MailContentBuilder;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
	

	@Autowired
	private MailContentBuilder mailContentBuilder;

	@Autowired
	private JavaMailSender mailSender;
	
	
	void sendMail(NotificationEmail notificationEmail) throws SpringRedditCloneException
	{
		MimeMessagePreparator messagePreparator=mimeMessage -> {
			MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
			//messageHelper.setFrom("aarushimathur@gmail.com");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
		};
		
		try {
			mailSender.send(messagePreparator);
			log.info("Activation Email Sent");
		}
		catch(MailException e)
		{
		log.error("Exception occured while sending email", e);
		throw new SpringRedditCloneException("Exception occured !!");
		}
	}
}
