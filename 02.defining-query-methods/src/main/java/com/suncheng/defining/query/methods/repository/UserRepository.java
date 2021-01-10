package com.suncheng.defining.query.methods.repository;


import com.suncheng.defining.query.methods.common.BaseRepository;
import com.suncheng.defining.query.methods.common.MyUserRepository;
import com.suncheng.defining.query.methods.entity.User;

public interface UserRepository extends BaseRepository<User, Long>, MyUserRepository {

}
