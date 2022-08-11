package com.hcmus.socketservice.aop;

import com.hcmus.socketservice.model.dto.CarRequestDto;
import com.hcmus.socketservice.model.enums.CarRequestStatus;
import com.hcmus.socketservice.model.enums.SenderServiceType;
import com.hcmus.socketservice.service.SmsPub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class WebSocketAspect {

  private final SmsPub smsPub;

  @Pointcut("@annotation(com.hcmus.socketservice.annotation.EnableSenderService)")
  private void sendAcceptMessageToCustomerMethod() { /* Pointcut's alias */ }

  @After("sendAcceptMessageToCustomerMethod()")
  public void afterSendAcceptMessageToCustomer(JoinPoint joinPoint) {
    log.info("After execute {}", joinPoint.getSignature().getName());

    CarRequestDto carRequestDto = (CarRequestDto) joinPoint.getArgs()[0];
    if (carRequestDto.getStatus() == CarRequestStatus.ACCEPTED) {
      log.info("Send SMS to customer");
      carRequestDto.setSenderServiceType(SenderServiceType.TWILIO);
      smsPub.send(carRequestDto);
    }
  }
}
