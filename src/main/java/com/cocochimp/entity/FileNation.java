package com.cocochimp.entity;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class FileNation {

	/**
	 * 民族id
	 */
	@ApiModelProperty(value = "民族id", position = 0)
	private String nId;

	/**
	 * 民族
	 */
	@ApiModelProperty(value = "民族", position = 1)
	private String nation;


}
