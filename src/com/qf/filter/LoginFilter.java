package com.qf.filter;

import com.alibaba.druid.util.StringUtils;
import com.qf.entity.User;
import com.qf.service.impl.UserServiceImpl;
import com.qf.utils.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebFilter(
//        urlPatterns = {"/back/*","/UserServlet/*","/GoodsTypeServlet/*","/GoodsServlet/*"},
//        initParams = {@WebInitParam(name = "ignoreActionList",value = "login,register")})

public class LoginFilter implements Filter {

    // 白名单
    private List<String> ignoreActionList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       ignoreActionList = Arrays.asList(filterConfig.getInitParameter("ignoreActionList").split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp =(HttpServletResponse)servletResponse;

        // 1.获取当前的用户
        Object user = req.getSession().getAttribute(Constant.BACK_USER);

        // 2.获取当前路径
        String requestURI = req.getRequestURI();

        // 3.从路径中截取用户正在访问的action
        String actionName = requestURI.substring(requestURI.lastIndexOf("/")+1);

        // 2.判断用户是否登录
        if(user != null || ignoreActionList.contains(actionName) || isRemeber(req)){
            filterChain.doFilter(req,resp);
        }else{
            resp.sendRedirect(req.getContextPath()+"/backLogin.jsp?errosmg=xxxx");
        }
    }

    private boolean isRemeber(HttpServletRequest req) {

        // 1.获取cookie
        Cookie[] cookies = req.getCookies();

        if(cookies == null || cookies.length <=0){
            return  false;
        }

        // 2.找到remember的Cookie
        String rememberCookie = null;
        for(int i =0;i<cookies.length;i++){
            Cookie cookie = cookies[i];
            if(Constant.BACK_REMEBER.equals(cookie.getName())){
                rememberCookie=cookie.getValue();
                break;
            }
        }

        // 3.判断remember是否为空
        if(StringUtils.isEmpty(rememberCookie)){
            return  false;
        }

        // 4.拆分remember
        String[] split = rememberCookie.split(":");

        if(split == null || split.length <=0){
            return  false;
        }
        String username = split[0];
        String password = split[1];

        // 5.认证用户名和密码是正确
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login(username, password);

        // 查询到用户
        if(user == null){
            return  false;
        }

        req.getSession().setAttribute(Constant.BACK_USER,user);

        return true;
    }

    @Override
    public void destroy() {

    }
}
