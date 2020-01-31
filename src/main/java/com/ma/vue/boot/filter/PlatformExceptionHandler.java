package com.ma.vue.boot.filter;


import com.ma.vue.boot.utils.ReturnMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

/**
 * 统一拦截异常
 */
@ControllerAdvice
public class PlatformExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(PlatformExceptionHandler.class);

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({Exception.class})
    public @ResponseBody
    Map handlerIncorrectException(Exception exception) {
        int respCode = 1007;
        String respMsg = exception.getMessage();
        logger.error(exception.getMessage(),exception);
        return ReturnMessageUtils.returnErrorMessage(respCode,respMsg);
    }
}
