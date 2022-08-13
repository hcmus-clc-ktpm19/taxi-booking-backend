package com.hcmus.socketservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SocketServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SocketServiceApplication.class, args);
	}

}
