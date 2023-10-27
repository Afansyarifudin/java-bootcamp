package com.example.challenge_4;

import com.example.challenge_4.controller.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Challenge4Application {

	public static void main(String[] args) {
//		SpringApplication.run(Challenge4Application.class, args);
		HomeController homeController = SpringApplication.run(Challenge4Application.class, args)
				.getBean(HomeController.class);
		homeController.home();
	}


}
