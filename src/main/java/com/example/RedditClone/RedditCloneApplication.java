package com.example.RedditClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

//import sun.tools.jar.CommandLine;

@SpringBootApplication
@EnableAsync
//@EnableConfigurationProperties
//@EntityScan(basePackages = { "model.*" })

public class RedditCloneApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(RedditCloneApplication.class, args);
		
	}

}
