package com.axx.book.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/****
 * @Author:axx
 * @Description:Patients构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Patients",value = "Patients")
@Data
public class Patients implements Serializable{

	@ApiModelProperty(value = "id",required = false)
	private Integer id;//id

	@ApiModelProperty(value = "姓名",required = false)
	private String name;//姓名

	@ApiModelProperty(value = "性别",required = false)
	private String gender;//性别

	@ApiModelProperty(value = "身份证号",required = false)
	private String IDnumber;//身份证号

	@ApiModelProperty(value = "用户id",required = false)
	private Integer userID;//用户id

}
