package com.example.spring_reddit_clone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_reddit_clone.dto.RegisterRequest;
import com.example.spring_reddit_clone.exception.SpringRedditCloneException;
import com.example.spring_reddit_clone.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerReq) throws SpringRedditCloneException
	{
		authService.signup(registerReq);
		return new ResponseEntity<>("User Registration Successful",HttpStatus.OK);
		
	}
	
	
}
