package com.axx.book.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/****
 * @Author:axx
 * @Description:DepartmentFirst构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "DepartmentFirst",value = "DepartmentFirst")
@Data
public class DepartmentFirst implements Serializable{

	@ApiModelProperty(value = "",required = false)
	private Integer id;//

	@ApiModelProperty(value = "",required = false)
	private String departmentName;//


}
