package com.ma.vue.boot.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author yangxiaolong
 * @Date 2018/8/10
 */
@Aspect
@Component
public class OperationLogAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(OperationLogAspect.class);


    @Pointcut("@annotation(com.ma.vue.boot.aop.OperationLog)")
    public void pointCut() {
    }

        @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        LOGGER.debug("excute method Aspect around() start");
        Object result = null;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        long executionTime = 0L;
        try {
            result = point.proceed();
            // 执行时长(毫秒)
            stopWatch.stop();
            executionTime = stopWatch.getTotalTimeMillis();
        } catch (Throwable throwable) {
            dealOperationLog(point, executionTime, throwable);
            LOGGER.error("excute method fail");

            throw throwable;
        }
        //定义处理参数方法
        dealOperationLog(point, executionTime, null);
        LOGGER.debug("excute method Aspect around() executionTime:{}", executionTime);
        return result;
    }


    private void dealOperationLog(ProceedingJoinPoint point, long time, Throwable throwable) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestUrl = request.getRequestURL().toString();

        Object[] args = point.getArgs();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        //注解的属性值
        String annotationValue = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(OperationLog.class).value();
        LOGGER.info("dealOperationLog requestUrl = {} , args ={} , methodName = {} ,annotationValue = {}",requestUrl,args,methodName,annotationValue);


    }
}
