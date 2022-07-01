package com.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.logging.Logger;

@EnableWebMvc
@SpringBootApplication
public class SpringBootRestJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestJpaApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		Logger.getLogger("Running");
	}
}
