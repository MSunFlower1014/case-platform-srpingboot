package com.ma.vue.boot.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ma.vue.boot.entity.UserEntity;
import com.ma.vue.boot.mapper.UserMapper;
import com.ma.vue.boot.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity>  implements IUserService{

}
