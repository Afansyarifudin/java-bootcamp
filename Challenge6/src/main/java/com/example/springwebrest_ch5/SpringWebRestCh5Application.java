package com.example.springwebrest_ch5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringWebRestCh5Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebRestCh5Application.class, args);
	}

}
