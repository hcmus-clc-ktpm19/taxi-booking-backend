package com.hcmus.wiberback.model.entity.redis;

import com.hcmus.wiberback.model.entity.mongo.CarRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("car_request")
@NoArgsConstructor
@Data
public class CarRequestRedis extends CarRequest {

  // Convert from CarRequestRedis to CarRequest
  public CarRequestRedis(CarRequest carRequest) {
    this.setId(carRequest.getId());
    this.setCustomer(carRequest.getCustomer());
    this.setCallCenter(carRequest.getCallCenter());
    this.setPickingAddress(carRequest.getPickingAddress());
    this.setArrivingAddress(carRequest.getArrivingAddress());
    this.setLngPickingAddress(carRequest.getLngPickingAddress());
    this.setLatPickingAddress(carRequest.getLatPickingAddress());
    this.setLngArrivingAddress(carRequest.getLngArrivingAddress());
    this.setLatArrivingAddress(carRequest.getLatArrivingAddress());
    this.setStatus(carRequest.getStatus());
    this.setCarType(carRequest.getCarType());
  }
}
