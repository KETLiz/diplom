package ru.gb.diplom.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerAspect {
//    @Pointcut("within(@ru.gb.springbootlesson3.Timer *)")
//    public void beansMethod(){}
//
//    @Pointcut("@annotation(ru.gb.springbootlesson3.Timer)")
//    public void beansWithAnnotation(){}

    @Around("@annotation(Timer)")
    public Object loggable(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(proceedingJoinPoint.getSignature() + " выполнился за " +
                executionTime + " ms");
        return proceed;
    }
}
