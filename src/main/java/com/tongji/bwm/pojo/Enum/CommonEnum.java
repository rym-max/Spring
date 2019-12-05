package com.tongji.bwm.pojo.Enum;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

public class CommonEnum {
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum WhetherEnum{
        No("否",0),Yes("是",1),;

        private String NameCN;
        private int code;

        WhetherEnum(String name, int code) {
            NameCN = name;
            this.code = code;
        }

        public String getNameCN() {
            return NameCN;
        }

        public int getCode() {
            return code;
        }
    }
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum AvailableEnum{
        Disable("禁用",0),Enable("启用",1),Others("未知",2);
        private String NameCN;
        private Integer code;

        AvailableEnum(String name, int code) {
            NameCN = name;
            this.code = code;
        }

        public String getNameCN() {
            return NameCN;
        }

        public Integer getCode() {
            return code;
        }
    }
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum AuditStatusEnum{
        NotPass("未通过",0),Pass("已通过",1),Others("未知",2);
        private String NameCN;
        private int code;

        AuditStatusEnum(String name, int code) {
            NameCN = name;
            this.code = code;
        }

        public String getNameCN() {
            return NameCN;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return String.valueOf(code);
        }
    }
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum DataType{
        String("字符串",0),Number("数字",1),DateTime("日期",2);

        private String NameCN;
        private Integer code;


        DataType(java.lang.String nameCN, Integer code) {
            NameCN = nameCN;
            this.code = code;
        }

        public String getNameCN() {
            return NameCN;
        }

        public Integer getCode() {
            return code;
        }

        public static String valueofCN(int ordinal){
            DataType[] values = DataType.values();
            if(ordinal<0 || ordinal>values.length){
                return "未知";
            }
            return values[ordinal].NameCN;
        }
    }
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum ControlType{
        Text("单行文本框",0),
        MultilineText("多行文本框",1),
        Html("HTML编辑器",2),
        Select("下拉列表",3),
        Radio("单选按钮",4),
        Checkbox("多选按钮",5),
        DateTime("日期选择框(yyyy-MM-dd HH:mm:ss)",6),
        Date("日期选择框(yyyy-MM-dd)",7),
        UploadCover("上传封面",8),
        Upload("上传文件控件",9);

        private String NameCN;
        private Integer code;

        ControlType(String nameCN,Integer code) {
            NameCN = nameCN;
            this.code=code;
        }

        public String getNameCN() {
            return NameCN;
        }

        public Integer getCode() {
            return code;
        }

        public static String valueofCN(int ordinal){
            ControlType[] values = ControlType.values();
            if(ordinal<0 || ordinal>values.length){
                return "未知";
            }
            return values[ordinal].NameCN;
        }
    }
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum CustomMetadataFieldObject{

        Channel("栏目",0),
        Category("分类",1),
        Others("其他",3);


        private String NameCN;
        private Integer code;


        CustomMetadataFieldObject(String nameCN, Integer code) {
            NameCN = nameCN;
            this.code = code;
        }

        public String getNameCN() {
            return NameCN;
        }

        public Integer getCode() {
            return code;
        }
    }
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @JsonIgnoreProperties(value = {"role"})
    public enum RoleType{
        Administrator("超级管理员","ROLE_ADMIN",0),
        ArticleAdministrator("文献管理员","ROLE_ARTICLE",1),
        ArticleEditor("文献编辑","ROLE_EDITOR",2);

        private String NameCN;
        private String Role;
        private Integer Code;

        RoleType(String nameCN,String role,Integer code) {
            NameCN = nameCN;
            Role = role;
            Code = code;
        }

        public String getNameCN() {
            return NameCN;
        }

        public String getRole() {
            return Role;
        }

        public Integer getCode() {
            return Code;
        }
    }

    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public enum DetailPageType{
        Error("错误",0),Article("文章",1),Journal("期刊",2);


        private String nameCN;

        private Integer code;

        DetailPageType(String nameCN, Integer code) {
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

    public enum RegionType{
        All("全部",0),
        EuUnion("欧盟",1),
        EuSubArea("欧洲次区域",2),
        EuUnionMember("成员国",3),
        EuCountries("其他国家",4);

        private String nameCN;
        private Integer parentid;

        RegionType(String nameCN, Integer parentid) {
            this.nameCN = nameCN;
            this.parentid = parentid;
        }

        public String getNameCN() {
            return nameCN;
        }

        public Integer getParentid() {
            return parentid;
        }
    }
}
