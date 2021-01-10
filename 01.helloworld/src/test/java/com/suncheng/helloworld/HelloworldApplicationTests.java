package com.suncheng.helloworld;

import com.suncheng.helloworld.entity.User;
import com.suncheng.helloworld.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HelloworldApplicationTests {

    @Resource
    private UserRepository userRepository;


    @Test //添加用户
    public void save() {
        User user = User.builder().name("wangwu").email("asjd123@126.com").build();
        user = userRepository.save(user);
        System.out.println(user);
        /* 打印：
            Hibernate: insert into user (email, name) values (?, ?)
            User(id=5, name=wangwu, email=asjd123@126.com)
         */
    }

    @Test //添加多用户
    public void saveAll(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            userList.add(User.builder().name("孙悟空0" + i).email("xx" + "xx@" + i + "com").build());
        }
        Iterable<User> saveAll = userRepository.saveAll(userList);
        saveAll.forEach(item -> System.out.println(item));
        /* 打印：
            Hibernate: insert into user (email, name) values (?, ?)
            Hibernate: insert into user (email, name) values (?, ?)
            Hibernate: insert into user (email, name) values (?, ?)
            User(id=9, name=孙悟空00, email=xxxx@0com)
            User(id=10, name=孙悟空01, email=xxxx@1com)
            User(id=11, name=孙悟空02, email=xxxx@2com)
         */
    }

    @Test //自定义根据 [name] 查询
    public void findByName() {
        List<User> userList = userRepository.findByName("wangwu");
        userList.forEach(item -> System.out.println(item));
        /* 打印：
            Hibernate: select user0_.id as id1_0_, user0_.email as email2_0_, user0_.name as name3_0_ from user user0_ where user0_.name=?
            User(id=5, name=wangwu, email=asjd123@126.com)
         */
    }

    @Test //修改用户
    public void update() {
        /*
         * 新增和修改，都调用save()
         * 底层判断方式：主键、version 两种方式判断，新增还是修改
         */
        //ifPresent() 如果对象存在则进行处理逻辑,否则跳过
        userRepository.findById(3L).ifPresent(user -> {
            user.setName("王德发");
            user = userRepository.save(user);
            System.out.println(user);
        });
        /* 打印：
            Hibernate: select user0_.id as id1_0_0_, user0_.email as email2_0_0_, user0_.name as name3_0_0_ from user user0_ where user0_.id=?
            Hibernate: select user0_.id as id1_0_0_, user0_.email as email2_0_0_, user0_.name as name3_0_0_ from user user0_ where user0_.id=?
            Hibernate: update user set email=?, name=? where id=?
            User(id=3, name=王德发, email=asjd123@126.com)
         */
    }

    @Test //删除用户
    public void delete() {
        //如果输入的ID不存在的话，只会打印一条 SELECT 语句
        userRepository.delete(User.builder().id(5L).build());
        /* 打印：
            Hibernate: select user0_.id as id1_0_0_, user0_.email as email2_0_0_, user0_.name as name3_0_0_ from user user0_ where user0_.id=?
            Hibernate: delete from user where id=?
         */
    }

}
