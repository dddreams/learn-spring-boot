package com.shure.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserEntity> findAll(String name){
        List<UserEntity> list = jdbcTemplate.query("SELECT * FROM t_user WHERE name = ?", (res, i) -> {
            UserEntity user = new UserEntity();
            user.setId(res.getInt("ID"));
            user.setName(res.getString("NAME"));
            user.setAddress(res.getString("ADDRESS"));
            user.setAge(res.getInt("AGE"));
            user.setGender(res.getBoolean("GENDER"));
            return user;
        }, name);
        return list;
    }

    public UserEntity getUser(Integer id){
        List<UserEntity> list = jdbcTemplate.query("SELECT * FROM t_user WHERE id = ?", (res, i) -> {
            UserEntity user = new UserEntity();
            user.setId(res.getInt("ID"));
            user.setName(res.getString("NAME"));
            user.setAddress(res.getString("ADDRESS"));
            user.setAge(res.getInt("AGE"));
            user.setGender(res.getBoolean("GENDER"));
            return user;
        }, id);
        return list.get(0);
    }

    public Integer save(UserEntity user){
        return jdbcTemplate.update("INSERT INTO t_user(name, age, gender, address) VALUES(?, ?, ?, ?)",
                user.getName(),
                user.getAge(),
                user.isGender(),
                user.getAddress());
    }

    public void update(UserEntity user){
        jdbcTemplate.update("UPDATE t_user SET name = ?, age = ?, gender = ?, address = ? WHERE id = ?",
                user.getName(),
                user.getAge(),
                user.isGender(),
                user.getAddress(),
                user.getId());
    }

    public void delete(Integer id){
        jdbcTemplate.update("DELETE FROM t_user WHERE id = ?", id);
    }


}
