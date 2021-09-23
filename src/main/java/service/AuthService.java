package service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dto.RegisterRequest;
import exception.RedditException;
import model.NotificationEmail;
import model.User;
import model.VerificationToken;
import repository.UserRepository;
import repository.VerificationTokenRepository;

@Service
public class AuthService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VerificationTokenRepository verificationTokenrepo;

	@Autowired
	private MailService mailService;
	
	@Transactional
	public void signup(RegisterRequest registerRequest)
	{
		User user=new User();
		user.setUsername(registerRequest.getUsername());
		user.setEmail(registerRequest.getEmail());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setCreated(Instant.now());
		user.setEnabled(true);
		userRepository.save(user);
		String token=generateVerificationToken(user);
		mailService.sendMail(new NotificationEmail("Please Activate your Account",user.getEmail(),"Thanks for signing up!!"
				+ "Please register the below link to activate your account : "+token));
	}
	private String generateVerificationToken(User user)
	{
		String token=UUID.randomUUID().toString();
		VerificationToken verificationToken=new VerificationToken();
		verificationToken.setUser(user);
		verificationToken.setToken(token);
		verificationTokenrepo.save(verificationToken);
		return token;
		
	}
	public void verifyAccount(String token) {
		// TODO Auto-generated method stub
		Optional<VerificationToken> vt=verificationTokenrepo.findByToken(token);
		vt.orElseThrow(()->new RedditException("Invalid Token"));
		fetchUserAndEnable(vt.get());
	}
	
	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		// TODO Auto-generated method stub
	String username=verificationToken.getUser().getUsername();
	User user=userRepository.findByUsername(username).orElseThrow(()->new RedditException("Invalid Username"));
	user.setEnabled(true);
	userRepository.save(user);
	}
	
	
}
