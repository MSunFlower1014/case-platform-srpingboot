package com.ma.vue.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@TableName("TF_MM_NOTICE")
public class NoticeEntity {
    @ApiModelProperty("主键")
    @TableId(value = "NOTICE_ID", type = IdType.UUID)
    private String id;

    @TableField(exist = false)
    private ReferralEntity referralEntity;

    @TableField(value = "REFERRAL_ID")
    private String referralId;

    @TableField(value = "NOTICE_STATUS")
    private String status;

    @TableField(value = "NOTICE_CREATE_DATE")
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReferralEntity getReferralEntity() {
        return referralEntity;
    }

    public void setReferralEntity(ReferralEntity referralEntity) {
        this.referralEntity = referralEntity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }
}
