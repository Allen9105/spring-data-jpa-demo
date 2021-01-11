package com.suncheng.defining.query.methods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

@SpringBootApplication
//设置查询策略(默认)
//@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public class DefiningQueryMethodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DefiningQueryMethodsApplication.class, args);
    }

}
