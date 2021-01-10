package com.suncheng.helloworld.repository;

import com.suncheng.helloworld.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    //Ctrl + F12 查看父类方法，勾选 Inherited members (Ctrl+F12)

    //自定义根据 [name] 查询
    List<User> findByName(String name);
}
