<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: crazyones
  Date: 17-12-25
  Time: 下午3:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
欢迎，${sessionScope.user.username}
<c:choose>
    <c:when test="${user.admin eq 1}">管理员</c:when>
    <c:when test="${user.admin eq 0 }">用户</c:when>
</c:choose>
<a href="personalCenter.jsp">个人中心</a>
</body>
</html>