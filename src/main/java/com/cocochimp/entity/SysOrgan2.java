package com.cocochimp.entity;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class SysOrgan2 {

	/**
	 * 二级机构id
	 */
	@ApiModelProperty(value = "二级机构id", position = 0)
	private String o2Id;

	/**
	 * 上级机构id
	 */
	@ApiModelProperty(value = "上级机构id", position = 1)
	private String o1Id;

	/**
	 * 二级机构名称
	 */
	@ApiModelProperty(value = "二级机构名称", position = 2)
	private String o2Name;


}
