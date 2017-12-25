<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: liu
  Date: 2017/12/24
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<s:form action="SearchTicket" method="post">
    <table>
        <tr><td>用户<input type="text" name="user.username"/></td></tr>
        <tr><td>密码<input type="password" name="user.password"/></td></tr>
        <tr><td><input type="submit" value="登录"/></td><td><input type="button" value="注册" onclick="jumpToRegist()"/></td></tr>
    </table>
</s:form>
</body>
</html>
