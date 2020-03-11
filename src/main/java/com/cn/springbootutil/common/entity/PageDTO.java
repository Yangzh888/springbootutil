package com.cn.springbootutil.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description
 * @author: YZH 接收所有DTO的分页基类
 * @create: 2020-03-06 09:12
 **/
@Data
public class PageDTO implements Serializable {

    /**
     * 起始页
     */
    private Integer pageStart;
    /**
     * 结束页
     */
    private Integer pageSize;
    /**
     * 当前页
     */
    private Integer  pageNumber;

    /**
     * 截止页
     */
    private  Integer  pageEnd;

    /**
     * 排序条件
     */
    private String sort;


    /**
     * 页面大小默为10
     * @return
     */
    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    /**
     * 当前页数默认为第一页
     * @return
     */
    public Integer getPageNumber() {
        return pageNumber == null ? 1 : pageNumber;
    }

    public Integer getPageStart() {
        return pageStart == null ?((getPageNumber()-1)*getPageSize()) : pageStart;


    }

    public Integer getPageEnd() {

        return pageEnd == null ? (getPageNumber()*getPageSize()) : pageEnd;

    }


}
