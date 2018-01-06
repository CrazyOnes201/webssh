<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="/tots/css/login-style.css" rel="stylesheet" type="text/css" />
<title>Login</title>
</head>

<div class="main">
  <div class="denglu">
    <div class="dlk">
      <s:form action="login" method="post">
      <table width="292" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="76" colspan="3"style="font-size:24px;text-align:center">用户登陆</td>
        </tr>
        <tr>
          <td width="65" style="font-size:18px">用户名</td>
          <td colspan="2"><input name="user.username" type="text" class="dlinput" placeholder="用户名/手机号/邮箱登陆" /></td>
        </tr>
        <tr>
          <td height="16" colspan="3"></td>
        </tr>
        <tr>
          <td style="font-size:18px">密码</td>
          <td colspan="2"><input name="user.password" type="password" class="dlinput" /></td>
        </tr>
        <tr>
          <td height="16" colspan="3"></td>
        </tr>
        
        <tr>
          <td height="16" colspan="3"></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="2"><input type="submit" value="登 录" class="loginbtn" /></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td colspan="2"><table width="224" border="0" cellspacing="0" cellpadding="0">
            <tr>
			</br>
              <td height="24"><a href="#none" class="mm" style="color: #e1e1e1">忘记登录密码？</a></td>
              <td align="right"><a href="/tots/register.jsp" class="zc" style="font-size:13px; color:#e1e1e1" >立即网上注册</a></td>
            </tr>
            <br>
          </table></s:form></td>
        </tr>
      </table>
    </div>
  </div>
</div>



</body>
</html>