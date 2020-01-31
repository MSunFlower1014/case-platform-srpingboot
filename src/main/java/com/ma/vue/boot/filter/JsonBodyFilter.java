package com.ma.vue.boot.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 解决Request inputString只能读取一次的问题
 *
 * @author xiyt
 */
public class JsonBodyFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(JsonBodyFilter.class);

    /**
     * 默认allow headers
     */
    private List<String> allowHeaders = Stream.of("Origin", "No-Cache", "X-Requested-With", "If-Modified-Since", "Pragma", "Last-Modified", "Cache-Control", "Expires", "Content-Type", "Authorization", "Access-Token", "ES-Naughty", "Request-ID").collect(Collectors.toList());
    /**
     * 默认allow methods
     */
    private List<String> allowMethods = Stream.of("OPTIONS", "GET", "POST").collect(Collectors.toList());

    /**
     * 手动指定允许的Origin
     */
    private String[] originWhiteList ={"http://localhost","http://127.0.0.1"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;

        if (servletResponse instanceof HttpServletResponse) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = ((HttpServletResponse) servletResponse);

            // 处理Origin
            //if (!ObjectUtils.isNullOrEmpty(this.originWhiteList)) {
            if(Arrays.asList(originWhiteList).contains(httpServletRequest.getHeader("Origin"))){
                log.info("JsonBodyFilter doFilter Origin " + httpServletRequest.getHeader("Origin"));
                httpServletResponse.addHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
            } else {
                httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
            }
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.addHeader("Access-Control-Allow-Methods", StringUtils.collectionToCommaDelimitedString(this.allowMethods));
            httpServletResponse.addHeader("Access-Control-Allow-Headers", StringUtils.collectionToCommaDelimitedString(this.allowHeaders));
            httpServletResponse.addHeader("Access-Control-Max-Age", "86400");
            httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        }

        if (requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

    /**
     * 设定自定义allow headers，将覆盖默认值
     * @param allowHeaders
     */
    public void setAllowHeaders(List<String> allowHeaders) {
        this.allowHeaders = allowHeaders;
    }

    /**
     * 添加额外的allow headers
     * @param allowHeaders
     */
    public void addAllowHeaders(List<String> allowHeaders) {
        this.allowHeaders.addAll(allowHeaders);
    }

    /**
     * 设定allow methods，将覆盖默认值
     * @param allowMethods
     */
    public void setAllowMethods(List<String> allowMethods) {
        this.allowMethods = allowMethods;
    }

    /**
     * 添加额外的allow methods
     * @param allowMethods
     */
    public void addAllowMethods(List<String> allowMethods) {
        this.allowMethods.addAll(allowMethods);
    }

    /**
     * 设定允许的Origin
     */
    public void setOriginWhiteList(String[] originWhiteList) {
        this.originWhiteList = originWhiteList;
    }
}
