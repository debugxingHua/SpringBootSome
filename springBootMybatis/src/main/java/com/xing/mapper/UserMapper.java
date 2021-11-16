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


    @Insert("insert into user (id, userName, passWord, realName) values(#{id}, #{userName}, #{passWord}, #{realName})")
//    @SelectKey(statement="call next value for user_sequence", keyProperty="id", before=true, resultType=int.class)
    int insertUser(UserEntity user);

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

    /**
     * 存过插入用户
     * @param user user
     */
    void insertUserCD(UserEntity user);

    /**
     *  试试select标签
     * @param user
     */
    void insertUserCD2(UserEntity user);
}
