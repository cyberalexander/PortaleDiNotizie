<!--
Created by IntelliJ IDEA.
User: alexanderleonovich
Date: 30.05.15
Time: 20:30
To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
</head>
<body>
<div align="center">
    <nav>
        <ul class="pagination">
            <c:forEach items="${paginatorList}" var="pager">
                <li><a href="shownews.do?pageNumber=${pager}&amp;pageId=${news.pageId}">${pager}</a></li>
            </c:forEach>
        </ul>
    </nav>
</div>
</body>
</html>