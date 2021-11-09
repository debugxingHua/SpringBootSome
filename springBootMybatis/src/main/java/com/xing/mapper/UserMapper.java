package com.xing.mapper;

import com.xing.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 面向接口
 */
@Repository
public interface UserMapper {

    UserEntity getOne(int id);

    @Select("SELECT * FROM user")
    List<UserEntity> getAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "userName", column = "userName")
    })
    UserEntity getOneById(Integer id);

    @Insert("INSERT INTO user(id, userName, passWord, realName) VALUES(#{id}, #{userName}, #{passWord}, #{realName})")
    void insert(UserEntity user);

    @Update("UPDATE user SET id=#{id},userName=#{userName},passWord=#{passWord},realName=#{realName} WHERE id =#{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    void delete(Integer id);
}
