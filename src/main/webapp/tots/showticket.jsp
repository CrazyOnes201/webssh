<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<script language="JavaScript" src="js/publicFun.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示所查询的火车票信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body background="/tots/images/003.jpg">
  <br />
<table width="80%" border="0" align="center">
  <tr>
    <td align="center" bordercolor="#FFFFFF"> 
      <div align="center"><strong><font size="5">车票购买</font></strong></div></td>
  </tr>
</table>


<%--<form  name="frmDataList" action="#" method="post">--%>
<br /><br /><br /><br /><br />
<table width="80%" border="1" align="center">
  <tr> 

    <td width="10%"><div align="center"><strong>车次</strong></div></td>
      <td width="10%"><div align="center"><strong>出发日期</strong></div></td>
    <td width="10%"><div align="center"><strong>起点</strong></div></td>
      <td width="10%"><div align="center"><strong>起始时间</strong></div></td>
    <td width="10%"><div align="center"><strong>终点</strong></div></td>
	<td width="10%"><div align="center"><strong>到达时间</strong></div></td>
	<td width="10%"><div align="center"><strong>票价</strong></div></td>
  </tr>
  <%
//      Object obj = request.getAttribute("ticketList");
//    if (obj !=null) {
//    List list = (List)obj;
//    Iterator itm = list.iterator();
//    TicketBean ticket = null;
//    while(list!=null && itm.hasNext()) {
//    ticket = (TicketBean)itm.next();
  %>
  <tr>
      <td width="10%"><div align="center">${sessionScope.tarTicket.trainId}</div></td>
      <td width="10%"><div align="center">${sessionScope.tarTicket.sDate}</div></td>
    <td width="10%"><div align="center">${sessionScope.tarTicket.beginStation}</div></td>
    <td width="10%"><div align="center">${sessionScope.tarTicket.beginTime}</div></td>
    <td width="10%"><div align="center">${sessionScope.tarTicket.targetStation}</div></td>
	<td width="10%"><div align="center">${sessionScope.tarTicket.targetTime}</div></td>
	<td width="10%"><div align="center"></div></td>
  </tr>
 <%--<% }} %>--%>
</table>

<form action="buyoneticket" method="post">
    <table width="50%" border="1" align="center">
        <tr>
            <th>票型</th>
            <th>余票</th>
            <th>金额</th>
            <th>选择</th>
        </tr>
        <c:forEach items="${sessionScope.tarTicket.ticketList}" var="elemTicket">
            <tr>
                <td>${elemTicket.level}</td>
                <td>${elemTicket.num}</td>
                <td>${elemTicket.price}</td>
                <td><input type="radio" name="tarLevel1" value="${elemTicket.level}" /></td>
            </tr>
        </c:forEach>
      <tr>
        <td align="right" colspan="4">
            <input type="submit" id="bookPiao" value="订票" />
            &nbsp;
            <input type="button" name="Submit6" value="返回" onClick="doCheck('back')">
        </td>
      </tr>
    </table>
</form>

</body>
</html>
<script  language="javascript">
   function doCheck(cmd){
      var  frm = frmDataList;      
       
      if (cmd == "back"){         
        frm.action="main.jsp";
        frm.submit();
        return;
      }
      //---以下操作（编辑，删除，查看）需要选择用户，才能进行操作。以下调用js函数来判断，单选按钮的名字必须定义为recordID
      var sel = getSelectedItem(frm);
      if (sel==null){
            alert("请选择您要订的票!");
            return;
      }      
      if (cmd == "book"){         
        frm.action="BookServlet?checi="+sel;
        frm.submit();
      }
   }
   
</script>