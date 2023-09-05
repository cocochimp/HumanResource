package com.cocochimp.entity;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class FileProfession {

	/**
	 * 职称id
	 */
	@ApiModelProperty(value = "职称id", position = 0)
	private String pId;

	/**
	 * 职称
	 */
	@ApiModelProperty(value = "职称", position = 1)
	private String profession;


}
