package com.suncheng.defining.query.methods.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    //如不指定 "datetime DEFAULT NULL"，则默认创建类型 "datetime(6)"
    @Column(columnDefinition = "datetime DEFAULT NULL")
    //使用jackson提供的日期格式化（方便查看toString()）
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="Asia/Shanghai")
    private Date createTime;
}
