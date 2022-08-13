package com.hcmus.socketservice.annotation;

import static com.hcmus.socketservice.model.enums.SenderServiceType.TWILIO;

import com.hcmus.socketservice.model.enums.SenderServiceType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface EnableSenderService {
  SenderServiceType value() default TWILIO;
}
