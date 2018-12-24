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

    </script>
    <title>Insert title here</title>

</head>
<body>

</div><div class="Navigation">
    <div class="left_logo">
        <img src="images/logo.png" alt="logo" align="left"/>
        <p class="project_name">
            火车订票系统</p>
    </div>
    <div id="navfirst">
        <ul id="menu">
            <li id="s3"><a href="SearchTicket.jsp" title="车票查询">车票查询</a></li>
            <li id="s1"><a href="userinfo.jsp" title="个人中心">个人中心</a></li>
            <li id="s2"><a href="login.jsp" title="记账">退出登陆</a></li>
        </ul>
    </div>
</div>
<h2 style="text-align:center;">订单查询</h2>

<div class="search">
    <form id="form-msg" class="sui-form form-horizontal"  action="userticket" method="post">
        <div id="navfirst" >
            <input type="submit" class="input-middle" value="查询自己的票" style="margin-top: 25px">
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
                <th>出发时间</th>
                <th>终点</th>
                <th>到达时间</th>
                <th>车票</th>
                <th>&nbsp;&nbsp;&nbsp;&nbsp;</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${! empty requestScope.isPostResponse}">
                <c:choose>
                    <c:when test="${empty requestScope.usedticketList}">
                        <td colspan="6" style="text-align:center;">
                            没有数据 试试<a href="advancedsearch.jsp">高级查询</a>
                        </td>
                    </c:when>
                    <c:when test="${! empty requestScope.usedticketList}">
                        <c:forEach items="${requestScope.usedticketList}" var="elemTrain">
                            <tr>
                                <td>${elemTrain.trainId}</td>
                                <td>${elemTrain.startStationId}</td>
                                <td>${elemTrain.endStationId}</td>
                                <td>${elemTrain.date}</td>
                                <td>${elemTrain.level}</td>
                                <td>${elemTrain.money}</td>
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
    * { padding:0; margin:0;}

    .Navigation{
        width: 100%;
        height: 80px;
        background-color:#4b6cd8;
        overflow:hidden;
    }
    img{
        width:100px;
        height:80px;
    }

    p.project_name{
        font-style: normal;
        font-size: 30px;
        color: #ffffff;
        float: left;
        margin-top:30px;
    }

    #menu {
        font:50px verdana, arial, sans-serif; /* 设置文字大小和字体样式 */
        margin-left:400px;
    }

    #menu, #menu li {
        list-style:none; /* 将默认的列表符号去掉 */
    }

    #menu li {
        float:left;
    }

    #menu li a {
        display:block;
        padding:8px 30px;
        color:#ffffff;
        text-decoration:none;
        margin-top: 0px;
        font-size: 30px;
    }

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
        height:600px;
    }
    .traintable{
        width:80%;
        margin:40px 10%;
        text-align:center;

    }
</style>
</html>
