package com.vasflam.lab03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("com.vasflam.lab03")
//@ComponentScan("com.vasflam.lab03")
//@EntityScan("com.vasflam.lab03")
public class Lab03Application {
	public static void main(String[] args) {
		SpringApplication.run(Lab03Application.class, args);
	}
}
