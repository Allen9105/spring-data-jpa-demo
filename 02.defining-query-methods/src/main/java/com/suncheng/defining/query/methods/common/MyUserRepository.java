package com.suncheng.defining.query.methods.common;

import com.suncheng.defining.query.methods.entity.User;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.Optional;

/**
 * 使用 @RepositoryDefinition 注解，定义 UserRepository（选择性暴露UserRepository中方法）
 */
@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface MyUserRepository {

    Optional<User> findByName(String name);

}
