package com.ma.vue.boot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *服务熔断切面
 * 调用 异常会 使 counter+1，当大于3时直接返回null，并breakerCounter+1，三次之后重新尝试 是否回复正常
 */
@Component
@Slf4j
@Aspect
public class CircuitBreakerAspect {
    private static final Integer THRESHOLD = 3;
    private Map<String, AtomicInteger> counter = new ConcurrentHashMap<>();
    private Map<String,AtomicInteger> breakerCounter = new ConcurrentHashMap<>();

    @Around("execution(* com.ma.vue.boot.controller..*(..))")
    public Object doWithCircuitBreaker(ProceedingJoinPoint proceedingJoinPoint) throws Exception{
        String signature = proceedingJoinPoint.getSignature().toLongString();
        log.info("服务熔断切面 CircuitBreakerAspect Invoke {}",signature);
        Object retVal = null;
        try{
            if(counter.containsKey(signature)){
                if(counter.get(signature).get()>THRESHOLD &&
                        breakerCounter.get(signature).get()< THRESHOLD){
                    log.warn("CircuitBreakerAspect return null ,break {} times",breakerCounter.get(signature).incrementAndGet());
                    return null;
                }
            }else{
                counter.put(signature,new AtomicInteger(0));
                breakerCounter.put(signature,new AtomicInteger(0));
            }
            retVal = proceedingJoinPoint.proceed();
            counter.get(signature).set(0);
            breakerCounter.get(signature).set(0);
        } catch (Throwable throwable) {
            log.warn("invoke {} ,break Counter : {} , Throwable : {}"
                    ,signature,counter.get(signature).incrementAndGet(),throwable.getMessage(),throwable);
            breakerCounter.get(signature).set(0);
        }
        return retVal;
    }
}
