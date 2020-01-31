package com.ma.vue.boot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Set;

@TableName("TF_MM_USER")
public class UserEntity {

    @TableId(value = "USER_ID", type = IdType.UUID)
    private String id;

    @TableField(value = "USER_NAME")
    private String userName;

    @TableField(value = "USER_PASSWORD")
    private String password;
    /**
     * 用户对应的角色集合
     */
    //private Set<RoleEntity> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Set<RoleEntity> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<RoleEntity> roles) {
//        this.roles = roles;
//    }
}
