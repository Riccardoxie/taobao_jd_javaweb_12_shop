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
	<script type="text/javascript" src="js/goodsType.js"></script>
	<script type="text/javascript">

		$(function(){

		    // 初始化大类
		    initGoodsType("#gpid");

		    $("#gpid").change(function(){

		        // 获取用户选择的大类id
		        var id = $(this).val();

		        // 获取小类的元素
		        var gfid = $("#gfid");

		        // 初始化小类
                changeFid(id,gfid);

			});

			// 给上传文件绑定点击事件
			$("#gpgn").change(function(){

			    // 1.获取用户上传的文件
				var file = this.files[0];

				// 2.根据图片对象生成一个图片地址
				var url = window.URL.createObjectURL(file);

				// 3.修改图片标签的src属性
				$("#showPng").prop("src",url);
			});
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
    
    <div class="formtitle"><span>添加商品信息</span></div>
    <form method="post"  action="GoodsServlet/addGoods" enctype="multipart/form-data">
    	<ul class="forminfo">
	    <li><label>商品名称</label><input name="gname" value="TCL 电视" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
	    <li><label>所属大类</label>
	    		<select name="gpid" id="gpid">
	    			<option value="">==请选择==</option>
	    		</select>
	    </li>
	    <li><label>所属小类</label>
	    		<select name="gfid" id="gfid">
					<option value="0">无</option>
					<option value="1">xx</option>
	    		</select>
	    </li>
	     <li><label>商品图片</label><input name="gpng" id="gpgn" type="file" /></li>
	     <li>
			 <img style="width: 200px" id="showPng" src="" alt=""/>
		 </li>
	    <li><label>商品价格</label><input name="gprice" value="4999.0" type="text" class="dfinput" /></li>
	    <li><label>商品优惠价</label><input name="gpriceOff"  value="4333.0" type="text" class="dfinput" /></li>
	    <li><label>库存</label><input name="gnum"  value="10" type="text" class="dfinput" /></li>
	    <li><label>商品描述</label><textarea rows="8" cols="40" name="gdesc" >高清 4K 曲面</textarea></li>
	    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    
    </form>
    </div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

