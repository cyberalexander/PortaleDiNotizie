<!--
Created by IntelliJ IDEA.
User: alexanderleonovich
Date: 19.06.15
Time: 12:03
To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="locale" %>
<html>
<head>
    <!-- Bootstrap core CSS -->
    <link href="./view/assests/css/bootstrap.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="./view/assests/css/signin.css" rel="stylesheet"/>
</head>
<body>
<div class="container" style="width: 300px;">
    <c:url value="/j_spring_security_check" var="loginUrl"/>

    <form action="${loginUrl}" method="post">
        <h2 class="form-signin-heading" style="color: snow; text-align: center"><locale:message code="label.signin"/></h2>
        <input type="text" class="form-control" name="j_username" placeholder="Email address" required="required"
               autofocus="autofocus" value="email@email.com"/>
        <input type="password" class="form-control" name="j_password" placeholder="Password" required="required"
               value="1234567890"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><locale:message code="button.signup"/></button>
    </form>
</div>
</body>
</html>
