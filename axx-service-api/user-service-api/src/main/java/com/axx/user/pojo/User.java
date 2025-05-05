package com.axx.user.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/****
 * @Author:axx
 * @Description:User构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "User",value = "User")
@Data
public class User implements Serializable{

	@ApiModelProperty(value = "",required = false)

	private Integer id;//

	@ApiModelProperty(value = "",required = false)
	private String phone;//

	@ApiModelProperty(value = "",required = false)
	private String password;//

	@ApiModelProperty(value = "昵称（不可用于登录）",required = false)
	private String nickname;//昵称（不可用于登录）

	@ApiModelProperty(value = "用户状态 1启用 0禁用 （用于预约黑名单）",required = false)
	private Integer status;//用户状态 1启用 0禁用 （用于预约黑名单）

	@ApiModelProperty(value = "用户角色 0普通用户 1管理员",required = false)
	private Integer role;//用户角色 0普通用户1管理员



}
