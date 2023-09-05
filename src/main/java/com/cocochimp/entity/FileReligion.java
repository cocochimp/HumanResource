package com.cocochimp.entity;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class FileReligion {

	/**
	 * 宗教id
	 */
	@ApiModelProperty(value = "宗教id", position = 0)
	private String rId;

	/**
	 * 宗教
	 */
	@ApiModelProperty(value = "宗教", position = 1)
	private String religion;


}
