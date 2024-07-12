package com.fernando;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebClientApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebClientApiApplication.class, args);
		
		
                /*
		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
		"", 8, 185000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		
				
		Map<String, PasswordEncoder> encoders =  new HashMap<>();
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		
		String result = passwordEncoder.encode("admin");
		System.out.println("My Hash: " + result);
                */
	
		
	
		
		
		
	}
}
