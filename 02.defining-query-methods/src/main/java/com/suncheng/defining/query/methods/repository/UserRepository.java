package com.suncheng.defining.query.methods.repository;

import com.suncheng.defining.query.methods.common.BaseRepository;
import com.suncheng.defining.query.methods.entity.User;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

/**
 * 使用 @RepositoryDefinition 注解，定义 UserRepository（选择性暴露UserRepository中方法）
 * 相当于： public interface UserRepository extends Repository<User, Long>
 */
@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserRepository extends BaseRepository<User, Long> {

    /**
     * 根据邮箱后缀查询
     * @param lastEmail
     * @return List<User>
     */
    List<User> findByEmailEndingWith(String lastEmail);

    /**
     * 根据邮箱地址查询（忽略大小写）
     * @param email
     * @return User
     */
    User findByEmailIgnoreCase(String email);

    /**
     * 根据姓名删除
     * @param name
     * @return row
     */
    long deleteByName(String name);

}
