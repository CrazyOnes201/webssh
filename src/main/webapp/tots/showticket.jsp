<%@ page language="java" import="java.util.*,com.bookonline.JavaBean.*" contentType="text/html;charset=gb2312" %>

<script language="JavaScript" src="js/publicFun.js"></script>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>��ʾ����ѯ�Ļ�Ʊ��Ϣ</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body background="images/003.jpg">
<table width="80%" border="0" align="center">
  <tr>
    <td align="center" bordercolor="#FFFFFF"> 
      <div align="center"><strong><font size="5">��Ʊ��Ϣ����</font></strong></div></td>
  </tr>
</table>


<form  name="frmDataList" action="#" method="post">
<table width="80%" border="1" align="center">
  <tr> 
    <td width="5%"><div align="center"></div></td>
    <td width="10%"><div align="center"><strong>����</strong></div></td>
    <td width="10%"><div align="center"><strong>���</strong></div></td>
    <td width="10%"><div align="center"><strong>�յ�</strong></div></td>
	<td width="10%"><div align="center"><strong>ʱ��</strong></div></td>
	<td width="10%"><div align="center"><strong>Ʊ��</strong></div></td>
	<td width="15%"><div align="center"><strong>ʣ��Ʊ��</strong></div></td>
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
    <td width="5%"><input type="radio" name="recordID" value=""></td>
    <td width="10%"><div align="center"></div></td>
    <td width="10%"><div align="center"></div></td>
    <td width="10%"><div align="center"></div></td>
	<td width="10%"><div align="center"></div></td>
	<td width="10%"><div align="center"></div></td>
	<td width="15%"><div align="center"></div></td>
  </tr>
 <%--<% }} %>--%>
</table>

<table width="15%" border="1" align="center">
  <tr>
    <td align="right">
	    <input name="bookPiao" type="button" id="bookPiao" value="��Ʊ" onClick="doCheck('book')">
	    &nbsp; 
	    <input type="button" name="Submit6" value="����" onClick="doCheck('back')">
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
      //---���²������༭��ɾ�����鿴����Ҫѡ���û������ܽ��в��������µ���js�������жϣ���ѡ��ť�����ֱ��붨��ΪrecordID
      var sel = getSelectedItem(frm);
      if (sel==null){
            alert("��ѡ����Ҫ����Ʊ!");
            return;
      }      
      if (cmd == "book"){         
        frm.action="BookServlet?checi="+sel;
        frm.submit();
      }
   }
   
</script>