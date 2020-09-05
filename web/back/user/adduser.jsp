<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
	String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="js/jquery.js"></script>

	<script type="text/javascript" src="js/validate.min.js"></script>

	<script type="text/javascript" src="js/messages_zh.js"></script>

	<script type="text/javascript">

		var flag = true; // 表单可以提交

		$(function(){

		    $("#addUserForm").submit(function(){

				if(required("#email") & required("#username") & required("#password") &  required("#repassword") &  repassword("#password","#repassword")){
				    return true; // 表单提交
				}else{
				    return false; // 表单不提交
				}
			})

			$("#email").blur(function(){

			    // debugger
			    // 1.获取用户输入的邮箱
			    var email  = $(this).val();

			    // 2.判断邮箱的格式

				// 3.发送到服务端
				var param = new Object();
				param.email = email; // 给对象中添加一个属性

				$.get("UserServlet/validateEmail",param,function(data){
					if(!data.flag){
                       	 flag = false;
						$("#email_msg").text("该邮箱已被注册");
					}else{
                        $("#email_msg").text("可用");
					}
				},"json");
			});
		})


		function repassword(ele1,ele2){

			var val1 = $(ele1).val();
			var val2 = $(ele2).val();
			if(val1 != val2){
                $(ele2+"_msg").text("两次输入的不一致");
                return false;
			}else{
                $(ele2+"_msg").text("");
                return true;
			}
		}


		function required(eleId){
       		var val =  $(eleId).val();
			if(val == null || val.length == 0){
                $(eleId+"_msg").text("不能为空");
                return false;
			}else{
                $(eleId+"_msg").text("");
                return true;
			}
		}

	</script>
</head>

<body>

	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
		<li><a href="#">首页</a></li>
		<li><a href="#">表单</a></li>
		</ul>
    </div>
    
    <div class="">
    
   	 <div class="formtitle"><span>添加用户信息</span></div>
		<form id="addUserForm" method="post" action="UserServlet/addUser">
			<ul class="forminfo">
				<li><label>用户名</label><input name="username" id="username" value="admin" type="text" class="dfinput" /><span id="username_msg"></span> </li>
				<li><label>密码</label><input name="password" id="password" value="123456" type="text" class="dfinput"/><span id="password_msg"></span></li>
				<li><label>确认密码</label><input name="repassword" id="repassword" value="123456" type="text" class="dfinput"/><span id="repassword_msg"></span></li>
				<li><label>年龄</label><input name="age" value="20" id="age" type="text" class="dfinput"/></li>

				<li><label>性别</label>
					<input name="sex" id="sex-man" type="radio" value="1" checked />男
					<input name="sex" id="sex-woman" type="radio" value="2"  class="df"/>女
				</li>

				<li><label>邮箱</label><input name="email" id="email" value="admin@qf.com" type="text" class="dfinput"/><span id="email_msg"></span>  </li>
				<li><label>生日</label><input name="birthday" value="2020-07-14" id="birthday" type="date" class="dfinput"/></li>

				<li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
			</ul>
		</form>
    </div>
<div style="display:none">
	<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

