package com.ma.vue.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ma.vue.boot.aop.OperationLog;
import com.ma.vue.boot.common.Constant;
import com.ma.vue.boot.entity.PageCondition;
import com.ma.vue.boot.entity.UserEntity;
import com.ma.vue.boot.mapper.UserMapper;
import com.ma.vue.boot.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/platform")
public class UserEntityController {
    private static final Logger logger = LoggerFactory.getLogger(UserEntityController.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @return
     */
    @OperationLog(value = "获取登录用户数据")
    @RequestMapping(value = "/self", method = RequestMethod.GET)
    public Map login(HttpServletRequest request) {
        Map result = new HashMap();
        result.put(Constant.System.RESULT_CODE,0);
        result.put("user",UserUtils.getUser());
        return result;
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @OperationLog(value = "获取用户列表")
    @RequestMapping(value = "/getUserList")
    public IPage<UserEntity> getUserList(@RequestBody PageCondition pageCondition, @RequestParam String name) {
        Page<UserEntity> page = new Page(pageCondition.getPageNum(),pageCondition.getPageSize());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("USER_NAME",name);
        queryWrapper.orderBy(false,false,"USER_ID");
        return userMapper.selectPage(page,queryWrapper);
    }
}
