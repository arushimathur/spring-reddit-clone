package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import exception.RedditException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import model.NotificationEmail;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private MailContentBuilder mailContentBuilder;
	
	@Async
	void sendMail(NotificationEmail notificationEmail) throws RedditException
	{
		
		MimeMessagePreparator messagePreparator=mimeMessage->
		{
			MimeMessageHelper messageHelper=new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
			
		};
		try {
			mailSender.send(messagePreparator);
			log.info("Activation Email Sent!!");
		}
		catch(MailException e)
		{
			throw new RedditException("Exception occured when sending email");
		}
	}
}
