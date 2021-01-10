package com.suncheng.defining.query.methods;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suncheng.defining.query.methods.entity.User;
import com.suncheng.defining.query.methods.repository.UserRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void findByName(){
       User user = userRepository.findByName("孙悟空00").orElse(new User());
       System.out.println(user);
   }

}
