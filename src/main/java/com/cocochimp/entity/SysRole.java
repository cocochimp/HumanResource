package com.cocochimp.entity;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class SysRole {

	/**
	 * 角色id
	 */
	@ApiModelProperty(value = "角色id", position = 0)
	private String rId;

	/**
	 * 角色名称
	 */
	@ApiModelProperty(value = "角色名称", position = 1)
	private String roleName;

	/**
	 * 逻辑删除【1true】
	 */
	@ApiModelProperty(value = "逻辑删除【x】", position = 2)
	private String isDeleted;


}
