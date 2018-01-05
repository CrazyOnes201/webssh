<%--
  Created by IntelliJ IDEA.
  User: liu
  Date: 2018/1/5
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员</title>
</head>
<body>
<s:form action="" method="POST">
    <table>
        <tr><td>trainId<input type="text" name="train.trainId"></td></tr>
        <tr><td>密码<input type="password" name="train.startStation"></td></tr>
        <tr><td>身份证号<input type="text" name="train.endStation"></td></tr>
        <tr><td>电话号码<input type="text" name="train.seatType"></td></tr>
        <tr><td>操作<input type="text" name=""></td></tr>
        <tr><td><input type="submit" value="提交"></td></tr>
    </table>
</s:form>
</body>
</html>
