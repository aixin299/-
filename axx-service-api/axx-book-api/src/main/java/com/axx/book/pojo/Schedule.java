package com.axx.book.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/****
 * @Author:axx
 * @Description:Schedule构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Schedule",value = "Schedule")
@Data
public class Schedule implements Serializable{

	@ApiModelProperty(value = "",required = false)
	private Integer id;//

	@ApiModelProperty(value = "医生id",required = false)
	private Integer doctorId;//医生id

	@ApiModelProperty(value = "剩余放号数量",required = false)
	private Integer permit;//剩余放号数量

	@ApiModelProperty(value = "候补名额数量",required = false)
	private String waiting;//候补名额数量

	@ApiModelProperty(value = "日期",required = false)
	private Date dateTime;//日期



}
