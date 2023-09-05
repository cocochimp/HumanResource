package com.cocochimp.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class FileInfo {

	/**
	 * 档案id
	 */
	@ApiModelProperty(value = "档案id")
	@TableId(type = IdType.ID_WORKER_STR)
	private String fId;

	/**
	 * 申请人id
	 */
	@ApiModelProperty(value = "申请人id")
	private String uId;

	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名", position = 1)
	private String name;

	/**
	 * 性别
	 */
	@ApiModelProperty(value = "性别", position = 2)
	private String sex;

	/**
	 * 出生日期
	 */
	@ApiModelProperty(value = "出生日期", position = 3)
	private String birth;

	/**
	 * 年龄
	 */
	@ApiModelProperty(value = "年龄", position = 4)
	private String age;

	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像【x】", position = 5)
	private String image;

	/**
	 * 职称id
	 */
	@ApiModelProperty(value = "职称id", position = 6)
	private String professionId;

	/**
	 * 三级机构id
	 */
	@ApiModelProperty(value = "三级机构id", position = 7)
	private String organizionId;

	/**
	 * 民族id
	 */
	@ApiModelProperty(value = "民族id", position = 8)
	private String nationId;

	/**
	 * 学历id
	 */
	@ApiModelProperty(value = "学历id", position = 9)
	private String academicId;

	/**
	 * 宗教id
	 */
	@ApiModelProperty(value = "宗教id", position = 10)
	private String religionId;

	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱", position = 11)
	private String email;

	/**
	 * 电话
	 */
	@ApiModelProperty(value = "电话", position = 12)
	private String phone;

	/**
	 * QQ
	 */
	@ApiModelProperty(value = "QQ", position = 13)
	private String qq;

	/**
	 * 居住地址
	 */
	@ApiModelProperty(value = "居住地址", position = 14)
	private String address;

	/**
	 * 个人履历
	 */
	@ApiModelProperty(value = "个人履历", position = 15)
	private String personalHistory;

	/**
	 * 家庭关系信息
	 */
	@ApiModelProperty(value = "家庭关系信息", position = 16)
	private String familyRelationship;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", position = 17)
	private String remarks;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间【x】", position = 18)
	private String createTime;

	/**
	 * 复核情况【1true】
	 */
	@ApiModelProperty(value = "复核情况【x】", position = 20)
	private String isCheck;

	/**
	 * 是否被驳回【1true】
	 */
	@ApiModelProperty(value = "是否被驳回【x】", position = 20)
	private String isReject;

	/**
	 * 逻辑删除【1true】
	 */
	@ApiModelProperty(value = "逻辑删除【x】", position = 19)
	private String isDeleted;


}
