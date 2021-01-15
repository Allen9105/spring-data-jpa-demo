package com.suncheng.defining.query.methods.service;

import com.suncheng.defining.query.methods.entity.User;

import java.util.List;

public interface UserService {

    void saveBatch(List<User> userList);

}
