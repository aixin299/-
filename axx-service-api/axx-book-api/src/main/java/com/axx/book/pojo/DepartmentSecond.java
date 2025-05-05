package com.axx.book.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/****
 * @Author:axx
 * @Description:DepartmentSecond构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "DepartmentSecond",value = "DepartmentSecond")
@Data
public class DepartmentSecond implements Serializable{

	@ApiModelProperty(value = "",required = false)
	private Integer id;//

	@ApiModelProperty(value = "",required = false)
	private String departmentName;//

	@ApiModelProperty(value = "",required = false)
	private Integer firstDepartment;//





}
