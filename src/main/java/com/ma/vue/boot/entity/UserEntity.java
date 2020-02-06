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

    @TableField(value = "USER_LEVEL")
    private int levle;

    @TableField(value = "USER_HOSPITAL")
    private String hospital;

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

    public int getLevle() {
        return levle;
    }

    public void setLevle(int levle) {
        this.levle = levle;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
}
