package com.tongji.bwm.entity.ERMS;

import com.tongji.bwm.entity.Basic.PKGUIDEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "ERMS_Achievement")
public class Achievement extends PKGUIDEntity {

    @NotEmpty(message = "题名长度不能超过100个字符！")
    @Size(min=1,max=100,message = "")
    @Column(length = 100)
    private String title;
    @Column(length = 100)
    private String author;
    @Column(length = 100)
    private String subject;
    @Column(length = 200)
    private String filePath;

    private String fileSize;

    private Date releaseDate;//发布日期
    @Column(length = 512)
    private String summary;

    private String description;

    private Integer click;

    private Integer sort;

    private Integer status;

    private Integer userId;
}
