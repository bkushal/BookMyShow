package com.lld.bookmyshow.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lld.bookmyshow.models.User;
import com.lld.bookmyshow.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User signupUser(String email, String password) {
		User user = new User();
		user.setEmail(email);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		user.setPassword(encoder.encode(password));
		User savedUser = this.userRepository.save(user);
		return savedUser;
		
	}
	
}
