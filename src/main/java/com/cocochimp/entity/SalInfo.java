package com.cocochimp.entity;


import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class SalInfo {

	/**
	 * 薪酬id
	 */
	@ApiModelProperty(value = "薪酬id", position = 0)
	private String sId;

	/**
	 * 登记人id
	 */
	@ApiModelProperty(value = "登记人id", position = 1)
	private String uId;

	/**
	 * 薪酬标准id
	 */
	@ApiModelProperty(value = "薪酬标准名称", position = 2)
	private String o3Id;

	/**
	 * 制定人姓名
	 */
	@ApiModelProperty(value = "制定人姓名", position = 3)
	private String fName;

	/**
	 * 总薪酬
	 */
	@ApiModelProperty(value = "总薪酬", position = 4)
	private double allmoney;

	/**
	 * 基本工资
	 */
	@ApiModelProperty(value = "基本工资", position = 5)
	private double basic;

	/**
	 * 交通补贴
	 */
	@ApiModelProperty(value = "交通补贴", position = 6)
	private double transport;

	/**
	 * 午餐补贴
	 */
	@ApiModelProperty(value = "午餐补贴", position = 7)
	private double lunch;

	/**
	 * 通信补贴
	 */
	@ApiModelProperty(value = "通信补贴", position = 8)
	private double communication;

	/**
	 * 养老保险
	 */
	@ApiModelProperty(value = "养老保险", position = 9)
	private double pension;

	/**
	 * 失业保险
	 */
	@ApiModelProperty(value = "失业保险", position = 10)
	private double unemployment;

	/**
	 * 医疗保险
	 */
	@ApiModelProperty(value = "医疗保险", position = 11)
	private double medical;

	/**
	 * 住房公积金
	 */
	@ApiModelProperty(value = "住房公积金", position = 12)
	private double homemoney;

	/**
	 * 复核情况【1true】
	 */
	@ApiModelProperty(value = "复核意见", position = 13)
	private String review;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间【x】", position = 14)
	private String createTime;

	/**
	 * 复核情况【1true】
	 */
	@ApiModelProperty(value = "复核情况【x】", position = 15)
	private String isCheck;

	/**
	 * 复核情况【1true】
	 */
	@ApiModelProperty(value = "是否被驳回【×】", position = 16)
	private String isReject;

	/**
	 * 发放情况【1true】
	 */
	@ApiModelProperty(value = "发放情况【x】", position = 17)
	private String isGive;

	/**
	 * 逻辑删除【1true】
	 */
	@ApiModelProperty(value = "逻辑删除【x】", position = 18)
	private String isDeleted;

}
