package com.suncheng.defining.query.methods.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    //如不指定 "datetime DEFAULT NULL"，则默认创建类型 "datetime(6)"
    @Column(columnDefinition = "datetime DEFAULT NULL")
    private Date createTime;
}
