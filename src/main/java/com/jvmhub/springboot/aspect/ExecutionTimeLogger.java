package com.jvmhub.springboot.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ExecutionTimeLogger {

    private Logger logger;

    public ExecutionTimeLogger() {
        logger = LoggerFactory.getLogger(getClass());
        logger.info("HEY");
    }

    //@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    //public void requestMapping() {}

    //@Pointcut("execution(* com.jvmhub.springboot.service.impl.*ServiceImpl.*(..))")
    @Pointcut("execution(* com.jvmhub.springboot.repository..*.*(..))")
    public void methodPointcut() {}
    

    //@Around("requestMapping() && methodPointcut()")
    @Around("methodPointcut()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        String name = pjp.getSignature().getName();
        try {
            sw.start();
            return pjp.proceed();
        } finally {
            sw.stop();
            logger.info("STOPWATCH: " + sw.getTotalTimeMillis() + " - " + name);
        }
    }
}