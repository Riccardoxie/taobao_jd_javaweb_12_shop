package com.qf.entity;

import lombok.Data;

import java.util.List;

//@Data
public class Page<T> {

    private Integer pageNum = 1;

    private Integer pageSize =10;

    private Integer totalPage;

    private Integer totalCount;

    private List<T> list;

    private String url; // 点击下一个页要发送的请求


    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {

        this.totalCount = totalCount;

        if(this.totalCount % this.pageSize == 0){
            this.totalPage = this.totalCount/this.pageSize;
        }else{
            this.totalPage = (this.totalCount/this.pageSize)+1;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", list=" + list +
                ", url='" + url + '\'' +
                '}';
    }
}
