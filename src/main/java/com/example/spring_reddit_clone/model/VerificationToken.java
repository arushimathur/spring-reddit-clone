package com.example.spring_reddit_clone.model;

import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "token")
public class VerificationToken {
	  @Id
	    @GeneratedValue(strategy = IDENTITY)
	    private Long id;
	    private String token;
	    @OneToOne(fetch = FetchType.LAZY)
	    private User user;
	    private Instant expiryDate;
}
