package com.hcmus.wiberback.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Component
@Configuration
public class WiberConfiguration {

  @Value("${queue.name}")
  private String queueName;
  private final RedisConnectionFactory redisConnectionFactory;

  public WiberConfiguration(RedisConnectionFactory redisConnectionFactory) {
    this.redisConnectionFactory = redisConnectionFactory;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CacheManager cacheManager() {

    return RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(redisConnectionFactory)
        .cacheDefaults(
            RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(
                    new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                    new GenericJackson2JsonRedisSerializer()))
        )
        .build();
  }

  @Bean
  public CommonsRequestLoggingFilter logFilter() {
    CommonsRequestLoggingFilter filter
        = new CommonsRequestLoggingFilter();
    filter.setIncludeQueryString(true);
    filter.setIncludePayload(true);
    filter.setMaxPayloadLength(10000);
    filter.setIncludeHeaders(false);
    filter.setAfterMessagePrefix("Request data: ");
    return filter;
  }

  @Bean
  public Queue queue() {
    return new Queue(queueName, true);
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
