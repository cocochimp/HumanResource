package com.cocochimp.entity;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class SysPermission {

	/**
	 * 权限id
	 */
	@ApiModelProperty(value = "权限id", position = 0)
	private String pId;

	/**
	 * 权限名称
	 */
	@ApiModelProperty(value = "权限名称", position = 1)
	private String pName;

	/**
	 * 权限路径
	 */
	@ApiModelProperty(value = "权限路径", position = 2)
	private String path;

	/**
	 * 逻辑删除【1true】
	 */
	@ApiModelProperty(value = "逻辑删除【x】", position = 3)
	private String isDeleted;


}
