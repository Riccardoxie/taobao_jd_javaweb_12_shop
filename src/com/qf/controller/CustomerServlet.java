package com.qf.controller;

import com.qf.entity.Customer;
import com.qf.entity.User;
import com.qf.utils.Constant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/CustomerServlet/*")
public class CustomerServlet extends  DispatcherServlet {

    public String login(String username, String password, HttpServletRequest request){

        // 对比用户名和密码
        if("admin".equals(username) && "admin".equals(password)){

            // 创建一个对象
            Customer customer = new Customer();
            customer.setUsername("admin");
            customer.setPassword("admin");
            customer.setId(2);

            // 放入session中
            request.getSession().setAttribute(Constant.FONT_USER,customer);

            // 重定向到首页
            return Constant.REDIRECT+":home.jsp";
        }

        return  Constant.FORWARD+":login.jsp?error=xxxx";
    }
}
