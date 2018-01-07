<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
userinfo
<a href="SearchTicket.jsp">查询火车票</a>
<form action="userticket" method="post">

    <input type="submit" value="查询自己的票">
</form>
<div class="contain">
    <div class="traintable">
        <table class="sui-table table-primary">
            <thead>
            <tr>
                <th>车次</th>
                <th>始发站</th>
                <th>终点站</th>
                <th>发车时间</th>
                <th>座位</th>
                <th>票价</th>
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
                    <%--<c:when test="${empty requestScope.usedticketList}">--%>
                        <%--<td colspan="6" style="text-align:center;">--%>
                            <%--查询日期车票不能购买--%>
                        <%--</td>--%>
                    <%--</c:when>--%>
                    <c:when test="${! empty requestScope.usedticketList}">
                        <c:forEach items="${requestScope.usedticketList}" var="elemTrain">
                            <tr>
                                <td>${elemTrain.trainId}</td>
                                <td>${elemTrain.startStationId}</td>
                                <td>${elemTrain.endStationId}</td>
                                <td>${elemTrain.date}</td>
                                <td>${elemTrain.level}</td>
                                <td>${elemTrain.money}</td>
                                <%--<td>--%>
                                    <%--<c:forEach items="${elemTrain.ticketList}" var="elemTicket">--%>
                                        <%--${elemTicket.level}:${elemTicket.num}&nbsp;&nbsp;--%>
                                    <%--</c:forEach>--%>
                                <%--</td>--%>
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
</html>
