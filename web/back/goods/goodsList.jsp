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
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});
</script>

</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">数据表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click"><a href="back/goods/goodsadd.jsp"><img src="images/t01.png" align="middle" />添加</a></li>
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    <table class="tablelist" >
    	<thead>
    	<tr>
            <th>商品编号</th>
            <th>商品名称</th>
            <th>商品大类</th>
            <th>商品小类</th>
            <th>商品描述</th>
            <th>商品图片</th>
            <th>单价</th>
            <th>优惠价</th>
            <th>库存</th>
            <th>操作</th>
       
        </tr>
        </thead>
        <tbody>
        
	        <c:forEach items="${page.list}" var="goods">
                <tr>
                    <td>${goods.id}</td>
                    <td>${goods.gname}</td>
                    <td>${goods.gpname}</td>
                    <td>${goods.gfname}</td>
                    <td>${goods.gdesc}</td>
                    <td>
                        <img onerror="this.src='GoodsServlet/showDefaultPng'" style="width: 100px" src="GoodsServlet/getGoodsPngById?id=${goods.id}" alt=""/>
                    </td>
                    <td>${goods.gprice}</td>
                    <td>${goods.gpriceOff}</td>
                    <td>${goods.gnum}</td>
                    <td>
                        <a href="GoodsServlet/getGoodsById?id=${goods.id}">编辑</a>
                        <a href="GoodsServlet/deleteGoodsById?id=${goods.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
	        
        </tbody>
    </table>

        <jsp:include page="/back/common/page.jsp"></jsp:include>
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
	
	
	
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
