package com.axx.book.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@ApiModel(description = "Doctor",value = "Doctor")

@Data
public class Doctor implements Serializable{

	private Integer id;

	private String name;

	private Integer age;

	private String gender;

	private String position;//职位

	private Integer departmentId;//科室id

	private String phone;

	private String major;//擅长

	private String doctorDesc;


}
