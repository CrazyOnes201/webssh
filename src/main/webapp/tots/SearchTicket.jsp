<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="http://g.alicdn.com/sj/dpl/1.5.1/css/sui.min.css" rel="stylesheet">
<script type="text/javascript" src="http://g.alicdn.com/sj/lib/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="http://g.alicdn.com/sj/dpl/1.5.1/js/sui.min.js"></script>
<script type="text/javascript" src="g.alicdn.com/sj/dpl/1.5.1/css/sui.min.css"></script>
<link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
  <script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
  <link rel="stylesheet" href="jqueryui/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
<title>Insert title here</title>
</head>
<body>
<h2 style="text-align:center;">车票查询</h2>
<div class="search">
  <form id="form-msg" class="sui-form form-horizontal"  method="post" action="searchticket">
     <div id="navfirst">
        <ul id="menu">
            <li > <div class="control-group">
                     <label for="startpoint" class="control-label">起点：</label>
                     <div class="controls">
                        <input type="text" id="startpoint"  name="beginStation" class="input-middle"
                               value="${requestScope.tatList[0].beginStation}"/>
                     </div></div></li>
            <li ><div class="control-group">
                     <label for="finishpoint" class="control-label">终点：</label>
                     <div class="controls">
                        <input type="text" id="finishpoint"  name="targetStation" class="input-middle"
                               value="${requestScope.tatList[0].targetStation}" />
                     </div></div></li>
            <li ><div class="control-group">
                     <label for="time" class="control-label">出发日期：</label>
                     <div class="controls">                    
                      <input type="text" name="targetDate" id="datepicker" />
                     </div></div></li>
            <li ><div class="control-group">
                     <label for="time" class="control-label"></label>
                     <div class="controls">
                        <input type="submit"  class="input-small" value="车次查询" />
                     </div></div></li></ul>
      </div>
   </form>
</div>
<div class="contain">
      <div class="traintable">
         <table class="sui-table table-primary">
			  <thead>
			    <tr>
			      <th>车次</th>
			      <th>起点</th>
			      <th>终点</th>
			      <th>出发时间</th>
			      <th>到达时间</th>
			      <th>车票</th>
			    </tr>
			  </thead>
			  <tbody>
              <c:if test="${! empty requestScope.isPostResponse}">
              <c:choose>
                  <c:when test="${empty requestScope.tatList}">
                      <td colspan="6" style="text-align:center;">
                          没有数据 试试<a href="advancedsearch.jsp">高级查询</a>
                      </td>
                  </c:when>
                  <c:when test="${! empty requestScope.tatList}">
                      <c:forEach items="${requestScope.tatList}" var="elemTrain">
                        <tr>
                          <td>${elemTrain.trainId}</td>
                          <td>${elemTrain.beginStation}</td>
                          <td>${elemTrain.targetStation}</td>
                          <td>${elemTrain.beginTime}</td>
                          <td>${elemTrain.targetTime}</td>
                          <td>
                              <c:forEach items="${elemTrain.ticketList}" var="elemTicket">
                                  ${elemTicket.level}:${elemTicket.num}&nbsp;&nbsp;
                              </c:forEach>
                          </td>
                        </tr>
                      </c:forEach>
                  </c:when>
              </c:choose>
              </c:if>
			  </tbody>
			</table>
      </div>
</div>
</body>
<script type="text/javascript">
$('#demo1 input').datepicker({size:"small"});
</script>
<style type="text/css">
.search{
  margin:20px 10%;
  width:80%;
  text-align:center;
  background-color:#eef1f8;
  height:60px;
  
}
body{
  margin:0;
  padding:0;
}
#navfirst{

}
#menu { 
  font:15px verdana, arial, sans-serif; /* 设置文字大小和字体样式 */ 

}
#menu, #menu li {
list-style:none; /* 将默认的列表符号去掉 */
margin-top:20px;
}

#menu li { 
    float:left; 
}
.contain{
  margin:20px 10%;
  width:80%;
  text-align:center;
  background-color:#eef1f8;
  height:400px;
}
.traintable{
  width:80%;
  margin:40px 10%;
  text-align:center;
  
}
</style>
</html>