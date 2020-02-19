package com.ma.vue.boot.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class RequestInfoInterceptor implements HandlerInterceptor {
    private ThreadLocal<StopWatch> stopWatchThreadLocal = new ThreadLocal<>();
    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StopWatch stopWatch =  new StopWatch();
        stopWatchThreadLocal.set(stopWatch);
        stopWatch.start();
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        stopWatchThreadLocal.get().stop();
        stopWatchThreadLocal.get().start();
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        StopWatch stopWatch = stopWatchThreadLocal.get();
        stopWatch.stop();
        String method = handler.getClass().getSimpleName();
        if(handler instanceof HandlerMethod){
            String beanType = ((HandlerMethod) handler).getBeanType().getName();
            String name = ((HandlerMethod) handler).getMethod().getName();
            method = method + " beanType: " +beanType + name;
        }
        log.info("uri: {} , method: {} , responseStatus: {},exception : {} , {}ms , {}ms ,{} ms",request.getRequestURI(),
                method,response.getStatus(),ex == null ? "-" : ex.getMessage(),stopWatch.getTotalTimeMillis(),
                stopWatch.getTotalTimeMillis() - stopWatch.getLastTaskTimeMillis(),stopWatch.getLastTaskTimeMillis());
        stopWatchThreadLocal.remove();
    }
}
