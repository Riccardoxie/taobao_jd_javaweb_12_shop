<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <% String path = request.getContextPath();
    	String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="css/loginstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<%--<script src="js/cloud.js" type="text/javascript"></script>--%>
    <script type="text/javascript">

        function initCode(){
            var showCode = $("#showCode");
//                showCode.prop("src",showCode.prop("src"));
            showCode.prop("src","ValidaCodeServlet/getValidaCode?date="+new Date().getTime());
        }

        $(function(){

            $("#changeCode").click(function(){
                initCode();
            });

            // 给表单添加一个提交事件
            $("#userLoginForm").submit(function(){

                // 1.获取用户输入的验证码
                var code = $("#code").val();

                // 2.【同步发送请求验证结果】
                var tdata = null;

//                $.ajax({
//                    url:"ValidaCodeServlet/checkCode",
//                    type:"GET",
//                    async:false, // 同步
//                    dataType:"JSON",
//                    data:{code:code},
//                    success:function(data){
//                        debugger
//                        tdata = data;
//                    }
//                });
//
//                if(tdata.flag){
//                    return true;
//                }else{
//                    alert("验证输入错误");
//                    initCode();
//                    return false;
//                }
               // return true/false;
            });
        })
    </script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })
});  
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <form id="userLoginForm" action="UserServlet/login" method="post">
        <ul>
        <li><input name="username" value="admin" type="text" class="loginuser" /></li>
        <li><input name="password" value="admin" type="text" class="loginpwd" /></li>
        <li>
            code:<input autocomplete="off" type="text" id="code" name="code" class="dfinput"/>
            <img id="showCode" src="ValidaCodeServlet/getValidaCode" alt=""/> <a href="javascript:void(0)" id="changeCode">看不清 换一张</a>
        </li>
        <li><input name="" type="submit" class="loginbtn" value="登录" /><label>
        <input name="remember" value="1" type="checkbox"/>记住密码</label><label><a href="#">忘记密码？</a></label></li>
        </ul>
    </form>
    
    </div>
    
    </div>

    <div class="loginbm">版权所有  2013  <a href="http://www.uimaker.com">uimaker.com</a>  仅供学习交流，勿用于任何商业用途</div>
	
    
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
