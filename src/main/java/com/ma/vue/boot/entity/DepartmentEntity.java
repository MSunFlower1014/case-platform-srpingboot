package com.ma.vue.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@TableName("TF_MM_DEPARTMENT")
public class DepartmentEntity {
    @ApiModelProperty("主键")
    @TableField(value = "P_ID")
    public String id;

    @ApiModelProperty("名称")
    @TableField(value = "NAME")
    private String name;
    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_DATE")
    private Date createDate;
    @ApiModelProperty("状态，0为有效，1为无效")
    @TableField(value = "STATUS")
    private String status;

    private List<DepartmentEntity> childDepartment;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DepartmentEntity> getChildDepartment() {
        return childDepartment;
    }

    public void setChildDepartment(List<DepartmentEntity> childDepartment) {
        this.childDepartment = childDepartment;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", status='" + status + '\'' +
                ", childDepartment=" + childDepartment +
                '}';
    }
}
