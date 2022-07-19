package com.hcmus.wiberback.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class WiberConfiguration {

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
    RedisSerializationContext.SerializationPair<Object> jsonSerializer =
        RedisSerializationContext.SerializationPair.fromSerializer(
            new Jackson2JsonRedisSerializer<>(Object.class));

    return RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(redisConnectionFactory)
        .cacheDefaults(
            RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(jsonSerializer))
        .build();
  }
}
