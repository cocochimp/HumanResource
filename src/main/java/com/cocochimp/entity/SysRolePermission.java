package com.cocochimp.entity;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class SysRolePermission {

	/**
	 * 角色id
	 */
	@ApiModelProperty(value = "角色id", position = 0)
	private String rId;

	/**
	 * 权限id
	 */
	@ApiModelProperty(value = "权限id", position = 1)
	private String pId;

	/**
	 * 逻辑删除【1true】
	 */
	@ApiModelProperty(value = "逻辑删除【x】", position = 2)
	private String isDeleted;


}
