package com.cocochimp.entity;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class SysOrgan3 {

	/**
	 * 三级机构id
	 */
	@ApiModelProperty(value = "三级机构id", position = 0)
	private String o3Id;

	/**
	 * 上级机构id
	 */
	@ApiModelProperty(value = "上级机构id", position = 1)
	private String o2Id;

	/**
	 * 三级机构名称
	 */
	@ApiModelProperty(value = "三级机构名称", position = 2)
	private String o3Name;


}
