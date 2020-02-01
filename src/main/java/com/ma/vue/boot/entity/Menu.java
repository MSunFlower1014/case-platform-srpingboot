package com.ma.vue.boot.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import java.util.Date;
import java.util.List;

@TableName("TF_MM_MENU")
public class Menu {
	@ApiModelProperty("主键")
	@TableId(value = "MENU_ID", type = IdType.UUID)
	private String id;

	@ApiModelProperty("父菜单id")
	@TableId(value = "PARENT_ID", type = IdType.UUID)
	private String parentId;

	@ApiModelProperty("菜单名称")
	@TableField("MENU_NAME")
	private String name;

	@ApiModelProperty("点击后前往的地址")
	@TableField(value = "MENU_URL")
	@Length( max = 200)
	private String url;

	@ApiModelProperty("备注")
	@TableField(value = "MENU_REMARK")
	private String remarks;

	/** 是否显示 */
	@TableField(value = "MENU_ISSHOW")
	private Short isshow;

	@ApiModelProperty("排序")
	@TableField(value = "MENU_SORT")
	@Max(Integer.MAX_VALUE)
	private Integer sort;

	@JsonIgnore
	@TableField(value = "MENU_CREATE_DATE")
	protected Date createDate; // 创建日期

	@JsonIgnore
	@ApiModelProperty("删除标记（0：正常；1：删除 ）")
	@TableField(value = "MENU_DEL_FLAG", fill = FieldFill.INSERT)
	protected String delFlag;

	@TableField(exist = false)
	private List<Menu> childMenus;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Short getIsshow() {
		return isshow;
	}

	public void setIsshow(Short isshow) {
		this.isshow = isshow;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<Menu> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<Menu> childMenus) {
		this.childMenus = childMenus;
	}

}
