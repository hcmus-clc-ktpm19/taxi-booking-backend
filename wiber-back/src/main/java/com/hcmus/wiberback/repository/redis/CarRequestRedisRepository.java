package com.hcmus.wiberback.repository.redis;

import com.hcmus.wiberback.model.entity.redis.CarRequestRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRequestRedisRepository extends CrudRepository<CarRequestRedis, String> {
}
