package com.ma.vue.boot.configuration;

import com.ma.vue.boot.shiro.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {
    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

//        Map<String, Filter> filterMap = new HashMap<>();
//        filterMap.put("sysUser", new SysUserFilter());
//        filterMap.put("users", new UserAuthFilter());
//        filterMap.put("perms", new PermissionAuthFilter());
//        filterMap.put("roles", new RoleAuthFilter());
//        filterMap.put("jCaptchaValidate", jCaptchaValidateFilter());
//        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/*", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        // 自定义session管理
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        SimpleCookie rememberMeCookies = new SimpleCookie("rememberMe");
        rememberMeCookies.setHttpOnly(true);
        rememberMeCookies.setPath("/");
        rememberMeCookies.setMaxAge(-1);
        rememberMeManager.setCookie(rememberMeCookies);
        return rememberMeManager;
    }

}
