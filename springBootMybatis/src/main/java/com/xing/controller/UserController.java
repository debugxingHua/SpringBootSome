package com.xing.controller;

import com.github.pagehelper.PageHelper;
import com.xing.entity.UserEntity;
import com.xing.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 控制类
 */
@RestController
@RequestMapping("/user")
public class UserController {

    final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据id获取一个user
     * @param id id
     * @return user
     */
    @RequestMapping("/getOneById/{id}")
    public String getOneById(@PathVariable int id){
        return userMapper.getOne(id).toString();
    }

    /**
     *  获取全部
     * @return user list
     */
    @RequestMapping("/getAll/{num}")
    public String getAll(@PathVariable int num){
        if(StringUtils.isEmpty(num)){
            num = 2;
        }
        PageHelper.startPage(1,num);
        return userMapper.getAll().toString();
    }

    /**
     * 新增
     * @param user user
     * @return ok
     */
    @PostMapping("/insert")
    public String insert(@RequestBody UserEntity user){
        userMapper.insert(user);
        return "ok";
    }

    /**
     *  更新
     * @param user user
     * @return ok
     */
    @PostMapping("/update")
    public String update(@RequestBody UserEntity user){
        userMapper.update(user);
        return "update ok";
    }

    /**
     *  删除
     * @param id id
     * @return ok
     */
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        userMapper.delete(id);
        return "ok";
    }
}
