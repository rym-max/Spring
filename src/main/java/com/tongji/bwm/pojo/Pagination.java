package com.tongji.bwm.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Pagination<T> {
    private int page;
    private int rows;
    private int total;
    private int pageCount;
    private List<T> list;

    public Pagination(){

    }

    public Pagination(List<T> list,int total, int page, int rows) {
        this.page = page;
        this.rows = rows;
        this.total = total;
        pageCount = ((this.total % this.rows) == 0)? (this.total / this.rows): (this.total / this.rows +1);
        this.list = list;
    }

    public Pagination(int page, int rows, int total, int pageCount, List<T> list) {
        this.page = page;
        this.rows = rows;
        this.total = total;
        this.pageCount = pageCount;
        this.list = list;
    }
}
