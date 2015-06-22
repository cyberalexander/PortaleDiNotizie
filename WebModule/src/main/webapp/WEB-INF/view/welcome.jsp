<!--
Created by IntelliJ IDEA.
User: alexanderleonovich
Date: 17.06.15
Time: 20:54
To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="locale" %>
<html>
<head>
    <title></title>
    <!-- Bootstrap core CSS -->
    <link href="./assests/css/bootstrap.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="./assests/css/jumbotron-narrow.css" rel="stylesheet"/>
    <link href="./assests/css/custom-style-library.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="jumbotron" style="margin-top: 20px;">
        <h1><locale:message code="lavel.portaledinotizie"/></h1>

        <p class="lead"><locale:message code="label.salute"/></p>
        <sec:authorize access="!isAuthenticated()">
            <p>
                <a class="btn btn-lg btn-info" href="shownews.do" role="button">
                    <locale:message code="button.gosite"/>
                </a>
            </p>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <h4><locale:message code="label.congrat.username"/><sec:authentication property="principal.username"/></h4>
            <h4><locale:message code="label.congrat.role"/><sec:authentication property="principal.authorities"/></h4>
            <p>
                <a class="btn btn-lg btn-danger" href="logout.do" role="button"><locale:message code="button.logout"/></a>
                <a class="btn btn-lg btn-info" href="person_cabinet.do" role="button"><locale:message code="button.goincabinet"/></a>
                <%--<a class="btn btn-lg btn-success" href="shownews.do" role="button"><locale:message code="button.watchnews"/></a>--%>
            </p>
        </sec:authorize>
    </div>
    <div class="footer">
        <p>© Alexander Leonovich 2015</p>
    </div>

</div>
</body>
</html>
