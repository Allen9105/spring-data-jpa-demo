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
@NoRepositoryBean //让Spring Data JPA不去实例化 repository
public interface  BaseRepository<T, ID extends Serializable> extends Repository<T, ID> {

    /**
     * 按照时间段查询并倒序
     * @param startDate
     * @param endDate
     * @return List<T>
     */
    List<T> findByCreateTimeBetweenOrderByCreateTimeDesc(Date startDate, Date endDate);

    /**
     * 按照时间段查询总条数
     * @param statDate
     * @param endDate
     * @return count
     */
    long countByCreateTimeBetweenOrderByCreateTime(Date statDate, Date endDate);

}
