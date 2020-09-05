
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <base href="<%=request.getContextPath()+"/"%>">
    <title>Title</title>
</head>
<body>

<a href="${page.url}?pageNum=1">首页</a>

<c:if test="${page.pageNum > 1}">
    <a href="${page.url}?pageNum=${page.pageNum-1}">上一页</a>
</c:if>

<c:if test="${page.pageNum < page.totalPage}">
    <a href="${page.url}?pageNum=${page.pageNum+1}">下一页</a>
</c:if>

<a href="${page.url}?pageNum=${page.totalPage}">尾页</a>

共:${page.totalCount}
</body>
</html>
