<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
	String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		$(function(){

		    // 在JS中也是可以写EL表达式
		    var sex = "${user.sex}";

		    if(sex == 1){
		        $("#sex-man").prop("checked","checked");
			}else{
		        $("#sex-woman").prop("checked","checked");
			}


		})
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
    
    <div class="formbody">
    
    <div class="formtitle"><span>编辑用户信息</span></div>
    <form method="post" action="UserServlet/updateUser">

    	<input type="hidden" name="id"	value="${user.id}" />

    	<ul class="forminfo">
			<li><label>用户名</label><input name="username" id="username" value="${user.username}" type="text" class="dfinput" /><span id="username_msg"></span> </li>
			<li><label>年龄</label><input name="age" value="20" id="age" type="text" class="dfinput"/></li>

			<li><label>性别</label>
				<input name="sex" id="sex-man" type="radio" value="1"  />男
				<input name="sex" id="sex-woman" type="radio" value="2" />女
			</li>

			<li><label>邮箱</label><input name="email" id="email" value="${user.email}" type="text" class="dfinput"/><span id="email_msg"></span>  </li>
			<li><label>生日</label><input name="birthday" value="${user.birthday}" id="birthday" type="date" class="dfinput"/></li>

			<li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    </form>
    </div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

