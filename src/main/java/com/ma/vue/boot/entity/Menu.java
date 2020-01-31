package com.ma.vue.boot.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import java.util.Date;
import java.util.List;

@TableName("tf_mm_menu")
public class Menu {
	@TableId(value = "CASE_ID", type = IdType.UUID)
	private String id;
	@TableField("name")
	private String name;
	/** 图标 */
	@TableField(value = "menu_icon")
	@Length( max = 255)
	private String menuIcon;
	/** 资源类型 */
	@TableField(value = "type")
	@Length( max = 50)
	private String type;
	/** 点击后前往的地址 */
	@TableField(value = "url")
	@Length( max = 200)
	private String url;
	/** 是否显示 */
	@TableField(value = "isshow")
	private Short isshow;
	/** 排序 */
	@TableField(value = "sort")
	@Max(Integer.MAX_VALUE)
	private Integer sort;
	@JsonIgnore
	@TableField(value = "create_by", fill = FieldFill.INSERT)
	protected String createBy; // 创建者
	@JsonIgnore
	@TableField(value = "create_date")
	protected Date createDate; // 创建日期
	@JsonIgnore
	@TableField(value = "update_by", fill = FieldFill.UPDATE)
	protected String updateBy; // 更新者
	@JsonIgnore
	@TableField(value = "update_date", fill = FieldFill.UPDATE)
	protected Date updateDate; // 更新日期
	@JsonIgnore
	@TableField(value = "del_flag", fill = FieldFill.INSERT)
	protected String delFlag; // 删除标记（0：正常；1：删除 ）
	/** 备注 */
	@TableField(value = "remarks")
	private String remarks;
	@TableField(exist = false)
	private List<Menu> childMenus;

	@TableField(value = "parent_id")
	private String parentId;
	@TableField(value = "parent_ids")
	private String parentIds;

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

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
