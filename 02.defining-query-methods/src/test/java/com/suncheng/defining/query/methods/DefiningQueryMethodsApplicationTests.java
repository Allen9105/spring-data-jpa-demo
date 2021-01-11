package com.suncheng.defining.query.methods;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suncheng.defining.query.methods.entity.User;
import com.suncheng.defining.query.methods.repository.UserRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DefiningQueryMethodsApplicationTests {

    @Resource
    private UserRepository userRepository;

    @Test //通用查询方法
    public void findByCreateTimeBetween() throws Exception {
        Date startDate = DateUtils.parseDate("2021-01-09 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date endDate = DateUtils.parseDate("2021-01-10 23:59:59", "yyyy-MM-dd HH:mm:ss");
        List<User> userList = userRepository.findByCreateTimeBetweenOrderByCreateTimeDesc(startDate, endDate);
        //将list转为json输出
        System.out.println(new ObjectMapper().writeValueAsString(userList));
    }

    @Test
    public void findByName() {
        User user = userRepository.findByName("孙悟空00").orElse(new User());
        System.out.println(user);
    }

    @Test
    public void findByEmailIsNotNull() {
        userRepository.findByEmailIsNotNull().forEach(item -> System.out.println(item));
        /* 打印：
            Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.email as email3_0_, user0_.name as name4_0_ from user user0_ where user0_.email is not null
            User(id=18, name=孙悟空00, email=xxxx@0com, createTime=2021-01-09 22:51:08.0)
            User(id=20, name=孙悟空02, email=xxxx@2com, createTime=2021-01-11 22:51:08.0)
         */
    }

    @Test
    public void findByIdNotIn() {
        userRepository.findByIdNotIn(new Long[]{18L, 20L}).forEach(item -> System.out.println(item));
        /* 打印：
            Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.email as email3_0_, user0_.name as name4_0_ from user user0_ where user0_.id not in  (? , ?)
            User(id=19, name=孙悟空01, email=null, createTime=2021-01-10 22:51:08.0)
         */
    }

    @Test
    @Transactional //需要自己托管事物
    @Rollback(value = false) //必须加这行，强制让事物不回滚
    //注意：如果只加 @Transactional 的话，打印只会输出一条 SELECT 语句，默认执行完会回滚
    public void deleteByName() {
        userRepository.deleteByName("孙悟空00").forEach(item -> System.out.println(item));
        /* 打印：
            Hibernate: select user0_.id as id1_0_, user0_.create_time as create_t2_0_, user0_.email as email3_0_, user0_.name as name4_0_ from user user0_ where user0_.name=?
            User(id=15, name=孙悟空00, email=xxxx@0com, createTime=2021-01-08 20:30:55.0)
            Hibernate: delete from user where id=?
         */
    }
}
