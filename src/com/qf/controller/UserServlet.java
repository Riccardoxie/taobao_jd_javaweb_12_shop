package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.qf.entity.Page;
import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/UserServlet/*")
public class UserServlet extends DispatcherServlet {

    private IUserService userService = new UserServiceImpl();

    public String getUserPage(Page<User> page, HttpServletRequest request) {

        // 1.调用servie层获取Page中的属性
        userService.selectPage(page);

        // 2.设置url
        page.setUrl("/UserServlet/getUserPage");

        // 3.把Page放到req作用域中
        request.setAttribute("page", page);

        // 4.跳转到展示页面
        return Constant.FORWARD + ":back/user/userinfo.jsp";
    }

    public void validateEmail(String email, HttpServletResponse response) {
        System.out.println("email = [" + email + "], response = [" + response + "]");
        // 1.根据邮箱查询用户
        User user = userService.selectUserByEmail(email);

        // 2.创建一个Map用来转最后的标识
        Map<String, Object> map = new HashMap<>();

        // 3.判断
        if (user == null) {
            map.put("flag", true); // 可以使用
        } else {
            map.put("flag", false); // 可以使用
        }

        // 4.把map转成JSON字符串
        String jsonString = JSON.toJSONString(map);

        // 5.响应
        responseString(jsonString, response);
    }

    public String addUser(User user) {
        int insert = userService.insert(user);
        return  responsePage(insert,"UserServlet/getUserPage");
    }

    public String getUserById(Integer id,HttpServletRequest request){

        // 1.根据id查询对象
        User user = userService.selectById(id);

        // 2.把对象放入到req作用域中
        request.setAttribute("user",user);

        // 3.转发到更新页面
        return  Constant.FORWARD+":back/user/updateuser.jsp";
    }

    public String updateUser(User user){
      return responsePage(userService.update(user),"UserServlet/getUserPage");
    }

    public String deleteUserById(Integer id){

        return  responsePage(userService.delete(id),"UserServlet/getUserPage");
    }

    public void batchDel(HttpServletRequest request,HttpServletResponse response){

        // 获取传递的要删除的id，因为ids是一个数组，所以需要加[]
        String[] ids = request.getParameterValues("ids[]");

        // 批量删除
        int i = userService.batchDel(ids);

        // 创建一个Map
        Map<String,Object> map = new HashMap<>();

        // 添加成功还是失败
        if(i > 0){
            map.put("flag",true);
        }else{
            map.put("flag",false);
        }

        // 响应浏览器
        responseString(JSON.toJSONString(map),response);
    }


    public String login(String remember,String username,String password,HttpServletRequest request,HttpServletResponse response){

        // 1.查询数据库
        User user = userService.login(username,password);

        // a.根据用户名查询对象,如果这里返回null，说明用户名不存在

        // b.用户是否是冻结状态，判断状态是否为正常

        // c.校验密码

        // 2.判断用户是否查询到
        if(user != null){

            // 3.把user对象放入到session中
            request.getSession().setAttribute(Constant.BACK_USER,user);

            // 登录成功后需要记住我
            if("1".equals(remember)){ // 用户勾选了记住我

                // 创建一个Cookie
                Cookie cookie = new Cookie(Constant.BACK_REMEBER,username+":"+password);
                cookie.setMaxAge(60*60*24*7); // 保留一周
                cookie.setPath("/"); // 设置/代表访问当前域下面的所有资源都可以携带这个cookie

                // 把cookie写入到resp中
                response.addCookie(cookie);
            }

            // 4.重定向到后台首页
           return Constant.REDIRECT+":back/main.jsp";
        }

        // 5.登录失败跳转到登录页面
        return  Constant.REDIRECT+":backLogin.jsp?errormsg=xxxx";
    }


}
