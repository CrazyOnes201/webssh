<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--
  Created by IntelliJ IDEA.
  User: liu
  Date: 2017/12/18
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>login</title>
    <script>
        function jumpToRegist(){
            document.location.href="register.jsp";
        }
    </script>
</head>
<body>
<s:form action="login" method="post">
    <table>
        <tr><td>用户<input type="text" name="user.username"/></td></tr>
        <tr><td>密码<input type="password" name="user.password"/></td></tr>
        <tr><td><input type="submit" value="登录"/></td><td><input type="button" value="注册" onclick="jumpToRegist()"/></td></tr>
    </table>
</s:form>
</body>
</html>
