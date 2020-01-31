package com.ma.vue.boot.controller;

import com.ma.vue.boot.aop.OperationLog;
import com.ma.vue.boot.common.Constant;
import com.ma.vue.boot.entity.UserEntity;
import com.ma.vue.boot.mapper.UserMapper;
import com.ma.vue.boot.service.IUserService;
import com.ma.vue.boot.utils.ImageCodeUtil;
import com.ma.vue.boot.utils.ReturnMessageUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/platform")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;
    /**
     * 用户创建
     *
     * @return
     */
    @OperationLog(value = "用户创建")
    @RequestMapping(value = "/saveUser")
    public Map  saveUser(@RequestBody UserEntity userEntity,HttpServletRequest request) {
        Map result = new HashMap<>();
        try {
            if(StringUtils.isEmpty(userEntity.getUserName())){
                return ReturnMessageUtils.returnErrorMessage(-1,"无效的用户名");
            }
            if(StringUtils.isEmpty(userEntity.getPassword())){
                return ReturnMessageUtils.returnErrorMessage(-1,"无效的密码");
            }

            //无id则为新建
            if(StringUtils.isEmpty(userEntity.getId())){
                Map columMap = new HashMap();
                columMap.put("USER_NAME",userEntity.getUserName());
                Collection collection = userService.listByMap(columMap);
                if(collection.isEmpty()){
                    if( userService.save(userEntity)){
                        result.put(Constant.System.RESULT_CODE,0);
                        result.put(Constant.System.RESULT_MESSAGE,Constant.System.MESSAGE_SUCCESS);
                    }else{
                        result = ReturnMessageUtils.returnErrorMessage(-1,"创建用户失败");
                    }
                }else{
                    result = ReturnMessageUtils.returnErrorMessage(-1,"用户名已经被注册了！");
                }
            }else{
                //修改用户名或密码
                //todo
            }

        } catch (Exception e) {
            result = ReturnMessageUtils.returnErrorMessage(-1,e.getMessage());
            logger.error("LoginController - saveUser error ",e);
        }
        return result;
    }

    /**
     * 用户登录
     *
     * @return
     */
    @OperationLog(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(@RequestBody UserEntity loginParam,
                         HttpServletRequest request) {
        Map result = new HashMap<>();
        try {
            if(StringUtils.isEmpty(loginParam.getUserName())){
                return ReturnMessageUtils.returnErrorMessage(-1,"无效的用户名");
            }
            if(StringUtils.isEmpty(loginParam.getPassword())){
                return ReturnMessageUtils.returnErrorMessage(-1,"无效的密码");
            }
            logger.info("Username:"+loginParam.getUserName()+" Password:"+loginParam.getPassword());
            UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUserName()
                    , loginParam.getPassword().toCharArray()
                  );

            Subject subject = SecurityUtils.getSubject();
            if (null != subject) {
                subject.logout();
            }
            //具体执行的回调方法在UserRealm类里面
            subject.login(token);
            result.put(Constant.System.RESULT_CODE,0);
            result.put(Constant.System.RESULT_MESSAGE,Constant.System.MESSAGE_SUCCESS);
        } catch (Exception e) {
            result = ReturnMessageUtils.returnErrorMessage(-1,e.getMessage());
            logger.error("LoginController - login error ",e);
        }
        return result;
    }

    /**
     * 用户退出
     *
     * @return
     */
    @OperationLog(value = "用户推出")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Boolean logout() {
        Boolean result = Boolean.TRUE;
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject != null) {
                subject.logout();
            }
        } catch (Exception e) {
            result = Boolean.FALSE;
            logger.error("LoginController - logout error ",e);

        }
        return result;
    }

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     */
    @OperationLog("生成验证码")
    @RequestMapping(value = "/validateCode", method = RequestMethod.GET)
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = ImageCodeUtil.generateCheckCode();
        logger.info("生成验证码 {} ",code);
        request.getSession().setAttribute(Constant.System.RANDOM_CODE, code);
        final BufferedImage image = ImageCodeUtil.generateCaptcha(code);
        response.setContentType("image/jpg");
        response.setHeader("Cache-Control", "no-cache");
        ImageIO.write(image, "JPG", response.getOutputStream());
    }
}
