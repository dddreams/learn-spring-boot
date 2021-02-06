package com.shure.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserJdbcDao userJdbcDao;

    @Override
    public List<UserEntity> findAll(String name) {
        return userJdbcDao.findAll(name);
    }

    @Override
    public UserEntity getUser(Integer id) {
        return userJdbcDao.getUser(id);
    }

    @Override
    public Integer save(UserEntity user) {
        return userJdbcDao.save(user);
    }

    @Override
    public void update(UserEntity user) {
        userJdbcDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        userJdbcDao.delete(id);
    }
}
