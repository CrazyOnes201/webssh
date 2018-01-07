<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/register-style.css">
	
<title>register</title>
  </head>
  
  <body>
    <div class="register-container">
	<h1>火车订票系统</h1>
	
	<div class="connect">
		<h2>用户注册</h2>
	</div>

	<form action="regist" method="post" id="registerForm">
		<div>
			<input type="text" name="user.username" class="realname" placeholder="输入真实姓名" autocomplete="off"/>
		</div>
		<div>
			<input type="password" name="user.password" class="password" placeholder="输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<%--<div>--%>
			<%--<input type="password" name="confirm_password" class="confirm_password" placeholder="再次输入密码" oncontextmenu="return false" onpaste="return false" />--%>
		<%--</div>--%>
		<div>
			<%--<input type="text" name="realname" class="" placeholder="" autocomplete="off" />--%>
		</div>
		<div>
			<input type="text" name="user.identityNum" class="idnumber" placeholder="输入二代身份证号" autocomplete="off" />
		</div>
		<div>
			<input type="text" name="user.phone" class="phone_number" placeholder="输入手机号码" autocomplete="off" id="number"/>
		</div>
		<%--<div>--%>
			<%--<input type="email" name="email" class="email" placeholder="输入邮箱地址" oncontextmenu="return false" onpaste="return false" />--%>
		<%--</div>--%>

		<button id="submit" type="submit">注 册</button>
	</form>
	<a href="login.jsp" type="submit" class="register-tis" >登陆</a>

</div>
  </body>
</html>
