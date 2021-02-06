package com.shure.jdbc;

import java.util.List;

public interface UserService {

    List<UserEntity> findAll(String name);

    UserEntity getUser(Integer id);

    Integer save(UserEntity user);

    void update(UserEntity user);

    void delete(Integer id);
}
