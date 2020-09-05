package com.qf.controller;

import cn.dsna.util.images.ValidateCode;
import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ValidaCodeServlet/*")
public class ValidaCodeServlet extends DispatcherServlet {

    public void getValidaCode(HttpServletRequest request,HttpServletResponse response){

        System.out.println("ValidaCodeServlet.getValidaCode");

        // 1.创建一个验证码对象
        ValidateCode validateCode = new ValidateCode(200,50,4,10);

        // 获取code
        String code = validateCode.getCode();

        System.out.println(code);
        // 放到session中
        request.getSession().setAttribute("code",code);

        // 验证码图片写入到resp中
        try {
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 对比验证码
     * @param code 用户输入的code
     * @param request 获取sesion
     */
    public void checkCode(String code,HttpServletRequest request,HttpServletResponse response){

        // 获取sessionz中的code
        String sCode = request.getSession().getAttribute("code").toString();

        Map<String,Object> map = new HashMap<>();


//        if(sCode.equalsIgnoreCase(code)){
//            map.put("flag",true);
//        }else{
//            map.put("falg",false);
//        }|

        // 定义一个表示
        boolean flag = false;

        if(sCode.equalsIgnoreCase(code)){
            flag = true;
        }

        map.put("flag",flag);


        responseString(JSON.toJSONString(map),response);

    }

}
