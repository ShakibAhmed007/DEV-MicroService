package com.example.addressservice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.addressservice.controller.*.*(..))")
    public void logBeforeControllerMethods(JoinPoint joinPoint) {
        logger.info("Executing: {}", joinPoint.getSignature().toShortString());
    }
}
