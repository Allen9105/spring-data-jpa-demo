package com.suncheng.defining.query.methods.common;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 定义通用Repository（选择性暴露Repository中方法）
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean //让Spring Data 不去实例化 repository
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    //通用查询：根据创建时间段查询并倒序
    List<T> findByCreateTimeBetweenOrderByCreateTimeDesc(Date startDate, Date endDate);

}
