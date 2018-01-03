<%@ taglib prefix="s" uri="/struts-tags" %>
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
    <title>Title</title>
    <script>
        function jumpToLogin(){
            document.location.href="login.jsp";
        }
    </script>
</head>
<body>
<s:form action="regist" method="POST">
    <table>
        <tr><td>用户名<input type="text" name="user.username"></td></tr>
        <tr><td>密码<input type="password" name="user.password"></td></tr>
        <tr><td>身份证号<input type="text" name="user.identityNum"></td></tr>
        <tr><td>电话号码<input type="text" name="user.phone"></td></tr>
        <tr><td><input type="submit" value="注册"></td><td><input type="button" value="登陆" onclick="jumpToLogin()"></td></tr>
    </table>
</s:form>
</body>
</html>
