package com.cocochimp.entity;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class SysOrgan1 {

	/**
	 * 一级机构id
	 */
	@ApiModelProperty(value = "一级机构id", position = 0)
	private String o1Id;

	/**
	 * 一级机构名称
	 */
	@ApiModelProperty(value = "一级机构名称", position = 1)
	private String o1Name;


}
