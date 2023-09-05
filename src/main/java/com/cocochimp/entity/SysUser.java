package com.cocochimp.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class SysUser {

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	@TableId(value = "u_id",type = IdType.ID_WORKER_STR)
	private String uId;

	/**
	 * 用户名
	 */
	@ApiModelProperty(value = "用户名", position = 1)
	private String username;

	/**
	 * 密码
	 */
	@ApiModelProperty(value = "密码", position = 2)
	private String password;

	/**
	 * 角色id
	 */
	@ApiModelProperty(value = "角色id", position = 3)
	private String rId;

	/**
	 * 逻辑删除【1true】
	 */
	@ApiModelProperty(value = "逻辑删除【x】", position = 4)
	private String isDeleted;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
