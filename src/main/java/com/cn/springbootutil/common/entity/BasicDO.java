package com.cn.springbootutil.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yzh
 * 基础domain  所有的domain对象都继承这个
 */
@ApiModel(description = "基类")
@Data
public class BasicDO implements Serializable {

    @ApiModelProperty(value = "创建人")
    private  Long  userIdCreate	;

    @ApiModelProperty(value = "修改人")
    private  Long  userIdUpdate	;

    @ApiModelProperty(value = "创建时间")
    private Date createDate	    ;

    @ApiModelProperty(value = "修改时间")
    private  Date  updateDate	;

}