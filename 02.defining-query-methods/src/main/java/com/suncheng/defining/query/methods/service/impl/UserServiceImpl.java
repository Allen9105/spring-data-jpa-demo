package com.suncheng.defining.query.methods.service.impl;

import com.suncheng.defining.query.methods.entity.User;
import com.suncheng.defining.query.methods.repository.UserRepository;
import com.suncheng.defining.query.methods.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @PersistenceContext //通过该注解获取容器托管的EntityManager对象
    private EntityManager em;

    @Override
    @Transactional
    public void saveBatch(List<User> userList) {
        userList.forEach(user ->
            em.persist(user)
        );
        //不需要关闭操作 em.close()
    }
}
