package com.cocochimp.entity;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class FileAcademic {

	/**
	 * 职称id
	 */
	@ApiModelProperty(value = "职称id", position = 0)
	private String aId;

	/**
	 * 职称姓名
	 */
	@ApiModelProperty(value = "职称姓名", position = 1)
	private String academic;


}
