<%--
  Created by IntelliJ IDEA.
  User: alexanderleonovich
  Date: 02.05.15
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>registration page</title>
</head>
<body>

<div class="row">
    <h1 class="custom-header-first-level">Registration form</h1>
</div>
<div class="container">
    <div class="col-md-3"></div>
    <div class="col-md-6" align="right">
        <s:form modelAttribute="personTO"  method="post" action="addperson_second_step.do">
            <label for="name" class="text">Name:</label>
            <s:input size="30" id="name" placeholder="what`s your name?" path="name"/><br/>
            <label for="surname" class="text">Surname:</label>
            <s:input size="30" id="surname" placeholder="what`s your surname" path="surname"/><br/>
            <button type="submit" class="btn btn-primary">NEXT</button>
        </s:form>
    </div>
    <div class="col-md-3"></div>
</div>
</body>
</html>
