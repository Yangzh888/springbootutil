package com.cn.springbootutil.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @description
 * @author: YZH
 * @create: 2020-03-05 15:24
 **/
@Data
@ToString
@ApiModel(value = "分页实体",description = "分页信息实体类")
public class  PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(name = "total",value = "总记录数")
    private int total;
    @ApiModelProperty(name = "rows",value = "符合条件的记录")
    private List<?> rows;
    /**
     * 每页条数
     */
    @ApiModelProperty(name = "pageSize",value = "页面大小")
    private Integer pageSize;
    /**
     * 当前页
     */
    @ApiModelProperty(name = "pageNumber",value = "当前页码")
    private Integer  pageNumber;

    public PageUtils(List<?> list, int total,int pageNumber,int pageSize) {
        this.rows = list;
        this.total = total;
        this.pageNumber=pageNumber;
        this.pageSize=pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
