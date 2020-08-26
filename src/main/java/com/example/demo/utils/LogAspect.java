package com.example.demo.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;


@Aspect
@Component
public class LogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * com.example.demo.controllers.PersonController.*(..))")
    public void myPointCut() { }

    @Before("myPointCut()")
    public void before(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
            .map(a -> a.toString())
            .collect(Collectors.joining(","));
        logger.info("Start time: " + LocalDateTime.now() + ": before " + jp.toString() + ", args=[" + args + "]");
    }

    @After("myPointCut()")
    public void after(JoinPoint jp) {
        logger.info("End time: " + LocalDateTime.now() + ": " + jp.toString());
    }
}
