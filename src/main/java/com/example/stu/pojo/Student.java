package com.example.stu.pojo;


import java.sql.Timestamp;
import java.util.Date;

//         Id 字符串类型长度 40,
//         姓名 (name)字符串类型长度 40,
//         出生日期(birthday) 日期类型,
//         备注 (description)字符串类型长度 255,
//         平均分(avgscore) 整数类型,
public class Student {

    private String id;
    private String name;
    private Date birthday;
    private String description;
    private Integer avgscore;

    public Student() {
    }

    public Student(String id, String name, Date birthday, String description, Integer avgscore) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.description = description;
        this.avgscore = avgscore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(Integer avgscore) {
        this.avgscore = avgscore;
    }
}
