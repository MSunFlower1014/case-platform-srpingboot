package com.ma.vue.boot.shiro.realm;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ma.vue.boot.entity.UserEntity;
import com.ma.vue.boot.mapper.CaseMapper;
import com.ma.vue.boot.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * http://blog.csdn.net/babys/article/details/50151407
 *
 * @author 王存见
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private IUserService userService;

    /**
     * 授权的回调方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(null);
        authorizationInfo.setStringPermissions(null);
        return authorizationInfo;
    }

    /**
     * 认证的回调方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return doGetAuthenticationInfoFromDB(token);
    }

    /**
     * 登录界面登录的用户
     * @param token
     * @return
     * @throws AuthenticationException
     */
    private AuthenticationInfo doGetAuthenticationInfoFromDB(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        LOGGER.info("UserRealm  AuthenticationInfo authcToken" + authcToken);
        String username = authcToken.getUsername();
        LOGGER.info("UserRealm  AuthenticationInfo username" + username);
        UserEntity userEntity =  new UserEntity();
        userEntity.setUserName(authcToken.getUsername());
        Wrapper<UserEntity> queryWrapper =  new QueryWrapper<UserEntity>(userEntity);

        UserEntity userAcount = userService.getOne(queryWrapper);
        // 没找到帐号
        if (userAcount == null) {
            throw new UnknownAccountException();
        }

        // 密码为空，禁止登陆
        if (StringUtils.isEmpty(userAcount.getPassword())) {
            throw new LockedAccountException();
        }
        LOGGER.info("UserRealm  AuthenticationInfo authenticationInfo user {} " , userAcount);
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户名
                new Principal(userAcount),
                // 密码
                userAcount.getPassword(),
                // realm name
                getName()
        );
        LOGGER.info("UserRealm  AuthenticationInfo authenticationInfo" + authenticationInfo);
        // 记录登录日志
//			LogUtils.saveLog(ServletUtils.getRequest(), "系统登录");
        return authenticationInfo;
    }


    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    /**
     * 授权用户信息
     */
    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;

        private String id; // 编号
        private String name; // 登录名
        private String realname; // 登录名
        public Principal(UserEntity userEntity){
            this.id = userEntity.getId();
            this.name = userEntity.getUserName();
        }

        @Override
        public String toString() {
            return id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }
    }
}
