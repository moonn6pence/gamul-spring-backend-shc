package com.gamul.gamul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GamulApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamulApplication.class, args);
	}

}
