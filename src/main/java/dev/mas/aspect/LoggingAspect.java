package dev.mas.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
public class LoggingAspect {

    public static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(public * dev.mas.user.service.UserService.*(..))")
    public void callAtUserServicePublic() {
    }

    @Before("callAtUserServicePublic()")
    public void beforeCallAtMethodInUserService(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info("[User Service] received " + jp.getSignature().getName() + " method call with" + " args=[" + args + "]");
    }

    @Pointcut("execution(public * dev.mas.order.service.OrderService.*(..))")
    public void callAtOrderServicePublic() {
    }

    @Before("callAtOrderServicePublic()")
    public void beforeCallAtMethodInOrderService(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info("[Order Service] received " + jp.getSignature().getName() + " method call with" + " args=[" + args + "]");
    }

    @Pointcut("execution(public * dev.mas.exception.AOPHomeworkExceptionHandler.*(..))")
    public void callAtAOPHomeworkExceptionHandler() {
    }

    @Before("callAtAOPHomeworkExceptionHandler()")
    public void beforeCallMethodAtAOPHomeworkExceptionHandler(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info("[Exception handler Service] received " + jp.getSignature().getName() + " method call with" + " args=[" + args + "]");
    }


    @Pointcut("execution(public * dev.mas.user.controller.PublicUserController.*(..))")
    public void callAtPublicUserController() {
    }

    @Before("callAtPublicUserController()")
    public void beforeCallMethodPublicUserController(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info("[Public user controller] received " + jp.getSignature().getName() + " method call with" + " args=[" + args + "]");
    }


    @Pointcut("execution(public * dev.mas.order.controller.PublicOrderController.*(..))")
    public void callAtPublicOrderController() {
    }

    @Before("callAtPublicOrderController()")
    public void beforeCallMethodPublicOrderController(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info("[Public order controller] received " + jp.getSignature().getName() + " method call with" + " args=[" + args + "]");
    }
}
