package com.tongji.bwm.entity.Book;

import com.tongji.bwm.entity.Basic.PKINTEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Book_Journal")
public class Journal extends PKINTEntity {

    @Column(name = "Name",length = 100)
    private String name;//期刊名称
    @Column(length = 100)
    private String nameEn;//期刊英文名称
    @Column(length = 10)
    private String gCH;//馆藏号
    @Column(length = 255)
    private String subject;//发文主题
    @Column(length = 255)
    private String domain;//发文领域
    @Column(length = 255)
    private String author;//发文作者
    @Column(length = 255)
    private String organ;//发文机构
    @Column(length = 50)
    private String iSSN;//国际标准刊号
    @Column(length = 255)
    private String competentUnit;//主管单位
    @Column(length = 255)
    private String hostUnit;//主办单位
    @Column(length = 50)
    private String editor;//主编
    @Column(length = 255)
    private String address;//地址
    @Column(length = 50)
    private String zipCode;//邮编
    @Column(length = 50)
    private String phoneNumber;//电话
    @Column(length = 50)
    private String email;//电子邮件
    @Column(length = 255)
    private String href;//链接
    @Column(length = 255)
    private String cover;//封面

    private String description;//期刊描述

    private Integer zpCount;//作品数

    private Integer byCount;//被引量

    private Integer hIndex;//H指数

}
