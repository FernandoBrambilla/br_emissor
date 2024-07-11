package com.fernando.services;


import com.fernando.Entities.Bank;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fernando.Entities.User;
import com.fernando.Exceptions.RequiredObjectIsNullException;
import com.fernando.Exceptions.ResourceNotFoundException;
import com.fernando.Repositories.UserRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;


@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository repository;

 
	// CONSTRUCTOR

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	// FindAll
	public List<User> findAll() {
		return repository.findAll();
	}
	
	// FindById
	public User findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	// Create
	public User create(User user) {
		if (user == null)
			throw new RequiredObjectIsNullException();
                //ENCODA O PASSWORD
                Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
		"", 8, 185000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);	
		Map<String, PasswordEncoder> encoders =  new HashMap<>();
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		String result = passwordEncoder.encode(user.getPassword());
                user.setPassword(result);
            return repository.save(user);
	}
        
        

	// Update
	public User update(User user) {
		if (user == null)
			throw new RequiredObjectIsNullException();

		User entity = repository.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException());
                
                //ENCODA O PASSWORD
                Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
		"", 8, 185000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);	
		Map<String, PasswordEncoder> encoders =  new HashMap<>();
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		String result = passwordEncoder.encode(user.getPassword());
                user.setPassword(result);
                
		entity.setFullName(user.getFullName());
		entity.setUserName(user.getUserName());
                entity.setEmail(user.getEmail());
		entity.setPassword(result);
		return repository.save(entity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUserName(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username" + username + " Not Found!");
		}
	}
        
        // Delete
	public void delete(Integer id) {
		User entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}
}
