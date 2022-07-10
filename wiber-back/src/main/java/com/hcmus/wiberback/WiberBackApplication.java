package com.hcmus.wiberback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:application.yaml", "classpath:secret.yaml"})
public class WiberBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiberBackApplication.class, args);
	}

}
