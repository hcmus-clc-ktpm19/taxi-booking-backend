package com.hcmus.socketservice.aop;

import com.hcmus.socketservice.annotation.EnableSenderService;
import com.hcmus.socketservice.model.dto.CarRequestDto;
import com.hcmus.socketservice.model.dto.MessageDto;
import com.hcmus.socketservice.model.enums.CarRequestStatus;
import com.hcmus.socketservice.model.enums.SenderServiceType;
import com.hcmus.socketservice.service.SmsPub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class SmsAspect {

  private final SmsPub smsPub;

  @Pointcut("@annotation(com.hcmus.socketservice.annotation.EnableSenderService)")
  private void enableSenderServiceAnnotation() { /* Pointcut's alias */ }

  @AfterReturning("enableSenderServiceAnnotation()")
  public void afterSentAcceptMessageToCustomer(JoinPoint joinPoint) {
    log.info("After execute {}", joinPoint.getSignature().getName());

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    SenderServiceType senderType = signature
        .getMethod()
        .getAnnotation(EnableSenderService.class)
        .value();
    log.info("EnableSenderService annotation value: {}", senderType);

    CarRequestDto carRequestDto = (CarRequestDto) joinPoint.getArgs()[0];
    if (carRequestDto.getStatus() == CarRequestStatus.ACCEPTED) {
      log.info("Sending SMS to customer");
      smsPub.send(MessageDto.builder()
                            .carRequestDto(carRequestDto)
                            .content(
                                "Driver " + carRequestDto.getDriverName()
                                    + " with phone number: "
                                    + carRequestDto.getDriverPhone()
                                    + " has accepted your request!")
                            .senderServiceType(senderType)
                            .build());
    }
  }
}
