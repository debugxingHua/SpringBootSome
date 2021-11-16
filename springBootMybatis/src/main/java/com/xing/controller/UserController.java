package com.xing.controller;

import com.github.pagehelper.PageHelper;
import com.xing.entity.UserEntity;
import com.xing.mapper.UserMapper;
import org.apache.ibatis.jdbc.SQL;
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
     * 新增
     * @param user user
     * @return ok
     */
    @PostMapping("/insertUser")
    public String insertUser(@RequestBody UserEntity user){
        userMapper.insertUser(user);
        return "ok";
    }
    /**
     * 新增
     * @param user user
     * @return ok
     */
    @PostMapping("/insertCG")
    public String insertUserCG(@RequestBody UserEntity user){
//        userMapper.insertUserCD(user);
        userMapper.insertUserCD2(user);
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

    public static void main(String[] args) {
        // 官网实例
        SQL sql1 = new SQL() {{
            SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FULL_NAME");
            SELECT("P.LAST_NAME, P.CREATED_ON, P.UPDATED_ON");
            FROM("PERSON P");
            FROM("ACCOUNT A");
            INNER_JOIN("DEPARTMENT D on D.ID = P.DEPARTMENT_ID");
            INNER_JOIN("COMPANY C on D.COMPANY_ID = C.ID");
            WHERE("P.ID = A.ID");
            WHERE("P.FIRST_NAME like ?");
            OR();
            WHERE("P.LAST_NAME like ?");
            GROUP_BY("P.ID");
            HAVING("P.LAST_NAME like ?");
            OR();
            HAVING("P.FIRST_NAME like ?");
            ORDER_BY("P.ID");
            ORDER_BY("P.FULL_NAME");
        }};
        System.out.println(sql1);
        // 删除语句
        SQL sql2 = new SQL();
        sql2.DELETE_FROM("user");
        sql2.WHERE("id = 1");
        System.out.println("删除语句:");
        System.out.println(sql2);
        // 插入语句
        SQL sql3 = new SQL().
                INSERT_INTO("USER")
                .VALUES("ID, USERNAME", "${id}, ${userName}")
                .VALUES("REALNAME", "${realName}");
        System.out.println("插入语句:");
        System.out.println(sql3);
        // 查询语句
        String id = "id";
        String userName = "userName";
        String realName = "realName";
        SQL sql4 = new SQL() {{
            SELECT("U.ID, U.USERNAME, U.PASSWORD");
            FROM("PERSON U");
            if (id != null) {
                WHERE("U.ID = ${id}");
            }
            if (userName != null) {
                WHERE("U.USERNAME like ${userName}");
            }
            if (realName != null) {
                WHERE("P.REALNAME like ${realName}");
            }
            ORDER_BY("P.LAST_NAME");
        }};
        System.out.println("查询语句:");
        System.out.println(sql4);
        // 更新修改语句
        SQL sql5 = new SQL();
        sql5.UPDATE("USER");
        sql5.SET("USERNAME = ${userName}");
        sql5.WHERE("ID = ${id}");
        System.out.println("更新语句:");
        System.out.println(sql5);
    }
}
