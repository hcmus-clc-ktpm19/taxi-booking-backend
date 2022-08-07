package com.hcmus.wiberback.config;

import com.hcmus.wiberback.model.entity.mongo.CarRequest;
import com.hcmus.wiberback.model.entity.redis.CarRequestRedis;
import com.hcmus.wiberback.repository.mongo.CarRequestRepository;
import com.hcmus.wiberback.repository.redis.CarRequestRedisRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Component
@Configuration
@EnableCaching
@EnableRabbit
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses = {CarRequest.class, CarRequestRepository.class})
@EnableRedisRepositories(basePackageClasses = {CarRequestRedis.class, CarRequestRedisRepository.class})
public class WiberConfiguration {

  @Value("${queue.sms.name}")
  private String smsQueueName;

  @Value("${queue.car-request.name}")
  private String carRequestQueueName;

  @Value("${queue.car-request-status.name}")
  private String carRequestStatusQueueName;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CommonsRequestLoggingFilter logFilter() {
    CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
    filter.setIncludeQueryString(true);
    filter.setIncludePayload(true);
    filter.setIncludeClientInfo(true);
    filter.setMaxPayloadLength(10000);
    filter.setIncludeHeaders(false);
    filter.setAfterMessagePrefix("Request data: ");
    return filter;
  }

  @Bean
  public ValidatingMongoEventListener validatingMongoEventListener() {
    return new ValidatingMongoEventListener(validator());
  }

  @Bean
  public LocalValidatorFactoryBean validator() {
    return new LocalValidatorFactoryBean();
  }

  @Bean
  public Queue smsQueue() {
    return new Queue(smsQueueName, true);
  }

  @Bean
  public Queue carRequestQueue() {
    return new Queue(carRequestQueueName, true);
  }

  @Bean
  public Queue carRequestStatusQueue() {
    return new Queue(carRequestStatusQueueName, true);
  }

  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
    return rabbitTemplate;
  }

  @Bean
  public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
