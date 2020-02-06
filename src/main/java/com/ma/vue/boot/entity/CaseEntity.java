package com.ma.vue.boot.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@TableName("TF_MM_CASE")
public class CaseEntity {
    @ApiModelProperty("主键")
    @TableId(value = "CASE_ID", type = IdType.UUID)
    private String id;

    @ApiModelProperty("病例名称")
    @TableField(value = "CASE_NAME")
    private String name;

    @ApiModelProperty("病人名字")
    @TableField(value = "PATIENT_NAME")
    private String patientName;

    @ApiModelProperty("病人年龄")
    @TableField(value = "PATIENT_AGE")
    private String patientAge;

    @ApiModelProperty("病人性别")
    @TableField(value = "PATIENT_SEX")
    private String patientSex;

    @ApiModelProperty("病人联系方式")
    @TableField(value = "PATIENT_MOBILE")
    private String patientMobile;

    @ApiModelProperty("所属医院")
    @TableField(value = "CASE_HOSPITAL")
    private String hospital;

    @ApiModelProperty("病例信息")
    @TableField(value = "CASE_MESSAGE")
    private String message;

    @ApiModelProperty("病例类型")
    @TableField(value = "CASE_TYPE")
    private String type;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    @TableField(value = "CASE_DATE")
    private Date createDate;

    @ApiModelProperty("病例状态  1 正常  2  转派中   3  出院结束")
    @TableField(value = "CASE_STATUS")
    private String status;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientMobile() {
        return patientMobile;
    }

    public void setPatientMobile(String patientMobile) {
        this.patientMobile = patientMobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
