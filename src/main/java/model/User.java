package model;

import java.math.BigInteger;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.datetime.standard.DateTimeContext;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
		@Id
	    @GeneratedValue(strategy = IDENTITY)
	    private Long userId;
	    @NotBlank(message = "Username is required")
	    private String username;
	    @NotBlank(message = "Password is required")
	    private String password;
	    @Email
	    @NotEmpty(message = "Email is required")
	    private String email;
	    private Instant created;
	    private boolean enabled;
	    
}