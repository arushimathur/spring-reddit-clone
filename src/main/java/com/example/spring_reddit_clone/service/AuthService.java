package com.example.spring_reddit_clone.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_reddit_clone.dto.RegisterRequest;
import com.example.spring_reddit_clone.exception.SpringRedditCloneException;
import com.example.spring_reddit_clone.model.NotificationEmail;
import com.example.spring_reddit_clone.model.User;
import com.example.spring_reddit_clone.model.VerificationToken;
import com.example.spring_reddit_clone.repository.UserRepository;
import com.example.spring_reddit_clone.repository.VerificationTokenRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
@Transactional
public class AuthService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private VerificationTokenRepository verifyRepo;
	
	@Autowired
	private MailService mailService;
	@Transactional
	public void signup(RegisterRequest registerReq) throws SpringRedditCloneException {
		// TODO Auto-generated method stub
		User user=new User();
		user.setUsername(registerReq.getUsername());
		user.setEmail(registerReq.getEmail());
		user.setPassword(registerReq.getPassword());
		user.setCreated(Instant.now());
		user.setEnabled(false);
		userRepo.save(user);
		String token=generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8085/api/auth/accountVerification/" + token));
       	}

	private String generateVerificationToken(User user) {
		// TODO Auto-generated method stub
		String token=UUID.randomUUID().toString();
		VerificationToken vtr=new VerificationToken();
		vtr.setToken(token);
		vtr.setUser(user);
		verifyRepo.save(vtr);
		return token;
	}

	
}
