<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 25.04.15
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="row" style="text-align: center">
    <h1 class="headerfirstlevel">${news.menuTitle}</h1>
    <%-- BUTTONS FOR LOG-OUT AND FOR GOING IN USERCABINET --%>
    <c:if test="${usertype eq 'GUEST'}">
    <div align="right">
        <form method="post" action="<c:url value="controller"/>">
            <input type="hidden" name="command" value="registration"/>
            <button type="submit" class="btn btn-success">Registration</button>
        </form>
    </div>
    </c:if>
    <c:if test="${usertype eq 'ADMINISTRATOR' || usertype eq 'USER'}">
    <div align="right">
        <!-- Single button -->
        <div class="btn-group">
            <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    ${user.name} menu <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a href="controller?command=goincabinet">Go in my cabinet</a></li>
                <li><a href="controller?command=editperson">Change info about me</a></li>
                <li class="divider"></li>
                <li><a href="controller?command=logout">log out</a></li>
            </ul>
        </div>
    </div>
    </c:if>
</body>
</html>
