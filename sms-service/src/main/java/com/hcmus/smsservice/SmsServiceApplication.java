package com.hcmus.smsservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableRabbit
@PropertySource({"classpath:application.yaml"})
public class SmsServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SmsServiceApplication.class, args);
  }
}
