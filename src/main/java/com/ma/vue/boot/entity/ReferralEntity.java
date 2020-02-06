package com.ma.vue.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@TableName("TF_MM_REFERRAL")
public class ReferralEntity {

    @TableId(value = "REFERRAL_ID", type = IdType.UUID)
    private String id;

    @TableField(value = "CASE_ID")
    private String caseId;

    @TableField(value = "OLD_OWN_ID")
    private String oldOwnId;

    @TableField(value = "OLD_OWN_NAME")
    private String oldOwnName;

    @TableField(value = "NEW_OWN_ID")
    private String newOwnId;

    @TableField(value = "NEW_OWN_NAME")
    private String newOwnName;

    @TableField(value = "MESSAGE")
    private String message;

    @ApiModelProperty("转诊类型 1 上转转诊 2 下转转诊")
    @TableField(value = "TYPE")
    private String type;

    @ApiModelProperty("转诊单创建时间")
    @TableField(value = "CREATE_DATE")
    private Date createDate;

    @ApiModelProperty("转诊单状态 1 接受中 2 已接受")
    @TableField(value = "STATUS")
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getOldOwnId() {
        return oldOwnId;
    }

    public void setOldOwnId(String oldOwnId) {
        this.oldOwnId = oldOwnId;
    }

    public String getOldOwnName() {
        return oldOwnName;
    }

    public void setOldOwnName(String oldOwnName) {
        this.oldOwnName = oldOwnName;
    }

    public String getNewOwnId() {
        return newOwnId;
    }

    public void setNewOwnId(String newOwnId) {
        this.newOwnId = newOwnId;
    }

    public String getNewOwnName() {
        return newOwnName;
    }

    public void setNewOwnName(String newOwnName) {
        this.newOwnName = newOwnName;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
