package com.hcmus.customerservice;

import com.hcmus.customerservice.controller.ExceptionAdviceController;
import com.hcmus.customerservice.service.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication(scanBasePackages = "com.hcmus.customerservice")
@ComponentScan(
    basePackages = "com.hcmus.customerservice",
    basePackageClasses = {
    ExceptionAdviceController.class,
    UserService.class
})
public class CustomerServiceConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CustomerServiceConfig.class);
    }
}
