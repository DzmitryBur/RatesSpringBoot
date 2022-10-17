package com.bpc.ratesspringboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Before("within(com.bpc.ratesspringboot.controller.RateController)")
    void logBefore(JoinPoint joinPoint) {
        log.info("THIS BEFORE METHOD ->" + joinPoint.getSignature().getName());
    }

    @After("within(com.bpc.ratesspringboot.controller.RateController)")
    void logAfter(JoinPoint joinPoint) {
        log.info("THIS AFTER METHOD ->" + joinPoint.getSignature().getName());
    }

}

