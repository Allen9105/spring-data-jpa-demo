package com.suncheng.defining.query.methods.common;

import com.suncheng.defining.query.methods.entity.User;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;
import java.util.Optional;

/**
 * 使用 @RepositoryDefinition 注解，定义 UserRepository（选择性暴露UserRepository中方法）
 */
@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface MyUserRepository {

    //根据姓名查询，这里其实建议返回List<User>
    //如果有多条 IncorrectResultSizeDataAccessException: query did not return a unique result: 条数; 异常
    Optional<User> findByName(String name);

    //查询id不为xx,xx数据
    List<User> findByIdNotIn(Long[] ids);

}
