package com.suncheng.defining.query.methods.common;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;

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

    List<T> findByCreateTimeBetween(Date startDate, Date endDate);

    List<T> findByCreateTimeBetweenOrderByCreateTimeDesc(Date startDate, Date endDate);

}
