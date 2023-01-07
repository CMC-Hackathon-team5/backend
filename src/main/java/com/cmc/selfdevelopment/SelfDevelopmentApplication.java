package com.cmc.selfdevelopment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SelfDevelopmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfDevelopmentApplication.class, args);
	}

}
