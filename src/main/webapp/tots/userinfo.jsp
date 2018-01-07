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
                    <c:when test="${empty requestScope.usedticketList}">
                        <td colspan="6" style="text-align:center;">
                            没有数据 试试<a href="advancedsearch.jsp">高级查询</a>
                        </td>
                    </c:when>
                    <c:when test="${empty requestScope.usedticketList[0].ticketList}">
                        <td colspan="6" style="text-align:center;">
                            查询日期车票不能购买
                        </td>
                    </c:when>
                    <c:when test="${! empty requestScope.usedticketList}">
                        <c:forEach items="${requestScope.usedticketList}" var="elemTrain">
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
</html>
