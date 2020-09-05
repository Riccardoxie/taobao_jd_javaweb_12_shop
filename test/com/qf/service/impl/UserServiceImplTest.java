package com.qf.service.impl;

import com.qf.entity.Page;
import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.utils.DaoUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import org.omg.CORBA.INTERNAL;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImplTest {

    private IUserService userService = new UserServiceImpl();

    @Test
    public void insert() throws Exception {

        for(int i =0;i<10;i++){
            User user = new User();
            user.setUsername("admin_"+i);
            user.setPassword("admin_"+i);
            user.setEmail("admin_"+i+"@qf.com");
            user.setAge(18+i);
            user.setSex(i % 2);
            user.setPng("xxx_"+i+".png");
            user.setBirthday(new Date());

            System.out.println(userService.insert(user));
        }


    }

    @Test
    public void test22(){

        String sql ="insert into t_user (username) values(?)";
        int x = DaoUtils.commonInsert(sql, "张三");
        System.out.println(x);
    }

    @Test
    public void update() throws Exception {
        User user = new User();
        user.setId(1);
        user.setUsername("qf");
        user.setPassword("QF");
        user.setEmail("QF@qf.com");
        user.setAge(20);
        user.setSex(0);
        user.setPng("QF.png");
        user.setBirthday(new Date());
        System.out.println(userService.update(user));
    }

    @Test
    public void delete() throws Exception {
        System.out.println(userService.delete(21));
    }

    @Test
    public void selectById() throws Exception {
        Integer i = 1234567890;
        System.out.println(userService.selectById(21));
    }

    @Test
    public void selectPage() throws Exception {

        Page<User> page = new Page<>();
        userService.selectPage(page);
        System.out.println(page);
    }

    @Test
    public void test2(){

        Map<String,Object> map = new HashMap<>();
//        map.put("xxx","2020-12-12");
        map.put("birthday",new Date());

        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }

}
