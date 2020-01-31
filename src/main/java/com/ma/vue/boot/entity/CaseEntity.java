package com.ma.vue.boot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("TF_MM_CASE")
public class CaseEntity {

    @TableId(value = "CASE_ID", type = IdType.UUID)
    private String id;

    @TableField(value = "CASE_NAME")
    private String name;

    @TableField(value = "CASE_HOSPITAL")
    private String hospital;
    @TableField(value = "CASE_MESSAGE")
    private String message;
    @TableField(value = "CASE_TYPE")
    private String type;
    @TableField(value = "CASE_DATE")
    private Date createDate;

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

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "CaseEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", hospital='" + hospital + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
