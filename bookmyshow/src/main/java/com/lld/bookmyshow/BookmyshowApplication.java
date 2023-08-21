package com.lld.bookmyshow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.lld.bookmyshow.controllers.UserController;
import com.lld.bookmyshow.dtos.SignupUserRequestDto;

@SpringBootApplication
@EnableJpaAuditing
public class BookmyshowApplication implements CommandLineRunner {
	
	private UserController userController;
	
	@Autowired
	public BookmyshowApplication(UserController userController) {
		this.userController = userController;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookmyshowApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Hello");
		
		SignupUserRequestDto request = new SignupUserRequestDto();
		request.setEmail("kushal@gmail.com");
		request.setPassword("password");
		
		userController.signupUser(request);
	}

}
