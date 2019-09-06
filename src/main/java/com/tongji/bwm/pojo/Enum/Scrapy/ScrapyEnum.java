package com.tongji.bwm.pojo.Enum.Scrapy;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ScrapyEnum {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum StatusEnum{
        FINISHED("已完成",0),
        OPENING("打开中",1),
        SUSPENDING("等待中",2),
        RUNNING("运行中",3),
        CLOSING("关闭中",4),
        ERROR("错误",5);

        private String nameCN;
        private Integer code;


        StatusEnum(String nameCN, Integer code) {
            this.nameCN = nameCN;
            this.code = code;
        }

        public String getNameCN() {
            return nameCN;
        }

        public Integer getCode() {
            return code;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public enum ActionEnum{
        AUTO_RUN(0),
        CRAWL_OVER(1),
        USER_RUN(2),
        USER_STOP(3),
        ABORT(4),
        ERROR(5);

        private Integer code;

        ActionEnum(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum SpiderType{
        ENDLESS("永久",0),
        TEMP("临时",1);

        private String nameCN;
        private Integer code;

        SpiderType(String nameCN, Integer code) {
            this.nameCN = nameCN;
            this.code = code;
        }

        public String getNameCN() {
            return nameCN;
        }

        public Integer getCode() {
            return code;
        }
    }
}
