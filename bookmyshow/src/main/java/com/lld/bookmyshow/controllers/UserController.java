package com.lld.bookmyshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lld.bookmyshow.dtos.SignupUserRequestDto;
import com.lld.bookmyshow.dtos.SignupUserResponseDto;
import com.lld.bookmyshow.models.User;
import com.lld.bookmyshow.services.UserService;

@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	public SignupUserResponseDto signupUser(SignupUserRequestDto request) {
		
		User user = userService.signupUser(request.getEmail(), request.getPassword());
		SignupUserResponseDto response = new SignupUserResponseDto();
		response.setUserId(user.getId());
		return response;
		
	}

}
