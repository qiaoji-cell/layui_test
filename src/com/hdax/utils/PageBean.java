package com.hdax.utils;

import java.util.List;

/**
 * Created by Joe on 2021-07-28 0028
 */
public class PageBean<E> {
    private int totalCount;//总条数
    private int currPage;//当前页
    private int pageSize;//每页显示条数

    private int startIndex;//当前数据库对应索引
    private int pageCount;//总页数
    private List<E> dataList;//当页数据集合
    public PageBean(int currPage,int pageSize,int totalCount){
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        //总条数除以每页显示条数
        this.pageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        //起始位置等于当前页+1*每页显示条数
        this.startIndex=(currPage-1)*pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int pageIndex) {
        this.startIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<E> getDataList() {
        return dataList;
    }

    public void setDataList(List<E> dataList) {
        this.dataList = dataList;
    }
}
