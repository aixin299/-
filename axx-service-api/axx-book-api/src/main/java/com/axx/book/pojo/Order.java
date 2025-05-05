package com.axx.book.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/****
 * @Author:axx
 * @Description:Order构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Order",value = "Order")
@Data
public class Order implements Serializable{

	@ApiModelProperty(value = "",required = false)

	private Integer id;//

	@ApiModelProperty(value = "",required = false)

	private Integer userId;//

	@ApiModelProperty(value = "",required = false)

	private Integer patientId;//

	@ApiModelProperty(value = "",required = false)

	private Integer scheduleId;//

}
