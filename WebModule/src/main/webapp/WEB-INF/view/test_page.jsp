<!--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 19.06.15
  Time: 16:20
  To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <html>
    <head><title>Simple jspx page</title></head>
    <body>
    Place content here
    <h1>HELLO FROM HELLO!</h1>
    <%--<sec:authorize access="isAuthenticated()">
        <p>Ваш логин: <sec:authentication property="principal.username"/></p>
        <p>Ваш логин: <sec:authentication property="principal.role"/></p>
        <p>Ваш логин: <sec:authentication property="principal.user_role"/></p>

        <p>
            <a class="btn btn-lg btn-danger" href="/logout" role="button">Выйти</a>
        </p>
    </sec:authorize>--%>
    </body>
    </html>
