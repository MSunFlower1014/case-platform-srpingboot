package com.ma.vue.boot.utils;


import com.ma.vue.boot.entity.UserEntity;
import com.ma.vue.boot.shiro.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

/**
 * 
 * All rights Reserved, Designed By www.jeeweb.cn
 * 
 * @title: UserUtils.java
 * @package cn.jeeweb.modules.sys.utils
 * @description: 用户工具类
 * @author: 王存见
 * @date: 2017年6月26日 下午6:00:39
 * @version V1.0
 * @copyright: 2017 www.jeeweb.cn Inc. All rights reserved.
 *
 */
@SuppressWarnings("unchecked")
public class UserUtils {

	/**
	 * 获取当前用户
	 * 
	 * @return 取不到返回 new User()
	 */
	public static UserEntity getUser() {
		UserRealm.Principal principal = getPrincipal();
		if (principal != null) {
			UserEntity user = new UserEntity();
			user.setUserName(principal.getName());
			user.setId(principal.getId());
			user.setHospital(principal.getHospital());
			return user;
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new UserEntity();
	}

	/**
	 * 获取当前登录者对象
	 */
	public static UserRealm.Principal getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			UserRealm.Principal principal = (UserRealm.Principal) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
			// subject.logout();
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}
}
